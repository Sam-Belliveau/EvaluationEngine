package eval.backend.tokens;

import eval.backend.EvalState;
import eval.backend.Token;
import eval.backend.EvaluationError;

/**
 * This is a function that takes in one input.
 * 
 * Examples of this are sqrt, cbrt, log, sin, cos, etc...
 */
public class UnaryFunction extends Token {

    /**
     * Interface used to pass and store a lambda
     */
    public interface UnaryLambda {
        public double exec(double a);
    }

    /**
     * Operation that this class uses when evaluating
     */
    private UnaryLambda mOperation;

    /**
     * Standard constructor for token, but with operation parameter
     */
    public UnaryFunction(String id, UnaryLambda operation) {
        super(id);
        mOperation = operation;
    }

    public void eval(EvalState state) {
        // Check for issues with the number stack
        EvaluationError.check(state, this, 1);

        // Pass items from the number stack to the function
        // then add the result back to the number stack
        double a = state.getNStack().pop();
        state.getNStack().push(mOperation.exec(a));
    }
}