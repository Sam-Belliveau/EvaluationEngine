package eval.backend.tokens;

import eval.backend.EvalState;
import eval.backend.Token;
import eval.backend.EvaluationError;

public class UnaryFunction extends Token {

    public interface UnaryLambda {
        public double exec(double a);
    }

    private UnaryLambda mOperation;

    public UnaryFunction(String id, UnaryLambda operation) {
        super(id);
        mOperation = operation;
    }

    public void eval(EvalState state) {
        EvaluationError.check(state, this, 1);

        double a = state.getNStack().pop();
        state.getNStack().push(mOperation.exec(a));
    }
}