package commands.fileSystem;

import commands.Path;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Open {
    private static void open (ArrayList<String> args) {
        File targetPath;

        if (args.isEmpty()) {
            targetPath = new File(Path.getPath());
        } else {
            targetPath = new File(Path.getPath() + String.join(" ", args));
        }

        if (targetPath.exists()) {
            try {
                Desktop.getDesktop().open(targetPath);
            } catch (IOException e) {
                System.out.println("Unable to open file or directory");
            }
        } else {
            System.out.println("File or directory was not found");
        }
    }

}
