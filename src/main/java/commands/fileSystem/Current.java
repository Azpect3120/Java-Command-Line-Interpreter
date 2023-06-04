package commands.fileSystem;

import commands.Path;
import java.util.ArrayList;

public class Current {
    public static void cur (ArrayList<String> args) {
        System.out.println("<path> " + Path.getPath());
    }
}
