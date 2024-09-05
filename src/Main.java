import com.fasterxml.jackson.core.JsonProcessingException;
import fileHandler.FileHandler;
import init.Init;

public class Main {
    public static void main(String[] args) {
        Init.run();

        Menu mainMenu = new Menu("Main Menu", new String[]{"Login", "Register", "Write JSON", "Read JSON"});

        int choice = -1;
        while (choice != 0) {
            choice = mainMenu.prompt();

            switch (choice) {
                case 3:
                    try {
                        FileHandler.writeObjectToFile(mainMenu, "data\\main-menu.json");
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    try {
                        mainMenu = (Menu) FileHandler.readObjectFromFile("data\\main-menu.json", Menu.class);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    break;
            }
        }
    }       
}