import com.fasterxml.jackson.core.JsonProcessingException;
import fileHandler.FileHandler;

import java.io.IOException;

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
            fromFile = FileHandler.readObjectFromFile(fromFile, Main.accountsDir + acc.username + ".json");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        if (fromFile == null) return false;

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
            FileHandler.writeObjectToFile(acc, Main.accountsDir + acc.username + ".json");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            System.out.println("Failed to create account (Couldn't save account as file).");
            return;
        }

        System.out.println("Account created.");
    }

    private static boolean checkUsernameExists(String username) {
        Account temp = null;

        try {
            FileHandler.readObjectFromFile(temp, Main.accountsDir + username + ".json");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return temp != null;
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
