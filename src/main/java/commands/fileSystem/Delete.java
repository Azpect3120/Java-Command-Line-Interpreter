package commands.fileSystem;

import commands.Path;
import java.io.File;
import java.util.ArrayList;

public class Delete {
    public static void delete (ArrayList<String> args) {
        File targetPath;

        if (args.isEmpty()) {
            targetPath = new File(Path.getPath());
        } else {
            targetPath = new File(Path.getPath() + String.join(" ", args));
        }

        if (targetPath.exists()) {
            // Recursive helper function to delete the sub paths
            deleteSubPath(targetPath);
        } else {
            System.out.println("File or directory does not exist");
        }

        // If args aren't provided, current file was deleted and needs to traverse back to the parent
        if (args.isEmpty()) {
            ArrayList<String> newArgs = new ArrayList<>();
            newArgs.add("..");

            ChangeDirectory.cd(newArgs);
        }
    }

    // Recursive helper function to delete sub paths from a directory
    private static void deleteSubPath (File path) {
        File[] subPaths = path.listFiles();

        if (subPaths != null) {
            for (File subPath : subPaths) {
                if (subPath.isDirectory()) {
                    deleteSubPath(subPath);
                } else {
                    subPath.delete();
                }
            }
        }
        path.delete();
    }
}
