package fileSystem;
import java.util.ArrayList;

public class Create {

    public static String createPath (ArrayList<String> args) {
        System.out.println("Create a new path!");

        for (int i = 0; i < args.size(); i++) {
            System.out.println(args.get(i));
        }

        return "";
    }
}
