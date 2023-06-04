package commands.fileEditor;

import commands.CurrentPath;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Preview {
    public static void preview (ArrayList<String> args) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(CurrentPath.getPath()));

            for (int i = 0; i < lines.size(); i++) {
                System.out.printf("%-5d %s%n", i+1, lines.get(i));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
