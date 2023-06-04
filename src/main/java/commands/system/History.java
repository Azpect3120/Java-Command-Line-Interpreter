package commands.system;

import java.util.ArrayList;

public class History {
    private static ArrayList<String> commandHistory = new ArrayList<>();

    public static void addToHistory (String keyword, ArrayList<String> args) {
        String command = keyword + " " + String.join(" ", args);
        commandHistory.add(command);
    }

    public static void history (ArrayList<String> args) {
        for (String command : commandHistory) {
            System.out.println("<history> " + command);
        }
    }

}
