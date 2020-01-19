package eval.backend;

import eval.backend.EvalState;

public class Token {

    /**
     * The name of the token used for parsing the user string
     */
    private String mID;

    /**
     * Create token that does nothing but hold the id
     */
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
        // Most tokens override this, but I left it blank to simplify tokens like
        // NoOP and the Bracket tokens
    }

    public void add(EvalState state) {
        // Most types of tokens are just added straight to the stack
        state.getTStack().push(this);
    }
}