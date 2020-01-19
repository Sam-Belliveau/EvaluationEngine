package eval.backend.tokens;

import eval.backend.EvalState;
import eval.backend.Token;

/**
 * Constants are one of the most simple types of tokens, all they do is add
 * themself to the number stack.
 * 
 * They should NEVER be added to the token stack, but in the event that they
 * are, it shouldn't cause many issues.
 * 
 * In the C++ version of this project, this was used to store variables.
 */
public class Constant extends Token {

    private double mValue;

    public Constant(String id, double value) {
        super(id);
        mValue = value;
    }

    public void eval(EvalState state) {
        state.getNStack().push(mValue);
    }

    public void add(EvalState state) {
        eval(state);
    }
}