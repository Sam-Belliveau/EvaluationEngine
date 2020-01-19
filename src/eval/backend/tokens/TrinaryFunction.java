package eval.backend.tokens;

import eval.backend.EvalState;
import eval.backend.Token;
import eval.backend.EvaluationError;

public class TrinaryFunction extends Token {

    public interface TrinaryLambda {
        public double exec(double a, double b, double c);
    }

    private TrinaryLambda mOperation;

    public TrinaryFunction(String id, TrinaryLambda operation) {
        super(id);
        mOperation = operation;
    }

    public void eval(EvalState state) {
        EvaluationError.check(state, this, 2);
        
        double c = state.getNStack().pop();
        double b = state.getNStack().pop();
        double a = state.getNStack().pop();
        state.getNStack().push(mOperation.exec(a, b, c));
    }
}