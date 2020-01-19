package eval.backend;

import eval.backend.EvalState;
import eval.backend.Token;

public class EvaluationError extends RuntimeException {

    private static final long serialVersionUID = -8471402012417580774L;

    public static void check(EvalState state, Token operation, int expected) throws EvaluationError {
        if (state.getNStack().size() < expected) {
            throw new EvaluationError(operation.getID(), expected, state.getNStack().size());
        }
    }

    public EvaluationError(String id, int expected, int recieved) {
        this(id + " -> Expected " + expected + " Number(s), Recieved " + recieved);
    }
    
    public EvaluationError(String error) {
        super("Invalid Syntax! [" + error + "]");
    }

}