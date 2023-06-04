package commands.fileEditor;

import commands.Path;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Insert {
    public static void insert (ArrayList<String> args) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(Path.getPath()));

            int targetLine;
            try {
                targetLine = Integer.parseInt(args.get(0));
            } catch (java.lang.NumberFormatException | java.lang.IndexOutOfBoundsException e) {
                System.out.println("Invalid line number");
                return;
            }

            args.remove(0);

            if (targetLine < lines.size()) {
                lines.add(targetLine, String.join(" ", args));

                FileWriter clear = new FileWriter(Path.getPath());
                clear.close();

                BufferedWriter writer = new BufferedWriter(new FileWriter(Path.getPath()));

                for (int i = 0; i < lines.size(); i++) {
                    writer.write(lines.get(i));
                    if (i != lines.size() - 1) writer.newLine();
                }

                writer.close();
            } else {
                System.out.println("Invalid line number");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
