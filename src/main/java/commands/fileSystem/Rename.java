package commands.fileSystem;

import commands.CurrentPath;

import java.io.File;
import java.util.ArrayList;

public class Rename {
    public static void rename (ArrayList<String> args) {
        File target = new File(CurrentPath.getPath());

        // CurrentPath is a disk
        if (CurrentPath.getPath().split("\\\\").length ==1) {
            System.out.println("Disks cannot be renamed");
        } else {
            if (target.exists()) {
                String[] subPaths = CurrentPath.getPath().split("\\\\");

                subPaths[subPaths.length - 1] = String.join(" ", args);

                File newPath = new File(String.join("\\", subPaths));

                if (newPath.exists()) {
                    System.out.println("File or directory already exists");
                } else {
                    target.renameTo(newPath);
                    CurrentPath.setPath(newPath.toString());
                }
            } else {
                System.out.println("File or directory does not exist");
            }
        }
    }
}
