package eval.backend;

import eval.backend.Token;
import eval.backend.tokens.*;

public interface TokenDatabase {

    /**
     * These two functions are used to simulate booleans with doubles
     */
    private static boolean DoubleToBool(double a) {
        return a > 0.5;
    }

    private static double BoolToDouble(boolean a) {
        return a ? 1.0 : 0;
    }

    /**
     * This is used when parsing the user string
     */
    final Token[] kTokenList = {
            // Ignore Cbaracters
            new NoOP(" "),

            // Parenthesis
            new Bracket("(", Bracket.BracketType.LEFT),
            new Bracket(")", Bracket.BracketType.RIGHT),
            new Bracket("[", Bracket.BracketType.LEFT),
            new Bracket("]", Bracket.BracketType.RIGHT),
            new Bracket("{", Bracket.BracketType.LEFT),
            new Bracket("}", Bracket.BracketType.RIGHT),
            new Bracket(",", Bracket.BracketType.FLUSH),
            new Bracket(";", Bracket.BracketType.FLUSH),

            // Operators
            new Operator("+",   (a, b) -> a + b, 2, Operator.Order.LEFT),
            new Operator("-",   (a, b) -> a - b, 2, Operator.Order.LEFT),
            new Operator("*",   (a, b) -> a * b, 3, Operator.Order.LEFT),
            new Operator("/",   (a, b) -> a / b, 3, Operator.Order.LEFT),
            new Operator("mod", (a, b) -> a % b, 3, Operator.Order.LEFT),
            new Operator("^",   (a, b) -> Math.pow(a, b), 4, Operator.Order.RIGHT),

            // If Statements
            new TrinaryFunction("ifelse", (cond, a, b) -> (DoubleToBool(cond)) ? a : b),
            new BinaryFunction("if", (cond, a) -> (DoubleToBool(cond)) ? a : 0.0),

            // Constants
            new Constant("pi",    3.14159265358979323846264338327950288419716939937510),
            new Constant("e",     2.71828182845904523536028747135266249775724709369995),
            new Constant("phi",   1.61803398874989484820458683436563811772030917980576),
            new Constant("true",  1.0),
            new Constant("false", 0.0),

            // Operators
            new Operator("and", (a, b) -> BoolToDouble(DoubleToBool(a) && DoubleToBool(b)), 0, Operator.Order.LEFT),
            new Operator("or",  (a, b) -> BoolToDouble(DoubleToBool(a) || DoubleToBool(b)), 0, Operator.Order.LEFT),
            new Operator("xor", (a, b) -> BoolToDouble(DoubleToBool(a) != DoubleToBool(b)), 0, Operator.Order.LEFT),
            new UnaryFunction("not", (a) -> BoolToDouble(!DoubleToBool(a))),

            new Operator("==", (a, b) -> BoolToDouble(a == b), 1, Operator.Order.LEFT),
            new Operator("!=", (a, b) -> BoolToDouble(a != b), 1, Operator.Order.LEFT),
            new Operator("<=", (a, b) -> BoolToDouble(a <= b), 1, Operator.Order.LEFT),
            new Operator(">=", (a, b) -> BoolToDouble(a >= b), 1, Operator.Order.LEFT),
            new Operator("<",  (a, b) -> BoolToDouble(a <  b), 1, Operator.Order.LEFT),
            new Operator(">",  (a, b) -> BoolToDouble(a >  b), 1, Operator.Order.LEFT),

            // Functions
            new UnaryFunction("%",      (in) -> in / 100.0),
            new UnaryFunction("abs",    (in) -> Math.abs(in)),
            new UnaryFunction("n",      (in) -> -in),
            new UnaryFunction("sqrt",   (in) -> Math.sqrt(in)),
            new UnaryFunction("cbrt",   (in) -> Math.cbrt(in)),
            new UnaryFunction("sin",    (in) -> Math.sin(in)),
            new UnaryFunction("cos",    (in) -> Math.cos(in)),
            new UnaryFunction("tan",    (in) -> Math.tan(in)),
            new UnaryFunction("ln",     (in) -> Math.log(in)),
            new UnaryFunction("log10",  (in) -> Math.log10(in)),
            new UnaryFunction("log",    (in) -> Math.log(in)),
            new UnaryFunction("signum", (in) -> (0 < in) ? 1.0 : -1.0),
            new UnaryFunction("bool",   (in) -> BoolToDouble(DoubleToBool(in))),
            new UnaryFunction("round",  (in) -> Math.round(in)),
            new UnaryFunction("int",    (in) -> Math.floor(in)),
            new UnaryFunction("floor",  (in) -> Math.floor(in)),
            new UnaryFunction("ceil",   (in) -> Math.ceil(in)),

            // Binary Functions
            new BinaryFunction("max", (a, b) -> Math.max(a, b)),
            new BinaryFunction("min", (a, b) -> Math.min(a, b)),
            new BinaryFunction("add", (a, b) -> a + b),
            new BinaryFunction("sub", (a, b) -> a - b),
            new BinaryFunction("mul", (a, b) -> a * b),
            new BinaryFunction("div", (a, b) -> a / b),
            new BinaryFunction("pow", (a, b) -> Math.pow(a, b))
        };
}