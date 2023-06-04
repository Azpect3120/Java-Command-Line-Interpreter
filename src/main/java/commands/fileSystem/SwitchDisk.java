package commands.fileSystem;

import commands.Path;

import java.io.File;
import java.util.ArrayList;

public class SwitchDisk {
    public static void sd (ArrayList<String> args) {
        if (args.isEmpty()) {
            System.out.println("Please provide proper arguments");
            return;
        }

        char disk = args.toString().charAt(1);
        String newPath = disk + ":\\";

        if (new File(newPath).exists()) {
            Path.setPath(newPath);
        } else {
            System.out.println("Unknown disk");
        }
    }
}
