package eval.backend;

import java.util.regex.Pattern;
import java.util.Scanner;
import java.util.Stack;

import eval.backend.Token;
import eval.backend.TokenDatabase;

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
        Scanner command = new Scanner(input);
        command.useDelimiter("");

        while(command.hasNext()) {
            if(command.hasNextDouble()) {
                mNStack.push(command.nextDouble());
                System.out.println(mNStack.peek());
            } else {
                boolean found = false;
                for(Token t : TokenDatabase.kTokenList) {
                    String pattern = Pattern.quote(t.getID());
                    if(command.hasNext(pattern)) {
                        command.next(pattern);
                        t.add(this);
                        System.out.println(t.getID());
                        found = true;
                        break;
                    }
                }

                if(!found) {
                    throw new EvaluationError("Unknown Character \"" + command.next() + "\"");
                }
            }
        }

        command.close();
    }
}