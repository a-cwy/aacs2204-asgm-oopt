import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Menu {
    private final String header;
    private final ArrayList<String> options;

    public Menu(String header, String[] optionsArr) {
        this.header = header;
        this.options = new ArrayList<>();
        this.options.add("Exit / Back");
        this.options.addAll(Arrays.asList(optionsArr));
    }

    public Menu(String header, ArrayList<String> optionsArr) {
        this.header = header;
        this.options = (ArrayList<String>) optionsArr.clone();
        this.options.addFirst("Exit / Back");
    }

    // METHODS
    public int prompt() {
        System.out.println(this.header);
        for (int i = 0; i < this.header.length(); i++) {
            System.out.print("=");
        }
        System.out.println();
        for (int i = 0; i < this.options.size(); i++) {
            System.out.printf("%2d: %s\n", i + 1, this.options.get(i));
        }

        // Get input
        Scanner sc = new Scanner(System.in);
        String buf;
        int retval = -1;

        while(retval < 1 || retval > this.options.size()) {
            System.out.print("\n> ");
            buf = sc.nextLine();
            try {
                retval = Integer.parseInt(buf);
            } catch(NumberFormatException e) {
                System.out.println("Option must be a number.");
            }
        }

        return retval;
    }
}
