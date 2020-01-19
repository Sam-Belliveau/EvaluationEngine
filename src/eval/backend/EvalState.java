package eval.backend;

import java.util.Stack;

import eval.backend.Token;
import eval.backend.tokens.TokenDatabase;

public class EvalState {

    private Stack<Double> mNStack;
    private Stack<Token>  mTStack;

    public Stack<Double> getNStack() { return mNStack; }
    public Stack<Token>  getTStack() { return mTStack; }

    private void reset() {
        mNStack = new Stack<Double>();
        mTStack = new Stack<Token>();
    }

    public EvalState() {
        reset();
    }

    public double getResult() {
        while(!mTStack.empty()) {
            mTStack.pop().eval(this);
        }

        if(mNStack.size() != 1) {
            if(mNStack.size() < 1) {
                throw new EvaluationError("No Numbers Given");
            } else {
                throw new EvaluationError("More Numbers than Operators");
            }
        }

        return mNStack.peek();
    }

    public void parse(String input) {
        // TODO: parse string into stacks
    }
}