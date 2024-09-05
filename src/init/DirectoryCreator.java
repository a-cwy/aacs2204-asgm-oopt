package init;

import java.io.File;

public class DirectoryCreator {
    private static final String[] dirs = {
            "data"
    };

    static void create() {
        int created = 0;
        int found = 0;

        for (String dir : dirs) {
            File dirFile = new File(dir);

            if (dirFile.mkdirs()) {
                System.out.println("Directory created: .\\" + dir);
                created++;
            } else {
                System.out.println("Directory found: .\\" + dir);
                found++;
            }
        }

        System.out.printf("Created\t: %d\nFound\t: %d\n", created, found);
    }
}
