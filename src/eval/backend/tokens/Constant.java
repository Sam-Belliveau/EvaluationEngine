package eval.backend.tokens;

import eval.backend.EvalState;
import eval.backend.Token;

public class Constant extends Token {

    private double mValue;

    public Constant(String id, double value) {
        super(id);
        mValue = value;
    }

    public void eval(EvalState state) {
        state.getNStack().push(mValue);
    }
}