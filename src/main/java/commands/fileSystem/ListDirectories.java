package commands.fileSystem;

import commands.Path;

import java.io.File;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

public class ListDirectories {

    public static void list (ArrayList<String> args) {
        String path = Path.getPath();
        try {
            File[] dirs = new File(path).listFiles();

            if (dirs.length != 0) {
                for(File dir : dirs) {
                    String[] split = dir.toString().split("\\\\");

                    Instant instant = Instant.ofEpochMilli(dir.lastModified());
                    LocalDateTime timestamp = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());

                    String date = timestamp.toString().split("T")[0];
                    String time = timestamp.toString().split("T")[1];

                    date = date.split("-")[1] + "/" + date.split("-")[2] + "/" + date.split("-")[0];
                    time = time.split(":")[0] + ":" + time.split(":")[1];

                    System.out.printf("%-6s %-10s %-10s %-50s%n", "<dir>", date, time, split[split.length-1]);
                }
            } else {
                System.out.println("No directories found");
            }
        } catch (java.lang.NullPointerException e) {
            System.out.println("No directories found");
        }
    }
}
