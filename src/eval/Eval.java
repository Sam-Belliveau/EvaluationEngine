package eval;

import eval.backend.EvalState;

public class Eval {
    /**
     * Function that acts like an interface for the EvalState class
     */
    public static double eval(String input) {
        EvalState state = new EvalState();
        state.parse(input);
        return state.getResult();
    }
}