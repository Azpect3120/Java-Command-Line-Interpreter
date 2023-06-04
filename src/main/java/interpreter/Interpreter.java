package interpreter;
import java.util.ArrayList;

public class Interpreter {

    // Creates an array where there first value holds the keyword and second value holds the arguments
    public static void interpreter (String inputString) {
        String keyword = null;
        ArrayList<String> args = new ArrayList<>();

        String[] inputSplit = inputString.split(" ");

        for (int i = 0; i < inputSplit.length; i++) {
            if (i == 0) {
                keyword = inputSplit[i];
            } else {
                args.add(inputSplit[i]);
            }
        }
        runCommand(keyword, args);
    }

    // Runs commands with the given arguments
    // Reflection example
    private static void runCommand (String keyword, ArrayList<String> args) {

    }
}
