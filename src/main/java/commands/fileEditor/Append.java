package commands.fileEditor;

import commands.Path;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Append {
    public static void append (ArrayList<String> args) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(Path.getPath(), true));

            writer.newLine();
            writer.write(String.join(" ", args));

            writer.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
