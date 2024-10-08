package root;

import com.fasterxml.jackson.core.JsonProcessingException;
import root.util.FileHandler;
import root.util.FileHandler;

public class Account {
    private String username;
    private String password;

    Account() {
        this.username = "";
        this.password = "";
    }

    Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // METHODS
    public static boolean authenticate(Account acc) {
        Account fromFile = new Account();

        try {
            fromFile = FileHandler.readObjectFromFile(fromFile, Main.dirs.get("accounts") + acc.username + ".json");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        if (fromFile.username.isBlank()) return false;

        return acc.equals(fromFile);
    }

    public static void createAccount(String username, String password) {
        if (checkUsernameExists(username)) return;
        if (password.length() <= 5) {
            System.out.println("Password must be longer than 5 characters.");
            return;
        }

        Account acc = new Account(username, password);
        try {
            FileHandler.writeObjectToFile(acc, Main.dirs.get("accounts") + acc.username + ".json");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            System.out.println("Failed to create account (Couldn't save account as file).");
            return;
        }

        System.out.println("Account created.");
    }

    private static boolean checkUsernameExists(String username) {
        Account temp = new Account();

        try {
            FileHandler.readObjectFromFile(temp, Main.dirs.get("accounts") + username + ".json");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return !temp.username.isBlank();
    }

    public boolean comparePassword(String password) {
        return this.password.equals(password);
    }

    public boolean equals(Account acc) {
        return this.username.equals(acc.username) && this.comparePassword(acc.password);
    }

    // GET SET
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
