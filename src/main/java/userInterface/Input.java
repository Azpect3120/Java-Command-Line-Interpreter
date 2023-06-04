package userInterface;
import interpreter.Interpreter;

import java.util.Scanner;
import java.util.ArrayList;

public class Input {
     private static Scanner scanner = new Scanner(System.in);

    // Used to recursively run the command line
    public static void runCMD () {
        System.out.print("<command> ");
        String userInput = scanner.nextLine();

        if (!userInput.equals("")) {
            Interpreter.interpreter(userInput);
        }

        runCMD();
    }
}
