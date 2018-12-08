package van.de.la.sehen.mathematics;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class MathContext {
    private Map<String, AbstractRealTree> substitutions;

    public MathContext() {
        substitutions = new HashMap<>();
    }

    @Nullable
    public AbstractRealTree retrieve(String key) {
        return substitutions.get(key);
    }

    public MathContext put(String key, AbstractRealTree value) {
        substitutions.put(key, value);
        return this;
    }

    public MathContext put(String key, double value) {
        return put(key, new RealTree(value));
    }

    public MathContext put(String key, Supplier<Double> value) {
        return put(key, new DelayerRealTree(value));
    }
}
