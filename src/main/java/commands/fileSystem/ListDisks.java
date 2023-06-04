package commands.fileSystem;

import java.io.File;
import java.util.ArrayList;

public class ListDisks {
    public static void disks (ArrayList<String> args) {
        File[] roots = File.listRoots();

        for (File root : roots) {
            System.out.println("<disk> " + root);
        }

    }
}
