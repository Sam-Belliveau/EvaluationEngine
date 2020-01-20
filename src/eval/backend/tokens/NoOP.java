package eval.backend.tokens;

import eval.backend.EvalState;
import eval.backend.Token;

/**
 * This class is used for characters that are not supposed to do anything. These
 * operators only really include " " 
 */
public class NoOP extends Token {

    public NoOP(String id) {
        super(id);
    }

    /**
     * NoOP literally means No Operations
     * so thats exactly what it does here
     */
    public void exec(EvalState state) {
    }

    public void add(EvalState state) {
    }
}