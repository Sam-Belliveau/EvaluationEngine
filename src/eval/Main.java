package eval;

import java.util.Scanner;

import eval.Eval;
import eval.backend.EvaluationError;
import eval.backend.Token;
import eval.backend.TokenDatabase;

/**
 * This is the main class of the project. This will contain very little of the
 * program and will instead work as an example frontend the the evaluation
 * engine that I have created.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Eval Shell : Sam Belliveau\n");

        System.out.println("When Returning Conditions: \n");
        System.out.println("\tTrue = 1.0");
        System.out.println("\tFalse = 0.0\n");
        
        System.out.println("Use '?' to get a list of operators");
        System.out.println("Use 'exit' to quit the program");

        Scanner shellin = new Scanner(System.in);

        boolean running = true;
        while(running) {
            System.out.print("\nEval Shell>");
            String line = shellin.nextLine().strip();
            
            if(line.equals("")) {}

            else if(line.equals("?")) {
                System.out.println("Operators: ");
                for(Token a : TokenDatabase.kTokenList) {
                    System.out.print(a.getID() + "\t");
                }
            }
            
            else if(line.equals("exit")) {
                System.out.println("Exiting...");
                running = false;
            } 
            
            else {
                try {
                    System.out.println(Eval.eval(line));
                } catch(EvaluationError error) {
                    System.out.println(error.getMessage());
                }
            }
        }

        shellin.close();
    }
}