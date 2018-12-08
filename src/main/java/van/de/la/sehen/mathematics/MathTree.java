package van.de.la.sehen.mathematics;

import org.springframework.lang.NonNull;
import van.de.la.sehen.warning.WarningStream;

import org.springframework.lang.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class MathTree {
    @NonNull
    public abstract AbstractRealTree evaluateToReal(@NonNull MathContext context);

    public double evaluateToDouble(@NonNull MathContext context) {
        return evaluateToReal(context).getValue();
    }
}

abstract class AbstractRealTree extends MathTree {
    @Override
    @NonNull
    public AbstractRealTree evaluateToReal(@NonNull MathContext context) {
        return this;
    }

    public AbstractRealTree plus(AbstractRealTree rhs) {
        return new RealTree(this.getValue() + rhs.getValue());
    }

    public AbstractRealTree minus(AbstractRealTree rhs) {
        return new RealTree(this.getValue() - rhs.getValue());
    }

    public AbstractRealTree times(AbstractRealTree rhs) {
        return new RealTree(this.getValue() * rhs.getValue());
    }

    public AbstractRealTree divide(AbstractRealTree rhs) {
        return new RealTree(this.getValue() / rhs.getValue());
    }

    public AbstractRealTree pow(AbstractRealTree rhs) {
        return new RealTree(Math.pow(this.getValue(), rhs.getValue()));
    }

    public static AbstractRealTree errorTree() {
        return new RealTree(Double.NaN);
    }

    @Nullable
    public Function<AbstractRealTree, AbstractRealTree> getFunctionFromOperator(MathOperator operator) {
        switch (operator) {
            case PLUS:
                return this::plus;
            case MINUS:
                return this::minus;
            case MULTIPLY:
                return this::times;
            case DIVIDE:
                return this::divide;
            case POWER:
                return this::pow;
        }
        WarningStream.putWarning("Unrecognized Operator.", this);
        return null;
    }

    public abstract double getValue();
}

class RealTree extends AbstractRealTree {
    private double value;

    public RealTree(double value) {
        this.value = value;
    }

    @Override
    public double getValue() {
        return value;
    }
}

class DelayerRealTree extends AbstractRealTree {
    private Supplier<Double> supplier;

    public DelayerRealTree(Supplier<Double> supplier) {
        this.supplier = supplier;
    }

    @Override
    public double getValue() {
        Double d = supplier.get();
        if (d == null) {
            WarningStream.putWarning("Supplier returns null.", this);
            return Double.NaN;
        } else return d;
    }
}

class Identifier extends MathTree {
    private String name;

    public Identifier(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    @NonNull
    public AbstractRealTree evaluateToReal(@NonNull MathContext context) {
        AbstractRealTree result = context.retrieve(name);
        if (result == null) return RealTree.errorTree();
        return result;
    }
}

enum MathOperator {
    PLUS, MINUS, MULTIPLY, DIVIDE, POWER, CALL;

    public static MathOperator fromChar(int character) {
        switch (character) {
            case '+':
                return PLUS;
            case '-':
                return MINUS;
            case '*':
                return MULTIPLY;
            case '/':
                return DIVIDE;
            case '^':
                return POWER;
        }
        return null;
    }
}

class BinaryTree extends MathTree {
    private MathTree lhs;
    private MathTree rhs;
    private MathOperator operator;

    public BinaryTree(MathTree lhs, MathTree rhs, MathOperator operator) {
        this.lhs = lhs;
        this.rhs = rhs;
        this.operator = operator;
    }

    @Nullable
    private static Function<Double, Double> mathFunctionFromName(String name) {
        switch (name) {
            case "sin":
                return Math::sin;
            case "cos":
                return Math::cos;
            case "tan":
                return Math::tan;
            case "log":
                return Math::log;
            case "exp":
                return Math::exp;
        }
        WarningStream.putWarning("Unrecognized function name " + name + ".", BinaryTree.class);
        return null;
    }

    public AbstractRealTree evaluateFunction(@NonNull MathContext context) {
        if (!(lhs instanceof Identifier)) {
            WarningStream.putWarning("Not a function on lhs.", this);
            return RealTree.errorTree();
        }
        Function<Double, Double> function = mathFunctionFromName(((Identifier) lhs).getName());
        if (function == null) return RealTree.errorTree();
        return new RealTree(function.apply(rhs.evaluateToReal(context).getValue()));
    }

    @Override
    @NonNull
    public AbstractRealTree evaluateToReal(@NonNull MathContext context) {
        if (operator == MathOperator.CALL) {
            return evaluateFunction(context);
        }
        Function<AbstractRealTree, AbstractRealTree> lhsOperation = lhs.evaluateToReal(context).getFunctionFromOperator(operator);
        if (lhsOperation != null) {
            return lhsOperation.apply(rhs.evaluateToReal(context));
        } else {
            return RealTree.errorTree();
        }
    }
}
