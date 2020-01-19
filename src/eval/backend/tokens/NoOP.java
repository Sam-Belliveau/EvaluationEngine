package eval.backend.tokens;

import eval.backend.EvalState;
import eval.backend.Token;

public class NoOP extends Token {
    
    public NoOP(String id) {
        super(id);
    }

    public void exec(EvalState state) {}
    public void add(EvalState state) {}
}