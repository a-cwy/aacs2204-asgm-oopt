package init;

import java.io.File;

class DirectoryCreator {
    static void create(String[] dirs) {
        int created = 0;
        int found = 0;

        for (String dir : dirs) {
            File dirFile = new File(dir);

            try {
                if (dirFile.mkdirs()) {
                    System.out.println("Directory created: .\\" + dir);
                    created++;
                } else {
                    System.out.println("Directory found: .\\" + dir);
                    found++;
                }
            } catch (SecurityException e) {
                System.out.print("Insufficient permission to create directory.\n" + e + "\n");
            }
        }

        System.out.printf("Created\t: %d\nFound\t: %d\n", created, found);
    }
}
