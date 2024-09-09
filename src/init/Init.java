package init;

import java.util.ArrayList;

public class Init {
    private static String[] dirs = null;

    // run method
    public static void run() {
        DirectoryCreator.create(Init.dirs);
    }

    // init setters
    public static void setDirs(ArrayList<String> dirArr) {
        Init.dirs = dirArr.toArray(new String[]{});
    }
}
