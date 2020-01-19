package eval.backend.tokens;

import eval.backend.EvalState;
import eval.backend.Token;
import eval.backend.EvaluationError;

public class BinaryFunction extends Token {

    /**
     * Interface used to pass and store a lambda
     */
    public interface BinaryLambda {
        public double exec(double a, double b);
    }

    /**
     * Operation that this class uses when evaluating
     */
    private BinaryLambda mOperation;

    /**
     * Standard constructor for token, but with operation parameter
     */
    public BinaryFunction(String id, BinaryLambda operation) {
        super(id);
        mOperation = operation;
    }

    public void eval(EvalState state) {
        // Check for issues with the number stack
        EvaluationError.check(state, this, 2);
        
        // Pass items from the number stack to the function
        // then add the result back to the number stack
        double b = state.getNStack().pop();
        double a = state.getNStack().pop();
        state.getNStack().push(mOperation.exec(a, b));
    }
}