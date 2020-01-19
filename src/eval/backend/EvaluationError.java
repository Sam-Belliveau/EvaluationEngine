package eval.backend;

import eval.backend.EvalState;
import eval.backend.Token;

public class EvaluationError extends RuntimeException {

    /**
     * Serial version that I need for some reason
     */
    private static final long serialVersionUID = -8471402012417580774L;

    /**
     * This is a conveniet function used for checking if there are no numbers left
     * for the operator to use
     */
    public static void check(EvalState state, Token operation, int expected) throws EvaluationError {
        if (state.getNStack().size() < expected) {
            throw new EvaluationError(operation.getID(), expected, state.getNStack().size());
        }
    }

    /**
     * Short hand for the expected inputs
     */
    public EvaluationError(String id, int expected, int recieved) {
        this(id + " -> Expected " + expected + " Number(s), Recieved " + recieved);
    }

    /**
     * Custom error for the evaluation error
     */
    public EvaluationError(String error) {
        super("Invalid Syntax! [" + error + "]");
    }

}