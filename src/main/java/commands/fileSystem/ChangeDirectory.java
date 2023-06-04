package commands.fileSystem;

import commands.CurrentPath;

import java.io.File;
import java.util.ArrayList;

public class ChangeDirectory {
    public static void cd (ArrayList<String> args) {
        if (args.isEmpty()) {
            System.out.println("Please provide proper arguments");
            return;
        }

        String path = CurrentPath.getPath();

        // Change to parent
        if (args.get(0).equals("..")) {
            if (path.split("\\\\").length == 1) {
                System.out.println("No parent directory was found");
            } else {
                String newPath = "";
                String[] split = path.split("\\\\");
                for(int i = 0; i < split.length - 1; i++) {
                    if (i != split.length-1) {
                        newPath += split[i] + "\\";
                    } else {
                        newPath += split[i];
                    }
                }

                CurrentPath.setPath(newPath);
            }
        // Change to child
        } else {
            String child = String.join(" ", args);
            String newPath = path + child + "\\";

            if (new File(newPath).exists()) {
                CurrentPath.setPath(newPath);
            } else {
                System.out.println("Child directory does not exist");
            }
        }
    }
}
