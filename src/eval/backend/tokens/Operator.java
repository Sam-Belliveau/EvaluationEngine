package eval.backend.tokens;

import eval.backend.EvalState;
import eval.backend.Token;
import eval.backend.tokens.Bracket;
import eval.backend.tokens.BinaryFunction;

/**
 * The operator class is basically a binary function that includes order of
 * operations. So unlike normal binary functions that just take two numbers like
 * max(a, b), operators have order, like 15 / 2 + 3.5
 */
public class Operator extends BinaryFunction {

    /**
     * Which way operators with the same priority evaluate. Most use LEFT, but there
     * are a few like ^ that use RIGHT
     */
    public enum Order {
        LEFT, RIGHT
    };

    /**
     * The Order and Priority values used for order of operations
     */
    private Order mOrder;
    private int mPriority;

    /**
     * Provides all information that is needed for an Operator
     * 
     * Because this extends BinaryFunction, we do not need to reimplement the .eval
     * function
     */
    public Operator(String id, BinaryLambda operation, int priority, Order order) {
        super(id, operation);
        mOrder = order;
        mPriority = priority;
    }

    /**
     * Getter functions for evaluating order
     */
    public int getPriority() {
        return mPriority;
    }

    public Order getOrder() {
        return mOrder;
    }

    /**
     * This function is used to check if the token on the top of the token stack
     * needs to be evaluated before this token is added.
     *
     * For example: 3 * 4 + 3
     *
     * The * needs to evaluate the 3 and 4 before the + is added
     */
    private boolean isBlocking(Token other) {
        // If the other token is an operator
        // Check the order of operation variables
        if (other instanceof Operator) {
            Operator operator = (Operator) other;

            if (getPriority() < operator.getPriority()) {
                return true;
            } else if (getPriority() == operator.getPriority()) {
                return operator.getOrder() == Order.LEFT;
            } else {
                return false;
            }
        }

        // Otherwise, everything needs to be evaluated before an operator
        // Except for a left bracket, which acts like a wall preventing
        // order of operations. [right brackets never get added to the token stack]
        return !(other instanceof Bracket);
    }

    public void add(EvalState state) {
        // While the token is being blocked, evaluate the blocking token
        // This is how the order of operations is implemented
        while (!state.getTStack().empty() && isBlocking(state.getTStack().peek())) {
            state.getTStack().pop().eval(state);
        }

        // Finally add the token to the stack
        state.getTStack().push(this);
    }

}