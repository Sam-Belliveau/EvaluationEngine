package eval.backend;

import java.util.Stack;

import eval.backend.Token;
import eval.backend.TokenDatabase;

public class EvalState {

    /**
     * The stacks that hold the numbers and pending operations
     */
    private Stack<Double> mNStack;
    private Stack<Token> mTStack;

    /**
     * Allows for the tokens to access the state stacks when evaluating
     */
    public Stack<Double> getNStack() {
        return mNStack;
    }

    public Stack<Token> getTStack() {
        return mTStack;
    }

    /**
     * Reset stacks so that they're empty
     */
    private void reset() {
        mNStack = new Stack<Double>();
        mTStack = new Stack<Token>();
    }

    /**
     * Create new state when constructed
     */
    public EvalState() {
        reset();
    }

    /**
     * This is called after all the text is added. It cleans up the token stack and
     * does checks on the number stack for errors.
     */
    public double getResult() {
        // While there are still operations 
        // to do, do the operations
        while (!mTStack.empty()) {
            mTStack.pop().eval(this);
        }

        // If there is more or less than one 
        // number left then throw an error
        if (mNStack.size() != 1) {
            if (mNStack.size() < 1) {
                throw new EvaluationError("No Numbers Given");
            } else {
                throw new EvaluationError("More Numbers than Operators");
            }
        }

        // Return the last number on the number stack (answer)
        return mNStack.peek();
    }

    /**
     * Check if that character is a digit or decimal
     */
    public static boolean isNumber(char i) {
        return (Character.isDigit(i)) || (i == '.');
    }

    /**
     * Take in a string and split it into numbers and
     * tokens so that it can be evaluated.
     * 
     * It is a modified version of reverse polish notation
     */
    public void parse(String input) {
        // Shorthand for the length of the string
        int max = input.length();

        // Loop over string
        for (int i = 0; i < max; ++i) {
            // If character is a number, then interpret it as a number
            if (isNumber(input.charAt(i))) {
                // Buffer to hold the number
                String number = "";

                // While the characters are numbers, add it to the buffer
                while (i < max && isNumber(input.charAt(i))) {
                    number += input.charAt(i++);
                }

                // Move i back by one after evaluating number
                --i;

                try {
                    // Add the number to the number stack
                    mNStack.push(Double.parseDouble(number));
                } catch (NumberFormatException e) {
                    throw new EvaluationError("Error parsing number \"" + number + "\""); 
                }
            } else {
                // Boolean used to check if any token was found
                boolean found = false;

                // Loop over token database for the operation
                for (Token t : TokenDatabase.kTokenList) {
                    // Shorthand for the id of the token
                    String id = t.getID();

                    // If this part of the input matches the id
                    if (input.substring(i, Math.min(max, i + id.length())).equals(id)) {
                        // Add to token stack with special properties
                        t.add(this);

                        // Skip over the rest of the token if its a word
                        i += id.length() - 1;

                        // Indicate that the token was found and break
                        found = true;
                        break;
                    }
                }

                // If no token was found, throw error describing the token it did not find
                if(!found) {
                    throw new EvaluationError("Unknown Operator: " + input.substring(i, Math.min(max, i + 3)) + "...");
                }
            }
        }
    }
}