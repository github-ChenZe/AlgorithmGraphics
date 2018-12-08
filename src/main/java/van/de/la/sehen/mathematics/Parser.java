package van.de.la.sehen.mathematics;

import van.de.la.sehen.warning.WarningStream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Parser {
    public static void main(String[] args) throws IOException {
        MathTree tree = new Parser().parse(new BufferedReader(new InputStreamReader(System.in)));
        System.out.println(tree.evaluateToReal(new MathContext().put("t", new RealTree(1.57))).getValue());

    }

    public MathTree parse(String expr) throws IOException {
        try (StringReader sr = new StringReader(expr);
             BufferedReader br = new BufferedReader(sr)) {
            return parse(br);
        }
    }

    public MathTree parse(BufferedReader reader) throws IOException {
        TokenSequence sequence = TokenSequence.tokenify(reader);
        ListIterator<Token> iterator = sequence.getTokenList().listIterator();
        return expr(iterator);
    }

    public MathTree expr(ListIterator<Token> tokens) {
        MathTree body = exprAddAndMinus(tokens);
        return body;
    }

    public MathTree exprAddAndMinus(ListIterator<Token> tokens) {
        MathTree lhs = exprMultipleAndDivide(tokens);
        while (tokens.hasNext()) {
            Token next = tokens.next();
            assert next instanceof CharToken;
            MathOperator operator = MathOperator.fromChar(((CharToken) next).getThisChar());
            if (operator != MathOperator.PLUS && operator != MathOperator.MINUS) {
                tokens.previous(); // feed the unexpected token back
                return lhs;
            }
            MathTree rhs = exprMultipleAndDivide(tokens);
            lhs = new BinaryTree(lhs, rhs, operator);
        }
        return lhs;
    }

    public MathTree exprMultipleAndDivide(ListIterator<Token> tokens) {
        MathTree lhs = exprPow(tokens);
        while (tokens.hasNext()) {
            Token next = tokens.next();
            assert next instanceof CharToken;
            MathOperator operator = MathOperator.fromChar(((CharToken) next).getThisChar());
            if (operator != MathOperator.MULTIPLY && operator != MathOperator.DIVIDE) {
                tokens.previous(); // feed the unexpected token back
                return lhs;
            }
            MathTree rhs = exprPow(tokens);
            lhs = new BinaryTree(lhs, rhs, operator);
        }
        return lhs;
    }

    public MathTree exprPow(ListIterator<Token> tokens) {
        MathTree lhs = terminal(tokens);
        if (!tokens.hasNext()) return lhs;
        Token next = tokens.next();
        assert next instanceof CharToken;
        MathOperator operator = MathOperator.fromChar(((CharToken) next).getThisChar());
        if (operator != MathOperator.POWER) {
            tokens.previous(); // feed the unexpected token back
            return lhs;
        }
        MathTree rhs = exprPow(tokens);
        lhs = new BinaryTree(lhs, rhs, operator);
        return lhs;
    }

    public MathTree terminal(ListIterator<Token> tokens) {
        if (!tokens.hasNext()) {
            WarningStream.putWarning("Token sequence comes to unexpected end.", this);
            return null;
        }
        Token token = tokens.next();
        if (token instanceof RealToken) {
            return new RealTree(((RealToken) token).getValue());
        } else if (token instanceof IdentifierToken) {
            Identifier identifier = new Identifier(((IdentifierToken) token).getName());
            if (!tokens.hasNext()) return identifier;
            Token next = tokens.next();
            if (!(next instanceof CharToken) || ((CharToken) next).getThisChar() != '(') {
                tokens.previous();
                return identifier;
            }
            tokens.previous();
            return new BinaryTree(identifier, terminal(tokens), MathOperator.CALL);
        } else if (token instanceof CharToken) {
            int character = ((CharToken) token).getThisChar();
            if (character != '(') {
                WarningStream.putWarning("Number, Identifier or ( expected.", this);
                return null;
            }
            MathTree result = expr(tokens);
            assert tokens.hasNext();
            Token next = tokens.next();
            assert next instanceof CharToken;
            if (((CharToken) next).getThisChar() != ')') {
                WarningStream.putWarning(") expected.", this);
                return null;
            }
            return result;
        }
        WarningStream.putWarning("Number, Identifier or ( expected.", this);
        return null;
    }
}

class TokenSequence {
    public static final int EOF = -1;

    private List<Token> tokenList = new ArrayList<>();

    private TokenSequence push(Token token) {
        tokenList.add(token);
        return this;
    }

    public List<Token> getTokenList() {
        return tokenList;
    }

    public static TokenSequence tokenify(BufferedReader reader) throws IOException {
        TokenSequence sequence = new TokenSequence();
        int character;
        while ((character = reader.read()) != 0 && character != EOF) {
            if (belongsTo(character, '+', '-', '*', '/', '^', '(', ')')) {
                sequence.push(new CharToken(character));
            } else if (isIdentifierChar(character)) {
                sequence.push(consumeIdentifierToken(reader, character));
            } else if (isRealChar(character)) {
                sequence.push(consumeRealToken(reader, character));
            } else if (belongsTo(character, ' ', '\n', '\r', '\t')) {
                continue;
            } else {
                WarningStream.putWarning("Unrecognized char: " + (char) character + ".", TokenSequence.class);
            }
        }
        return sequence;
    }

    private static Token consumeRealToken(BufferedReader reader, int initialChar) throws IOException {
        int currentChar = initialChar;
        StringBuilder sb = new StringBuilder();
        do {
            sb.append((char) currentChar);
            reader.mark(1);
            currentChar = reader.read();
        } while (isRealChar(currentChar));
        reader.reset();
        return new RealToken(Double.parseDouble(sb.toString()));
    }

    private static boolean isRealChar(int character) {
        return inClosedRange(character, '0', '9') || belongsTo(character, '.');
    }

    private static Token consumeIdentifierToken(BufferedReader reader, int initialChar) throws IOException {
        int currentChar = initialChar;
        StringBuilder sb = new StringBuilder();
        do {
            sb.append((char) currentChar);
            reader.mark(1);
            currentChar = reader.read();
        } while (isIdentifierChar(currentChar));
        reader.reset();
        return new IdentifierToken(sb.toString());
    }

    private static boolean isIdentifierChar(int character) {
        return inClosedRange(character, 'A', 'Z') ||
                inClosedRange(character, 'a', 'z') ||
                inClosedRange(character, '_', '_');
    }

    private static boolean belongsTo(int character, int ... candidates) {
        for (int candidate : candidates) {
            if (character == candidate) return true;
        }
        return false;
    }

    private static boolean inClosedRange(int character, int rangeStart, int rangeEnd) {
        return character >= rangeStart && character <= rangeEnd;
    }
}

interface Token {

}

class CharToken implements Token {
    private int thisChar;

    public CharToken(int thisChar) {
        this.thisChar = thisChar;
    }

    public int getThisChar() {
        return thisChar;
    }

    @Override
    public String toString() {
        return "CharToken{" +
                "thisChar=" + thisChar +
                '}';
    }
}

class IdentifierToken implements Token {
    private String name;

    public IdentifierToken(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "IdentifierToken{" +
                "name='" + name + '\'' +
                '}';
    }
}

class RealToken implements Token {
    private double value;

    public RealToken(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "RealToken{" +
                "value=" + value +
                '}';
    }
}
