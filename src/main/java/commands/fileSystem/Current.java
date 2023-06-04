package commands.fileSystem;

import commands.CurrentPath;
import java.util.ArrayList;

public class Current {
    public static void cur (ArrayList<String> args) {
        System.out.println("<path> " + CurrentPath.getPath());
    }
}
