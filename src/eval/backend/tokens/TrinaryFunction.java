package eval.backend.tokens;

import eval.backend.EvalState;
import eval.backend.Token;
import eval.backend.EvaluationError;

/**
 * This is a function that takes in 3 inputs.
 *
 * This is really only used for ifelse
 */
public class TrinaryFunction extends Token {

    /**
     * Interface used to pass and store a lambda
     */
    public interface TrinaryLambda {
        public double exec(double a, double b, double c);
    }

    /**
     * Operation that this class uses when evaluating
     */
    private TrinaryLambda mOperation;

    /**
     * Standard constructor for token, but with operation parameter
     */
    public TrinaryFunction(String id, TrinaryLambda operation) {
        super(id);
        mOperation = operation;
    }

    public void eval(EvalState state) {
        // Check for issues with the number stack
        EvaluationError.check(state, this, 3);

        // Pass items from the number stack to the function
        // then add the result back to the number stack
        double c = state.getNStack().pop();
        double b = state.getNStack().pop();
        double a = state.getNStack().pop();
        state.getNStack().push(mOperation.exec(a, b, c));
    }
}