package eval.backend.tokens;

import eval.backend.EvalState;
import eval.backend.Token;
import eval.backend.tokens.Bracket;
import eval.backend.tokens.BinaryFunction;

public class Operator extends BinaryFunction {

    public enum Order { LEFT, RIGHT };

    private Order mOrder;
    private int mPriority;

    public Operator(String id, BinaryLambda operation, int priority, Order order) {
        super(id, operation);
        mOrder = order;
        mPriority = priority;
    }

    public int getPriority() {
        return mPriority;
    }

    public Order getOrder() {
        return mOrder;
    }

    private boolean isBlocking(Token other) {
        if (other instanceof Operator) {
            Operator operator = (Operator)other;
            if(getPriority() < operator.getPriority()) {
                return true;
            } else if (getPriority() == operator.getPriority()) {
                return operator.getOrder() == Order.LEFT;
            } else {
                return false;
            }
        }

        return (other instanceof Bracket);
    }

    public void add(EvalState state) {
        while(!state.getTStack().empty() && isBlocking(state.getTStack().peek())) {
            state.getTStack().pop().eval(state);
        }

        state.getTStack().push(this);
    }

}