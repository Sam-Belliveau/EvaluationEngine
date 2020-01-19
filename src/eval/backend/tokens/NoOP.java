package eval.backend.tokens;

import eval.backend.EvalState;
import eval.backend.Token;

/**
 * This class is used for characters that are not supposed to do anything These
 * operators include " " and ","
 *
 * Mr Brooks was confused why the "," operator was included, but it basically
 * allows for the user to type add(3, 4)
 *
 * It is really dumb, but it is useful none the less
 *
 * \/ add(3, 4) 
 * \/ add(3 4) 
 * \/ add 3 4 
 * == 7
 */
public class NoOP extends Token {

    public NoOP(String id) {
        super(id);
    }

    public void exec(EvalState state) {
    }

    public void add(EvalState state) {
    }
}