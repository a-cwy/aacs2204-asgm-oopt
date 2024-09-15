package root.init;

import java.util.ArrayList;

public class Init {
    private static ArrayList<String> dirs = null;

    // run method
    public static void run() {
        DirectoryCreator.create(Init.dirs);
    }

    // init setters
    public static void setDirs(ArrayList<String> dirArr) {
        Init.dirs = dirArr;
    }
}
