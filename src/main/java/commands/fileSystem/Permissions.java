package commands.fileSystem;

import commands.CurrentPath;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.AclEntry;
import java.nio.file.attribute.AclFileAttributeView;
import java.nio.file.attribute.UserPrincipal;
import java.util.ArrayList;
import java.util.List;

public class Permissions {
    public static void permissions (ArrayList<String> args) {
        String pathString;
        if (args.isEmpty()) {
            pathString = CurrentPath.getPath();
        } else {
            pathString = CurrentPath.getPath() + String.join(" ", args);
        }

        try {
            AclFileAttributeView aclView = Files.getFileAttributeView(Path.of(pathString), AclFileAttributeView.class);
            List<AclEntry> aclEntries = aclView.getAcl();

            for (AclEntry entry : aclEntries) {
                UserPrincipal principal = entry.principal();
                System.out.println("User: " + principal.getName());
                System.out.println("Permissions: " + entry.permissions());
                System.out.println("Type: " + entry.type());
                System.out.println("Flags: " + entry.flags());
                System.out.println("-----");
            }
        } catch (Exception e) {
            System.out.println("An error occurred while retrieving file permissions: " + e.getMessage());
        }
    }

}
