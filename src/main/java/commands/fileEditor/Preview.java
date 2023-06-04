package commands.fileEditor;

import commands.Path;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Preview {
    public static void preview (ArrayList<String> args) {
        // Success case
        try {
            // List of each line in the file
            List<String> lines = Files.readAllLines(Paths.get(Path.getPath()));

            // Print each line
            // MUST BE CHANGED WHEN OUTPUT TYPE CHANGES ARE MADE
            for (int i = 0; i < lines.size(); i++) {
                System.out.printf("%-5d %s%n", i+1, lines.get(i));
            }

            // Error handling
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
