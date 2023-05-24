// package CommandLineInterpreter;

// import java.lang.reflect.Constructor;
// import javax.swing.JButton;
import javax.swing.JFrame;
import java.util.Scanner;
import java.io.File;

public class cmd {
        public static void main (String[] args) {

        // Window example
        // Window window = new Window();
        // window.createWindow();

        // Commands cmd = new Commands();
        // String name = cmd.getInput("What is your name?");
        // System.out.println("Hello " + name);

        InputHandler input = new InputHandler();
        Interpreter interpreter = new Interpreter();

        while(true) {
            // Get input from user
            String command = input.getInput();

            // Loop exit handling
            if (command.trim().equals("exit")) {
                input.closeScanner();
                break;
            } else {
                interpreter.interpretCommand(command);
            }
            
        }


    }


    static class Window {
        // Window size
        int height = 960;
        int width = 540;


        // Create the window
        void createWindow () {
            // Instantiate window
            JFrame window = new JFrame();

            // Window title & size
            window.setVisible(true);
            window.setTitle("Jarvis: Command Line");
            window.setSize(this.height, this.width);

            // Defines the 'x' close event on the window
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    }

    // Handles the input from the user
    static class InputHandler {
        // Instantiate objects
        Scanner scanner = new Scanner(System.in);
        // FileSystemCommands file = new FileSystemCommands();

        // Prompts the user for a command and returns the input as a string
        // Does not close scanner
        public String getInput () {
            System.out.print("<Enter command> ");
            return scanner.nextLine().trim();
        }
        // Close the scanner to prevent resource link
        public void closeScanner () {
            scanner.close();
        }
    }



    // File system commands
    static class FileSystemCommands {
        // Current selected path
        String currentPath = "C:\\";

        // Traverse backward to the parent directory
        public void traverseToParent () {
            // No parent directory was found
            if (currentPath.split(":\\\\").length == 1) {
                System.out.println("ERROR: No parent directory found");

            // Parent directory is found
            } else {
                // Split path into strings
                String[] split = currentPath.split("\\\\");
                        
                // Clear path string
                currentPath = "";

                // Reform string
                for(int i = 0; i < split.length - 1; i++) {currentPath += split[i] + "\\";}
            }
        }

        // Traverse forward to the inputted child directory
        public void traverseToChild(String child) {
            // Holds the new path
            String newPath = currentPath + child + "\\";

            // List of child directories
            File[] dirs = new File(currentPath).listFiles(); 

            // Ensures the child directory is found             
            for (File dir : dirs) {
                // Directory was found
                // System.out.println(dir.toString());
                // System.out.println(newPath);
                String fDir = dir.toString() + "\\";
                if (fDir.equals(newPath)) {
                    currentPath = newPath;
                    return;
                }
            }
            // Unknown child error
            System.out.println("ERROR: Child directory was not found");
        }


        // Print the list of directories
        public void listDirectories () {
            try {
                // List of each child directory
                File[] dirs = new File(currentPath).listFiles(); 

                // No child directories found  
                if(dirs.length == 0) {
                    System.out.println("ERROR: No child directories found");
                } else {
                    // Print each child
                    for(File f : dirs) {
                        // Split child and print last piece of the path
                        String[] split = f.toString().split("\\\\");
                        System.out.println("<dir> " + split[split.length-1]);
                    }
                }
            } catch (java.lang.NullPointerException e) {
                System.err.println("ERROR: No child directories found");
            }

        }


        // Switches the main path drive
        public void switchDrive (char drive) {
            // Switch drive
            String newPath = drive + ":\\";

            // Ensure the drive exists
            if (new File(newPath).exists()) {
                // Update path
                currentPath = newPath;
            } else {
                System.out.println("ERROR: Drive does not exist");
            }
        }

        // Creates a new child directory at the current path
        public void createDirectory (String name) {
            // Path of the new directory
            File newDir = new File(currentPath + name);

            // File already exists
            if (newDir.exists()) {
                // Define the count
                int count = 1;
                // Until a directory name with the count is NOT found
                while(newDir.exists()) {
                    // Add count to directory name
                    newDir = new File(currentPath + name + "(" + count + ")");
                    count++;
                }
                // Create the new directory
                newDir.mkdir();
            } else {
                // Create the directory
                newDir.mkdir();
            }
            // Print success message
            System.out.println("New directory '" + newDir + "' was created");
        }

    }


    // Other system commands
    static class SystemCommands {
        // Operating system
        final String os = System.getProperty("os.name");

        // Displays help message to the user
        public void getHelp () {
            // Help message
            String helpMessage = "\nFile System Commands\n----------------------------------\npd: traverse to parent directory\ncd {name}: traverse to 'name' child directory\ndirs: list of child directories\ncur: print the current index\nsd {letter}: switch drive to {letter} drive\nnewdir {name}: creates a new child directory at the current path\n\nOther System Controls\n----------------------------------\nhelp: displays this message\nclear: clears the log";
            // Print message
            System.out.println(helpMessage);
        }

        // Clears the log
        public void clearTerminal () {
            try {
                // Windows clear function
                if (os.contains("Windows")) new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                
            // Error handling
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Interpreter class to interpret the commands inputted by the user 
    static class Interpreter {
        // External class objects
        FileSystemCommands file = new FileSystemCommands();
        SystemCommands sys =  new SystemCommands();
        InputHandler inputHandler = new InputHandler();
        
        // Handle user command input
        public void interpretCommand (String input) {
            // Split input into individual words
            String[] command = input.split(" ");

            // traverseToParent command: pd
            if (command[0].equals("pd")) {
                file.traverseToParent();
            }

            // traverseToChild command: cd {childName} 
            else if (command[0].equals("cd")) {
                // Contains name
                try {
                    // Append all args to string to allow spaces in file name
                    String fileName = command[1];
                    for(int i = 2; i < command.length; i++) {
                        fileName += " " + command[i];
                    }
                    file.traverseToChild(fileName);

                // Index out of range error
                } catch (java.lang.ArrayIndexOutOfBoundsException error) {
                    System.out.println("ERROR: Undefined child directory");
                }
            }

            // currentPath command: cur 
            else if (command[0].equals("cur")) {
                System.out.println("<path> " + file.currentPath);
            }

            // listDirectories command: dirs
            else if (command[0].equals("dirs")) {
                file.listDirectories();
            }

            // switchDrive command: sd {driveLetter}
            else if (command[0].equals("sd")) {
                // Contains drive letter
                try {
                    // Convert second arg to capital char[]
                    char[] chars = command[1].toUpperCase().toCharArray();
                    file.switchDrive(chars[0]);

                // Index out of range error
                } catch (java.lang.ArrayIndexOutOfBoundsException error) {
                    System.out.println("ERROR: Undefined drive indicator");
                }
            }

            // createDirectory command: newdir
            else if (command[0].equals("newdir")) {
                // Contains name
                try {
                    // Append all args to string to allow spaces in file name
                    String dirName = command[1];
                    for(int i = 2; i < command.length; i++) {
                        dirName += " " + command[i];
                    }
                    file.createDirectory(dirName);

                // Index out of range error
                } catch (java.lang.ArrayIndexOutOfBoundsException error) {
                    System.out.println("ERROR: Undefined directory name");
                }
            }

            // getHelp command: help
            else if (command[0].equals("help")) {
                sys.getHelp();
            }

            // clearTerminal command: clear
            else if (command[0].equals("clear")) {
                sys.clearTerminal();
            }


            // unknown command: else
            else {
                System.out.println("ERROR: Unknown command");
            }
        }
    }
}