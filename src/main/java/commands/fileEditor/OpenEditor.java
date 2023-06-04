package commands.fileEditor;
import commands.CurrentPath;
import userInterface.Input;

import java.io.File;
import java.util.ArrayList;

public class OpenEditor {
    public static void editor (ArrayList<String> args) {
        if (new File(CurrentPath.getPath()).isDirectory()) {
            System.out.println("Directories cannot be opened with the file editor");
            return;
        }

        Input.runCMD("<editor> ", true);
    }

}
