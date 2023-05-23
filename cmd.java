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


    static class Commands {

        
        // Prompts the user for a command and returns the input as a string
        public String getInput (String command) {
            // Instantiate scanner
            Scanner scanner = new Scanner(System.in);

            // Print the command
            System.out.println(command);

            // Get input from next string
            String input = scanner.nextLine();

            // Close scanner
            scanner.close();

            // Return user input
            return input;
        }


    }

    
}