package userInterface;
import interpreter.Interpreter;

import java.util.Scanner;

public class Input {
     private static Scanner scanner = new Scanner(System.in);

    // Used to recursively run the command line
    public static void runCMD (String prompt, boolean inEditor) {
        System.out.print(prompt);
        String userInput = scanner.nextLine();

        if (!userInput.equals("") && !userInput.toLowerCase().equals("exit")) {
            Interpreter.interpreter(userInput, inEditor);
        }

        if (!userInput.toLowerCase().equals("exit")) {
            runCMD(prompt, inEditor);
        }
    }
}
