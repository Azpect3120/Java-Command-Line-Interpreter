package CommandLineInterpreter;

import java.lang.reflect.Constructor;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.util.Scanner;

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
                // input.closeScanner();
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
        FileSystemCommands file = new FileSystemCommands();

        // Prompts the user for a command and returns the input as a string
        // Does not close scanner
        public String getInput () {
            // Print the command
            System.out.print("<Enter command> ");

            // Return user input
            return scanner.nextLine().trim();
        }
    }

    // Close the scanner to prevent resource link
    public void closeScanner () {
        scanner.close();
    }

    // File system commands
    static class FileSystemCommands {
        String currentPath = "C:\\";

        // Traverse backward to the parent directory
        public void traverseToParent () {
            // No parent directory was found
            if (currentPath.equals("C:\\")) {
                System.out.println("ERROR: No parent directory found");
            // Parent directory is found
            } else {
                // Split path into strings
                String[] split = currentPath.split("\\\\");
                        
                // Clear path string
                currentPath = "";

                // Reform string
                for(int i = 0; i < split.length - 1; i++) {
                        currentPath += split[i] + "\\";
                }
            }
        }

        // Traverse forward to the inputted child directory
        public void traverseToChild(String child) {
            // Update path
            currentPath = currentPath + child + "\\";
        }

        // Print the list of directories
        public void listDirectories () {

        }

        // Print the current path of the path
        public void currentPath () {
            System.out.println(currentPath);
        }

    }

    // Interpreter class to interpret the commands inputted by the user 
    static class Interpreter {
        // External class objects
        FileSystemCommands file = new FileSystemCommands();
        InputHandler inputHandler = new InputHandler();
        
        // Handle user command input
        public void interpretCommand (String input) {
            // Split input into individual words
            String[] command = input.split(" ");

            // traverseToParent command: pd
            if (command[0].equals("pd")) {
                file.traverseToParent();
            }

            // traverseToChild command: cd 'childName' 
            else if (command[0].equals("cd")) {
                // Contains name
                try {
                    file.traverseToChild(command[1]);
                // Index out of range error
                } catch (java.lang.ArrayIndexOutOfBoundsException error) {
                    System.out.println("ERROR: Undefined child directory");
                }
            }

            // currentPath command: cur
            else if (command[0].equals("cur")) {
                file.currentPath();
            }


            // unknown command: else
            else {
                System.out.println("ERROR: Unknown command");
            }
        }
    }

    
}