package commands.system;

import java.io.IOException;
import java.util.ArrayList;

public class Clear {
    private static final String os = System.getProperty("os.name");

    public static void clear (ArrayList<String> args) {
        try {
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("bash", "-c", "clear").inheritIO().start().waitFor();
            }

        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
