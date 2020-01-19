package eval.backend.tokens;

import eval.backend.EvalState;
import eval.backend.Token;

public class Bracket extends Token {

    public enum BracketType { LEFT, RIGHT };

    private BracketType mType;

    Bracket(String id, BracketType type) {
        super(id);
        mType = type;
    }

    public void add(EvalState state) {
        if(mType == BracketType.LEFT) {
            state.getTStack().push(this);
        } else {
            while(!state.getTStack().empty()) {
                if(state.getTStack().peek() instanceof Bracket) {
                    state.getTStack().pop();
                    return;
                } else {
                    state.getTStack().pop().eval(state);
                }
            }
        }
    }
}