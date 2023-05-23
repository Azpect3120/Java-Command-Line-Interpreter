package CommandLineInterpreter;

import java.lang.reflect.Constructor;
import javax.swing.JButton;
import javax.swing.JFrame;

public class cmd {
    public static void main (String[] args) {

        Window window = new Window();

        window.createWindow();

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



    }

    
}