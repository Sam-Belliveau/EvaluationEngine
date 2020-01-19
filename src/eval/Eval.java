package eval;

import eval.backend.EvalState;

public class Eval {
    public static double eval(String input) {
        EvalState state = new EvalState();
        state.parse(input);
        return state.getResult();
    }
}