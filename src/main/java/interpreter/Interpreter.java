package interpreter;
import commands.fileEditor.*;
import commands.fileSystem.*;
import commands.system.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.lang.reflect.Method;

public class Interpreter {

    // Creates an array where there first value holds the keyword and second value holds the arguments
    public static void interpreter (String inputString) {
        String keyword = null;
        ArrayList<String> args = new ArrayList<>();

        String[] inputSplit = inputString.split(" ");

        for (int i = 0; i < inputSplit.length; i++) {
            if (i == 0) {
                keyword = inputSplit[i];
            } else {
                args.add(inputSplit[i]);
            }
        }
        runCommand(keyword, args);
    }

    // Runs commands with the given arguments
    // Reflection example
    private static void runCommand (String keyword, ArrayList<String> args) {
        Class className = findClassName(keyword);

        if (className != null) {
            Method[] methods = className.getDeclaredMethods();

            for (Method method : methods) {
                if (keyword.equals(method.getName())) {
                    method.setAccessible(true);
                    try {
                        method.invoke(null, args);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            System.out.println("Unknown command");
        }
    }

    // Finds the classname that contains a method name
    private static Class findClassName (String keyword) {
        Class[] classes = {
                ChangeDirectory.class, Create.class, Current.class, Delete.class, ListDirectories.class, ListDisks.class, Open.class, Rename.class, SwitchDisk.class,
                Append.class, Insert.class, OpenEditor.class, Preview.class, Remove.class, Wipe.class,
                Clear.class, Help.class, History.class
        };

        for (int i = 0; i < classes.length; i++) {
            Method[] methods = classes[i].getDeclaredMethods();
            for (Method method : methods) {
                if (method.getName().equals(keyword)) {
                    return classes[i];
                }
            }
        }
        return null;
    }
}
