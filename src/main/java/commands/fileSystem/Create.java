package commands.fileSystem;
import commands.CurrentPath;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Create {
    public static void create (ArrayList<String> args) {
        if (args.isEmpty()) {
            System.out.println("Please provide proper arguments");
            return;
        }

        // Break creation into processes to allow for creation of full paths
        String newName = String.join(" ", args);
        String[] processes = newName.split("[\\\\/]");

        String curPath = CurrentPath.getPath();

        // Complete processes
        for (int i = 0; i < processes.length; i++) {
            File newPath = new File(curPath + processes[i]);

            String pathName = newPath.getName();

            int dotIndex = pathName.lastIndexOf('.');
            boolean hasExt = dotIndex > 0 && dotIndex < newName.length() - 1;

            if (hasExt) {
                try {
                    newPath.createNewFile();
                } catch (IOException e) {
                    System.out.println(e);
                }
            } else {
                newPath.mkdirs();
            }
            curPath += processes[i] + "\\\\";
        }
    }
}
