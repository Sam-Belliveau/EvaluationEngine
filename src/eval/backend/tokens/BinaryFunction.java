package eval.backend.tokens;

import eval.backend.EvalState;
import eval.backend.Token;
import eval.backend.EvaluationError;

public class BinaryFunction extends Token {

    public interface BinaryLambda {
        public double exec(double a, double b);
    }

    private BinaryLambda mOperation;

    public BinaryFunction(String id, BinaryLambda operation) {
        super(id);
        mOperation = operation;
    }

    public void eval(EvalState state) {
        EvaluationError.check(state, this, 2);
        
        double b = state.getNStack().pop();
        double a = state.getNStack().pop();
        state.getNStack().push(mOperation.exec(a, b));
    }
}