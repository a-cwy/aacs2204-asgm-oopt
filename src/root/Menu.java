package root;

import java.util.Scanner;

public class Menu {
    private String header;
    private final String[] options;

    public Menu() {
        this("", new String[]{});
    }

    public Menu(String header, String[] optionsArr) {
        this.header = header;
        this.options = new String[optionsArr.length + 1];
        options[0] = "Exit/Back";
        System.arraycopy(optionsArr, 0, this.options, 1, optionsArr.length);
    }

    // METHODS
    public int prompt() {
        System.out.println(this.header);
        for (int i = 0; i < this.header.length(); i++) {
            System.out.print("=");
        }
        System.out.println();
        for (int i = 0; i < this.options.length; i++) {
            System.out.printf("%2d: %s\n", i, this.options[i]);
        }

        // Get input
        Scanner sc = new Scanner(System.in);
        String buf;
        int retval = -1;

        while (retval < 0 || retval > this.options.length) {
            System.out.print("\n> ");
            buf = sc.nextLine();
            try {
                retval = Integer.parseInt(buf);
            } catch (NumberFormatException e) {
                System.out.println("Option must be a number.");
            }
        }

        return retval;
    }

    // GET SET
    public void setHeader(String header) {
        this.header = header;
    }

    public String getHeader() {
        return header;
    }

    public String[] getOptions() {
        return options;
    }

}
