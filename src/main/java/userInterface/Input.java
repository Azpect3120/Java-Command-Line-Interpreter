package userInterface;
import interpreter.Interpreter;

import java.util.Scanner;

public class Input {
     private static Scanner scanner = new Scanner(System.in);

    // Used to recursively run the command line
    public static void runCMD () {
        System.out.print("<command> ");
        String userInput = scanner.nextLine();

        if (!userInput.equals("") && !userInput.toLowerCase().equals("exit")) {
            Interpreter.interpreter(userInput);
        }

        if (!userInput.toLowerCase().equals("exit")) {
            runCMD();
        }
    }
}
