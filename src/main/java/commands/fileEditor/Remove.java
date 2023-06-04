package commands.fileEditor;

import commands.CurrentPath;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Remove {
    public static void remove (ArrayList<String> args) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(CurrentPath.getPath()));

            int targetLine;
            try {
                targetLine = Integer.parseInt(args.get(0)) - 1;
            } catch (java.lang.NumberFormatException | java.lang.IndexOutOfBoundsException e) {
                System.out.println("Invalid line number");
                return;
            }

            if (targetLine > lines.size() - 1) {
                System.out.println("Invalid line number");
                return;
            }

            FileWriter clear = new FileWriter(CurrentPath.getPath());
            clear.close();

            BufferedWriter writer = new BufferedWriter(new FileWriter(CurrentPath.getPath(), true));

            for (int i = 0; i < lines.size(); i++) {
                if (i != targetLine) {
                    writer.write(lines.get(i));
                    if (i != lines.size() - 1) {
                        writer.newLine();
                    }
                }
            }

            writer.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
