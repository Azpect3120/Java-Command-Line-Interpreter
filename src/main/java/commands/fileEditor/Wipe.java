package commands.fileEditor;

import commands.CurrentPath;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Wipe {
    public static void wipe (ArrayList<String> args) {
        try {
            FileWriter clear = new FileWriter(CurrentPath.getPath());
            clear.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
