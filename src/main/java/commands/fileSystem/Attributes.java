package commands.fileSystem;

import commands.CurrentPath;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.AclEntry;
import java.nio.file.attribute.AclFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.UserPrincipal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Attributes {
    public static void attributes (ArrayList<String> args) {
        String pathString;
        if (args.isEmpty()) {
            pathString = CurrentPath.getPath();
        } else {
            pathString = CurrentPath.getPath() + String.join(" ", args);
        }

        try {
            BasicFileAttributes fileAttributes = Files.readAttributes(Path.of(pathString), BasicFileAttributes.class);

            String type;
            if (fileAttributes.isDirectory()) {
                type = "directory";
            } else if (fileAttributes.isRegularFile()) {
                type = "regular file";
            } else if (fileAttributes.isSymbolicLink()) {
                type = "symbolic link";
            } else if (fileAttributes.isOther()) {
                type = "other";
            } else {
                type = "unknown";
            }

            System.out.println("<creation> " + convertTime(String.valueOf(fileAttributes.creationTime())));
            System.out.println("<last modified> " + convertTime(String.valueOf(fileAttributes.lastModifiedTime())));
            System.out.println("<last accessed> " + convertTime(String.valueOf(fileAttributes.lastAccessTime())));
            System.out.println("<type> " + type);
            System.out.println("<size> " + fileAttributes.size() + " bytes");

        } catch (IOException e) {
            System.out.println("Unable to read attributes of the file or directory");
        }
    }

    // Converts inputted timestamp to a readable timestamp
    private static String convertTime (String dateTimeString) {
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");

        return dateTime.format(formatter);
    }
}
