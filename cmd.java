// package CommandLineInterpreter;

// import java.lang.reflect.Constructor;
// import javax.swing.JButton;
import javax.swing.JFrame;
import java.util.Scanner;
import java.time.*;
import java.io.*;
import java.awt.Desktop;
import java.util.List;
import java.nio.file.Paths;
import java.nio.file.Files;


public class cmd {
        public static void main (String[] args) {

        // Instantiate classes
        InputHandler input = new InputHandler();
        Interpreter interpreter = new Interpreter();

        // Until the exit keyword is typed
        while(true) {
            // Get input from user
            String command = input.getInput("<command> ");

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
        // Instantiate scanner object
        Scanner scanner = new Scanner(System.in);


        // Prompts the user for a command and returns the input as a string
        public String getInput (String prompt) {
            System.out.print(prompt);
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
                System.out.println("<ERROR> No parent directory found");

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
            System.out.println("<ERROR> Child directory was not found");
        }


        // Print the list of directories
        public void listDirectories () {
            try {
                // List of each child directory
                File[] dirs = new File(currentPath).listFiles(); 

                // No child directories found  
                if(dirs.length == 0) {
                    System.out.println("<ERROR> No child directories found");
                } else {
                    // Print each child
                    for(File f : dirs) {
                        // Split child and print last piece of the path
                        String[] split = f.toString().split("\\\\");
                        
                        // Last edit on the file: epoch time
                        long epochTime = f.lastModified();

                        // Convert epochTime to readable data
                        Instant instant = Instant.ofEpochMilli(epochTime);
                        LocalDateTime timestamp = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());

                        // Convert time variable to date & time string 
                        String date = timestamp.toString().split("T")[0];
                        String time = timestamp.toString().split("T")[1];

                        // Reformat date & time strings
                        date = date.split("-")[1] + "/" + date.split("-")[2] + "/" + date.split("-")[0];
                        time = time.split(":")[0] + ":" + time.split(":")[1];

                        // Print final formatted output
                        System.out.printf("%-6s %-10s %-10s %-50s%n", "<dir>", date, time, split[split.length-1]);
                    }
                }
            } catch (java.lang.NullPointerException e) {
                System.err.println("<ERROR> No child directories found");
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
                System.out.println("<ERROR> Drive does not exist");
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
            System.out.println("<new directory> " + newDir);
        }


        // Create a new file at the current path (file name and extension cannot include spaces)
        public void createFile (String name, String ext) {
            // Create new file path
            File newFile = new File(currentPath + name + "." + ext);

            // File already exists 
            if (newFile.exists()) {
                // Define the count
                int count = 1;
                // Until a file name with the count is NOT found
                while(newFile.exists()) {
                    // Add count to the file name
                    newFile = new File(currentPath + name + "(" + count + ")." + ext);
                    count++;
                }
                // Create the new file
                try{newFile.createNewFile();} 
                catch (IOException e) {System.out.println(e);}

            // File does not exist
            } else {
                // Create the new file
                try{newFile.createNewFile();} 
                catch (IOException e) {System.out.println(e);}
            }
            // Print success message
            System.out.println("<new file> " + newFile);
        }


        // Deletes the dir/file at the given path
        public void deletePath (String path) {
            // Path of the target file
            File target = new File(currentPath + path);

            // File exists
            if (target.exists()) {
                // List of targets child directories
                File[] dirs = new File(currentPath + path).listFiles(); 

                // If target has any child directories
                if (dirs != null) {
                    // Remove child directories
                    for(File dir : dirs) {
                        dir.delete();
                    }
                }

                // Delete the target
                target.delete();
                System.out.println("<path deleted> " + target);
            } else {
                System.out.println("<ERROR> Path does not exist");
            }
        }


        // Renames the current directory or file
        public void renamePath (String newName) {
            // Path of the target directory
            File target = new File(currentPath);

            // Path is a disk directory
            if ((currentPath.split("\\\\").length == 1)) {
                System.out.println("<ERROR> Cannot rename disks");
            // Path is not a disk directory
            } else {
                // Target path does exist
                if (target.exists()) {
                    // Split path into strings
                    String[] split = currentPath.split("\\\\");
            
                    // Create new path string
                    String newPath = "";

                    // Add all parent directories to path 
                    for(int i = 0; i < split.length - 1; i++) {
                        newPath += split[i] + "\\";
                    }
                    // Add new path name
                    newPath += newName;

                    // Path name already exists
                    if (new File(newPath).exists()) {
                        System.out.println("<ERROR> Path name already exists");
                        
                    // Path name does not exist
                    } else {
                          // Rename the target
                        target.renameTo(new File(newPath));

                        // Update path 
                        currentPath = newPath;
                    }
                // Selected directory is unknown
                } else {
                    System.out.println("<ERROR> Unknown directory selected");
                }

            }
        }


        // Opens the current directory or file in the desktops default application 
        public void openPath () {
            // Open the path using the desktops default application
            try {Desktop.getDesktop().open(new File(currentPath));
        
            // Could not open file error
            } catch (IOException e) {System.out.println("<ERROR> Could not open directory or file");}
        }
    }

    // File editor commands
    static class FileEditorCommands {
        // Instantiate new scanner
        Scanner editorInput = new Scanner(System.in);
        String openedPath = "";

        // Opens the file editor on the current file
        public void openEditor (String path) {
            // Set path in editor
            openedPath = path.toString();

            // Loop until user inputs exit command
            while (true) {
                // Prompt
                System.out.print("<file editor> ");
                
                // Gets input from the user
                String[] command = editorInput.nextLine().split(" ");

                // exit editor: close
                if (command[0].equals("close")) {
                    // Reset path
                    openedPath = "";
                    // Exit loop
                    break;
                }


                // preview command: preview
                else if (command[0].equals("preview")) {
                    preview();
                }


                // append command: append {text}
                else if (command[0].equals("append")) {
                    // Create new line string
                    String newLine = "";

                    // Input was provided
                    if (command.length > 1) {
                        // Build new line string
                        for(int i = 1; i < command.length; i++) {newLine += command[i] + " ";}
                        // Run command with user input
                        append(newLine);
                    
                    // Input was not provided
                    } else {System.out.println("<ERROR> Undefined input");}
                }

                
                // Unknown command
                else {
                    System.out.println("<ERROR> Unknown command");
                }
            }
        }


        // create a new line at the bottom of the file
        public void append (String newLine) {
            // Success
            try {
                // Create a file writer
                FileWriter writer = new FileWriter(openedPath, true);

                // Create a Buffered writer
                BufferedWriter buffer = new BufferedWriter(writer);

                // Write new line to the file
                buffer.newLine();
                buffer.write(newLine);

                // Close the writers
                buffer.close();
                writer.close();

                // Success output 
                System.out.println("<line appended> " + newLine);

            // Error handler
            } catch (IOException e) {System.out.println("<ERROR> " + e);}
        }



        // display the contents of the file
        public void preview () {
            // Success
            try {
                // Get list of each line in the file
                List<String> lines = Files.readAllLines(Paths.get(openedPath));

                // Print each line
                for (String line : lines) {System.out.println(line);}

            // Error handler
            } catch (IOException e) {System.out.println("<ERROR> " + e);}  
        }


    }

    // Other system commands
    static class SystemCommands {
        // Operating system
        final String os = System.getProperty("os.name");


        // Displays help message to the user
        public void getHelp () {
            // Help message
            String helpMessage = "\nFile System Commands\n----------------------------------\npd: traverse to parent directory\ncd {name}: traverse to 'name' child directory\ndirs: list of child directories\ncur: print the current index\nsd {letter}: switch drive to {letter} drive\nnewdir {name}: creates a new child directory at the current path\nnewfile {name} {fileExtension}: create a new file at the current path (file name and extension cannot include spaces)\ndelpath {dirName/fileName}: deletes the dir/file at the given path\nrename {newDirName/newFileName}: renames the current directory or file (include file extension in the file name)\nopen: opens the current directory or file in the desktops default application\n\nOther System Controls\n----------------------------------\nhelp: displays this message\nclear: clears the log\nexit: exits out of the command line";
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
        FileSystemCommands fileSys = new FileSystemCommands();
        FileEditorCommands editor = new FileEditorCommands();
        SystemCommands sys =  new SystemCommands();
        InputHandler inputHandler = new InputHandler();
        
        // Handle user command input
        public void interpretCommand (String input) {
            // Split input into individual arguments
            String[] command = input.split(" ");

            // traverseToParent command: pd
            if (command[0].equals("pd")) {
                fileSys.traverseToParent();
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
                    fileSys.traverseToChild(fileName);

                // Index out of range error
                } catch (java.lang.ArrayIndexOutOfBoundsException error) {
                    System.out.println("<ERROR> Undefined child directory");
                }
            }

            // currentPath command: cur 
            else if (command[0].equals("cur")) {
                System.out.println("<path> " + fileSys.currentPath);
            }

            // listDirectories command: dirs
            else if (command[0].equals("dirs")) {
                fileSys.listDirectories();
            }

            // switchDrive command: sd {driveLetter}
            else if (command[0].equals("sd")) {
                // Contains drive letter
                try {
                    // Convert second arg to capital char[]
                    char[] chars = command[1].toUpperCase().toCharArray();
                    fileSys.switchDrive(chars[0]);

                // Index out of range error
                } catch (java.lang.ArrayIndexOutOfBoundsException error) {
                    System.out.println("<ERROR> Undefined drive indicator");
                }
            }

            // createDirectory command: newdir {name}
            else if (command[0].equals("newdir")) {
                // Contains name
                try {
                    // Append all args to string to allow spaces in file name
                    String dirName = command[1];
                    for(int i = 2; i < command.length; i++) {
                        dirName += " " + command[i];
                    }
                    fileSys.createDirectory(dirName);

                // Index out of range error (name is not provided)
                } catch (java.lang.ArrayIndexOutOfBoundsException error) {
                    System.out.println("<ERROR> Undefined directory name");
                }
            }

            // createFile command: newfile {name} {extension}
            else if (command[0].equals("newfile")) {
                // Contains name and extension
                try {
                    // Define name and extension for new file 
                    String fileName = command[1];
                    String fileExt = command[2];

                    // Create new file
                    fileSys.createFile(fileName, fileExt);

                // Index out of range error (name or extension is not provided)
                } catch (java.lang.ArrayIndexOutOfBoundsException error) {
                    System.out.println("<ERROR> Undefined file name or extension");
                }
            }

            // deletePath command: delpath {path}
            else if (command[0].equals("delpath")) {
                // Contains a path
                try {
                    // Define the target path (can include spaces)
                    String targetPath = command[1];
                    for(int i = 2; i < command.length; i++) {
                        targetPath += " " + command[i];
                    }
                    fileSys.deletePath(targetPath);

                // Index out of range error (dir/file name is not provided)
                } catch (java.lang.ArrayIndexOutOfBoundsException error) {
                    System.out.println("<ERROR> Undefined file name or path");
                }
            }

            // renamePath command: rename {newName}
            else if (command[0].equals("rename")) {
                fileSys.renamePath(command[1]);
            }

            // openPath command: open
            else if (command[0].equals("open")) {
                fileSys.openPath();
            }


            // openEditor command: editor
            else if (command[0].equals("editor")) {
                editor.openEditor(fileSys.currentPath);
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
                System.out.println("<ERROR> Unknown command");
            }
        }
    }
}