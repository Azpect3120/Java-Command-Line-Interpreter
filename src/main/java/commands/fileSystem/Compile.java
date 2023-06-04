package commands.fileSystem;

import commands.Path;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;
import java.util.ArrayList;

public class Compile {
    public static void compile (ArrayList<String> args) {
        File targetPath;

        if (args.isEmpty()) {
            targetPath = new File(Path.getPath());
        } else {
            targetPath = new File(Path.getPath() + String.join(" ", args));
        }

        if (targetPath.exists()) {
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            int result = compiler.run(null, null, null, targetPath.toString());

            if (result == 0) {
                System.out.println("Successful compilation");
            } else {
                System.out.println("Compilation error");
            }
        } else {
            System.out.println("File or directory does not exist");
        }
    }
}
