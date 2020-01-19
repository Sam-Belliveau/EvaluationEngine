package eval.backend;

import eval.backend.EvalState;

public class Token {

    private String mID;

    public Token(String id) {
        mID = id;
    }

    public final String getID() {
        return mID;
    }

    /**
     * These functions could be abstract, but these are the most common operations
     * for these functions and are overridden in only certain situaions
     */
    public void eval(EvalState state) {
    }

    public void add(EvalState state) {
        state.getTStack().push(this);
    }
}