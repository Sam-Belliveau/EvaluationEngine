package eval.backend.tokens;

import eval.backend.EvalState;
import eval.backend.Token;

/**
 * Brackets are used to circumvent order of operations by hijacking the evaluation process.
 * 
 * This is basically done like this:
 * - Left bracket is added to token stack
 *      - Left brackets can not be evaluated by anything other than a right bracket
 * - When the right bracket comes, it evaluates everything until it sees a left bracket
 * - When this happens, everything inside the brackets is evaluated
 * 
 * There is another type of Bracket which I created called a Flush. A flush basically
 * is like the right bracket except it doesnt remove the left semicolon. This could be
 * used like max(3 + 5, 3 * 5) or min(7 * 3; 2 + 3). 
 */
public class Bracket extends Token {

    /**
     * Enum used to store which type of bracket it is
     */
    public enum BracketType { LEFT, RIGHT, FLUSH };

    /**
     * Which type of bracket the class is
     */
    private BracketType mType;

    /**
     * Simple constuctor for this bracket type
     */
    public Bracket(String id, BracketType type) {
        super(id);
        mType = type;
    }

    public void add(EvalState state) {
        // If it is a left bracket, it is immediately added
        if(mType == BracketType.LEFT) {
            state.getTStack().push(this);
        }

        // If it is a right bracket, the process is a little more complicated
        else {
            while(!state.getTStack().empty()) {
                // If the token is a left bracket [right brackets are never added to stack]
                // Then remove it and exit the loop.
                if(state.getTStack().peek() instanceof Bracket) {
                    // The flush bracket does not remove the bracket
                    if(mType != BracketType.FLUSH) {
                        state.getTStack().pop();
                    }
                    break;
                }

                // Otherwise, evaluate the token that it finds.
                else {
                    state.getTStack().pop().eval(state);
                }
            }
        }
    }
}