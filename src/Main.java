import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String mainMenuHeader = "Main Menu";
        ArrayList<String> mmo = new ArrayList<>(Arrays.asList("Login", "Register"));
        Menu mainMenu = new Menu(mainMenuHeader, mmo);

        System.out.printf("%-5s %-30s %-30s %-20s %-10s %-10s\n", "ID", "Name", "Description", "Brand", "Price", "Quantity");

        int choice = 0;
        while (choice != 1) {
            choice = mainMenu.prompt();
        }
    }       
}