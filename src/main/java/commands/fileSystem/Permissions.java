package commands.fileSystem;

import commands.CurrentPath;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.*;
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

            if (aclEntries.size() % 2 == 0) {
                for (int i = 0; i < aclEntries.size(); i += 2) {
                    UserPrincipal principal1 = aclEntries.get(i).principal();
                    UserPrincipal principal2 = aclEntries.get(i + 1).principal();

                    if (principal1.equals(principal2)) {
                        System.out.println("<user> " + principal1.getName().split("\\\\")[1]);
                        System.out.println("<permissions> " + aclEntries.get(i).permissions());
                        System.out.println("<type> " + aclEntries.get(i).type());
                        System.out.println("<flags> " + aclEntries.get(i + 1).flags());
                        System.out.println("");
                    }
                }
            } else {
                for (AclEntry entry : aclEntries) {
                    UserPrincipal principal = entry.principal();
                    System.out.println("<user> " + principal.getName().split("\\\\")[1]);
                    System.out.println("<permissions> " + entry.permissions());
                    System.out.println("<type> " + entry.type());
                    System.out.println("<flags> " + entry.flags());
                    System.out.println("");

                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred while retrieving file permissions: " + e.getMessage());
        }
    }

}
