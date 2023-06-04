package commands.fileSystem;

import java.util.ArrayList;

public class ChangeDirectory {

    public static void cd (ArrayList<String> args) {
        System.out.println("Changed directories!");
        System.out.println(args);
    }
}
