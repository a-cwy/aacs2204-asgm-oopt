import com.fasterxml.jackson.core.JsonProcessingException;
import fileHandler.FileHandler;
import init.Init;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static final String dataDir = "data\\";
    public static final String accountsDir = "accounts\\";

    public static void main(String[] args) {
        {   // INIT BLOCK
            ArrayList<String> dirs = new ArrayList<>();
            dirs.addAll(0, List.of(dataDir));
            dirs.addAll(dirs.size(), List.of(accountsDir));
            Init.setDirs(dirs);

            // RUN INIT
            Init.run();
        }

        // MENUS
        Menu mainMenu = new Menu("Main Menu", new String[]{"Login", "Register"});

        int choice = -1;
        while (choice != 0) {
            choice = mainMenu.prompt();

            switch (choice) {
                case 1: // LOGIN
                {
                    String username, password;

                    //get inputs
                    Scanner sc = new Scanner(System.in);
                    System.out.print("Enter username: ");
                    username = sc.nextLine();

                    System.out.print("Enter password: ");
                    password = sc.nextLine();

                    Account acc = new Account(username, password);
                    if (Account.authenticate(acc)) {
                        System.out.println("Login successful.");
                    } else {
                        System.out.println("Login failed.");
                    }

                    break;
                }
                case 2: // REGISTER
                {
                    String username, password;

                    //get inputs
                    Scanner sc = new Scanner(System.in);
                    System.out.print("Enter username: ");
                    username = sc.nextLine();

                    System.out.print("Enter password: ");
                    password = sc.nextLine();

                    Account.createAccount(username, password);
                    break;
                }
                default:
                    break;
            }
        }
    }       
}