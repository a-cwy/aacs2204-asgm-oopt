import com.fasterxml.jackson.core.JsonProcessingException;
import fileHandler.FileHandler;
import init.Init;

import java.text.SimpleDateFormat;
import java.util.*;
public class Main {
    private static final Scanner sc = new Scanner(System.in);
    public static final Map<String, String> dirs = new HashMap<>();
    private static final Menu mainMenu = new Menu("Main Menu", new String[]{
            "Login",
            "Register"
    });
    private static final Menu userMenu = new Menu("Logged in as [user].", new String[]{
            "View supplier",
            "View warehouse",
            "View branch",
            "Add new supplier",
            "Add new warehouse",
            "Add new branch"
    });
    private static final Menu supplierMenu = new Menu("Currently viewing [supplier] as [user].", new String[]{
            "Display information", // id, name, location, total amt of items, total value of inventory
            "Edit information",
            "Display products", // table format, include id, name, price, quantity, total subvalue, total value
            "Transactions"
    });
    private static final Menu supplierEditMenu = new Menu("Currently viewing [supplier].", new String[]{
            "Edit name",
            "Edit Location"
    });
    private static final Menu warehouseMenu = new Menu("Currently viewing [warehouse] as [user].", new String[]{
            "Display information", // id, name, location, total amt of items, total value of inventory, total subscribed suppliers
            "Edit information",
            "Display products", // table format, include id, name, price, quantity, total subvalue, total value
            "Display subscribed suppliers", // table format, id, name, location
            "Display supplier inventory", // prompt id and display info
            "Subscribe supplier",
            "Unsubscribe supplier",
            "Transactions"
    });
    private static final Menu branchMenu = new Menu("Currently viewing [branch] as [user].", new String[]{
            "Display information", // id, name, location, total amt of items, total value of inventory, total subscribed warehouses
            "Edit information",
            "Display products", // table format, include id, name, price, quantity, total subvalue, total value
            "Display subscribed warehouses", // table format, id, name, location
            "Display warehouse inventory", // prompt id and display info
            "Subscribe supplier",
            "Unsubscribe supplier",
            "Transactions"
    });
    private static final Menu transactionMenu = new Menu("Transactions for [inventory].", new String[]{
            "Create transaction",
            "Update transaction",
            "Display all transactions",
            "Display outgoing transactions",
            "Display incoming transactions",
            "Display pending transactions"
    });

    public static void main(String[] args) {
        {   // INIT BLOCK
            dirs.put("data", "data\\");
            dirs.put("branches", "data\\branches\\");
            dirs.put("suppliers", "data\\suppliers\\");
            dirs.put("warehouses", "data\\warehouses\\");
            dirs.put("accounts", "accounts\\");

            Init.setDirs(new ArrayList<String>(dirs.values()));
            Init.run();
        }

        int choice = -1;
        while (choice != 0) {
            choice = mainMenu.prompt();

            switch (choice) {
                case 1: // LOGIN
                {
                    String username, password;

                    //get inputs
                    System.out.print("Enter username: ");
                    username = sc.nextLine();

                    System.out.print("Enter password: ");
                    password = sc.nextLine();

                    Account acc = new Account(username, password);
                    if (Account.authenticate(acc)) {
                        System.out.println("Login successful.");
                        userMenu(acc);
                    } else {
                        System.out.println("Login failed.");
                    }

                    break;
                }
                case 2: // REGISTER
                {
                    String username, password;

                    //get inputs
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

    private static void userMenu(Account acc) {
        userMenu.setHeader(String.format("Logged in as %s.", acc.getUsername()));

        int choice = -1;
        while (choice != 0) {
            choice = userMenu.prompt();

            switch (choice) {
                case 1: // VIEW SUPPLIER
                {
                    // prompt for supplier id
                    //
                    // read from file using directory :
                    // Main.dirs.get("suppliers") + id + ".json";
                    //
                    // call supplierMenu(sup, acc);
                    //test by waiz
//                    Product[] prod = new Product[]{new Product("name", "name")};
//
//                    Address add = new Address ("No,9", "", "", "", "", 4700);
//                    Supplier sup = new Supplier("1003", "Test 2", add, prod);
//                    try {
//                        FileHandler.writeObjectToFile(sup, Main.dirs.get("suppliers") + sup.getId() + ".json");
//                    } catch (JsonProcessingException e) {
//                        e.printStackTrace();
//                        System.out.println("Failed to create account (Couldn't save account as file).");
//                        return;
//                    }
                    String supplierID;

                    System.out.print("Enter supplier id: ");
                    supplierID = sc.nextLine();
                    Supplier temp = new Supplier();
                    try{
                        temp = FileHandler.readObjectFromFile(temp, Main.dirs.get("suppliers") + supplierID + ".json");
                    } catch (JsonProcessingException _) {}
                    supplierMenu(temp, acc);

                    // TODO
                    break;
                }
                case 2: // VIEW WAREHOUSE
                {
                    // prompt for warehouse id
                    //
                    // read from file using directory :
                    // Main.dirs.get("warehouses") + id + ".json";
                    //
                    // call warehouseMenu(war, acc);

                    // TODO
                    break;
                }
                case 3: // VIEW BRANCH
                {
                    // prompt for branch id
                    //
                    // read from file using directory :
                    // Main.dirs.get("branch") + id + ".json";
                    //
                    // call branchMenu(br, acc);

                    // TODO
                    break;
                }
                case 4: // ADD NEW SUPPLIER
                {
                    System.out.print("Enter ID for new supplier > ");
                    String supplierID = sc.nextLine();

                    try {
                        Supplier sup = new Supplier();
                        sup = FileHandler.readObjectFromFile(sup, Main.dirs.get("suppliers") + supplierID + ".json");
                        if (sup.getId() != null) {
                            System.out.println("ID already taken.");
                            break;
                        }
                    } catch (JsonProcessingException _) {}

                    System.out.print("Enter name for supplier > ");
                    String supplierName = sc.nextLine();

                    System.out.println("Enter location for supplier,");
                    Address supplierLocation = new Address();
                    System.out.print("\tUnit > ");
                    supplierLocation.setUnit(sc.nextLine());
                    System.out.print("\tBuilding > ");
                    supplierLocation.setBuilding(sc.nextLine());
                    System.out.print("\tStreet > ");
                    supplierLocation.setStreet(sc.nextLine());
                    System.out.print("\tTown > ");
                    supplierLocation.setTown(sc.nextLine());
                    System.out.print("\tState > ");
                    supplierLocation.setState(sc.nextLine());
                    System.out.print("\tPostal Code > ");
                    supplierLocation.setPostalCode(sc.nextInt());
                    sc.nextLine();

                    Supplier sup = new Supplier(supplierID, supplierName, supplierLocation, new Product[]{});

                    try {
                        FileHandler.writeObjectToFile(sup, Main.dirs.get("suppliers") + supplierID + ".json");
                    } catch (JsonProcessingException _) {}

                    break;
                }
                default:
                    break;
            }
        }
    }

    private static void supplierMenu(Supplier sup, Account acc) {
        supplierMenu.setHeader(String.format("Currently viewing [%s] as [%s].", sup.getId(), acc.getUsername()));

        int choice = -1;
        while (choice != 0) {
            choice = supplierMenu.prompt();

            switch (choice) {
                case 1: // DISPLAY INFO
                {
                    System.out.println("Supplier id: " + sup.getId());
                    System.out.println("Supplier Name: " + sup.getName());
                    System.out.println("Supplier Address: " + sup.getLocation());
                    // TODO
                    break;
                }
                case 2: // EDIT INFO
                {
                    supplierEditMenu(sup);
                    break;
                }
                case 3: // DISPLAY PRODUCTS
                {

                    // TODO
                    break;
                }
                case 4: // TRANSACTION MENU
                {
                    transactionMenu(sup, acc);

                    try {
                        FileHandler.writeObjectToFile(sup, Main.dirs.get("suppliers") + sup.getId() + ".json");
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                        return;
                    }

                    break;
                }
                default:
                    break;
            }
        }
    }

    private static void warehouseMenu(Warehouse war, Account acc) {
        warehouseMenu.setHeader(String.format("Currently viewing [%s] as [%s].", war.getId(), acc.getUsername()));

        int choice = -1;
        while (choice != 0) {
            choice = warehouseMenu.prompt();

            switch (choice) {
                case 1: // DISPLAY INFO
                {
                    // TODO
                    break;
                }
                case 2: // EDIT INFO
                {
                    // TODO
                    break;
                }
                case 3: // DISPLAY PRODUCTS
                {
                    // TODO
                    break;
                }
                case 4: // DISPLAY SUBSCRIBED SUPPLIERS
                {
                    // TODO
                    break;
                }
                case 5: // DISPLAY SUPPLIER INVENTORY
                {
                    // prompt id and display

                    // TODO
                    break;
                }
                case 6: // SUBSCRIBE SUPPLIER
                {
                    // prompt id, existence check, append to subscribed and save file

                    // TODO
                    break;
                }
                case 7: // UNSUBSCRIBE SUPPLIER
                {
                    // prompt id, existence check, remove from subscribed and save file

                    // TODO
                    break;
                }
                case 8: // TRANSACTION MENU
                {
                    transactionMenu(war, acc);
                    // TODO
                    break;
                }
                default:
                    break;
            }
        }
    }

    private static void branchMenu(Branch br, Account acc) {
        branchMenu.setHeader(String.format("Currently viewing [%s] as [%s].", br.getId(), acc.getUsername()));

        int choice = -1;
        while (choice != 0) {
            choice = branchMenu.prompt();

            switch (choice) {
                case 1: // DISPLAY INFO
                {
                    // TODO
                    break;
                }
                case 2: // EDIT INFO
                {
                    // TODO
                    break;
                }
                case 3: // DISPLAY PRODUCTS
                {
                    // TODO
                    break;
                }
                case 4: // DISPLAY SUBSCRIBED WAREHOUSES
                {
                    // TODO
                    break;
                }
                case 5: // DISPLAY WAREHOUSE INVENTORY
                {
                    // prompt id and display

                    // TODO
                    break;
                }
                case 6: // SUBSCRIBE WAREHOUSE
                {
                    // prompt id, existence check, append to subscribed and save file

                    // TODO
                    break;
                }
                case 7: // UNSUBSCRIBE WAREHOUSE
                {
                    // prompt id, existence check, remove from subscribed and save file

                    // TODO
                    break;
                }
                case 8: // TRANSACTION MENU
                {
                    transactionMenu(br, acc);
                    // TODO
                    break;
                }
                default:
                    break;
            }
        }
    }

    private static void transactionMenu(Inventory inv, Account acc) {
        transactionMenu.setHeader(String.format("Transaction menu for [%s].", inv.getLog().getOwnerID()));

        int choice = -1;
        while (choice != 0) {
            choice = transactionMenu.prompt();

            switch (choice) {
                case 1: // CREATE TRANSACTION
                {
                    System.out.print("Please enter ID of supplier/warehouse/branch to create a transaction with.\n> ");
                    String buyerID = sc.nextLine();

                    //check for self transact
                    if (inv.getLog().getOwnerID().equals(buyerID)) {
                        System.out.println("Can't perform transaction with self.");
                        break;
                    }

                    //check for existence of buyer
                    Supplier sup = new Supplier();
                    Warehouse war = new Warehouse();
                    Branch br = new Branch();
                    switch (buyerID.charAt(0)) {
                        case 'S':
                        {
                            try {
                                sup = FileHandler.readObjectFromFile(sup, Main.dirs.get("suppliers") + buyerID + ".json");
                            } catch (JsonProcessingException _) {}
                            break;
                        }
                        case 'W':
                        {
                            try {
                                war = FileHandler.readObjectFromFile(war, Main.dirs.get("warehouses") + buyerID + ".json");
                            } catch (JsonProcessingException _) {}
                            break;
                        }
                        case 'B':
                        {
                            try {
                                br = FileHandler.readObjectFromFile(br, Main.dirs.get("suppliers") + buyerID + ".json");
                            } catch (JsonProcessingException _) {}
                            break;
                        }
                        default:
                            System.out.println("ID does not exist.");
                            break;
                    }

                    if (sup.getId().isBlank() && war.getId().isBlank() && br.getId().isBlank()) {
                        System.out.println("ID does not exist.");
                        break;
                    }

                    //create items list
                    ArrayList<Product> items = new ArrayList<>();

                    char cont = 'y';
                    while (cont == 'y') {
                        System.out.print("Enter product name > ");
                        String name = sc.nextLine();

                        System.out.print("Enter product price > RM");
                        double price = sc.nextDouble();
                        sc.nextLine();

                        System.out.print("Enter quantity > ");
                        int quantity = sc.nextInt();
                        sc.nextLine();

                        boolean exists = false;
                        for (Product prod : items) {
                            if (prod.getName().equals(name) && prod.getPrice() == price) {
                                prod.addQuantity(quantity);
                                exists = true;
                                break;
                            } else if (prod.getName().equals(name) && prod.getPrice() != price) {
                                System.out.println("Product already exists with different price.");
                                exists = true;
                                break;
                            }
                        }
                        if (!exists) items.addLast(new Product(name, price, quantity));

                        System.out.print("Add more? (Y/N) > ");
                        String temp = sc.nextLine();
                        cont = Character.toLowerCase(temp.charAt(0));
                    }

                    //create a new transaction
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");
                    Date currentTime = new Date();
                    String tranID = inv.getLog().getOwnerID() +
                            "-" +
                            buyerID +
                            "-" +
                            dateFormat.format(currentTime);

                    Transaction newTran = new Transaction(tranID, currentTime, inv.getLog().getOwnerID(), buyerID, items);

                    //add transaction into logs
                    inv.getLog().append(newTran);

                    switch (buyerID.charAt(0)) {
                        case 'S':
                        {
                            try {
                                sup.getLog().append(newTran);
                                FileHandler.writeObjectToFile(sup, Main.dirs.get("suppliers") + buyerID + ".json");
                            } catch (JsonProcessingException _) {}
                            break;
                        }
                        case 'W':
                        {
                            try {
                                war.getLog().append(newTran);
                                FileHandler.writeObjectToFile(war, Main.dirs.get("warehouses") + buyerID + ".json");
                            } catch (JsonProcessingException _) {}
                            break;
                        }
                        case 'B':
                        {
                            try {
                                br.getLog().append(newTran);
                                FileHandler.writeObjectToFile(br, Main.dirs.get("suppliers") + buyerID + ".json");
                            } catch (JsonProcessingException _) {}
                            break;
                        }
                        default:
                            break;
                    }

                    break;
                }
                case 2: // UPDATE TRANSACTION
                {
                    System.out.print("Enter transaction ID to update > ");
                    String tranID = sc.nextLine();

                    //find transaction
                    Transaction tran = new Transaction();
                    for (Transaction transaction : inv.getLog().getLog()) {
                        transaction.printTransaction();
                        if (transaction.getId().equals(tranID)) {
                            tran = transaction;
                        }
                    }

                    //check for existence
                    if (tran.getId() == null) {
                        System.out.println("Transaction does not exist.");
                        break;
                    }

                    //check if completed
                    if (!tran.getStatus().equals("PENDING")) {
                        System.out.println("Transaction already completed.");
                        break;
                    }

                    tran.printTransaction();


                    break;
                }
                case 3: // DISPLAY ALL TRANSACTIONS
                {
                    for (Transaction transaction : inv.getLog().getLog()) {
                        transaction.printTransaction();
                    }

                    break;
                }
                case 4: // DISPLAY OUTGOING TRANSACTIONS
                {
                    // TODO
                    break;
                }
                case 5: // DISPLAY INCOMING TRANSACTIONS
                {
                    // TODO
                    break;
                }
                case 6: // DISPLAY PENDING TRANSACTIONS
                {
                    // TODO
                    break;
                }
                default:
                    break;
            }
        }
    }

    private static void supplierEditMenu(Supplier sup) {
        supplierEditMenu.setHeader(String.format("Currently viewing [%s].", sup.getId()));

        int choice = -1;
        while (choice != 0) {
            choice = supplierEditMenu.prompt();

            switch (choice) {
                case 1: // EDIT NAME
                {
                   System.out.println("Current supplier name: " + sup.getName());
                   System.out.print("New supplier name: ");
                   sup.setName(sc.nextLine());
                   System.out.print("Successfully updated supplier name");
                    try {
                        FileHandler.writeObjectToFile(sup, Main.dirs.get("suppliers") + sup.getId() + ".json");
                    } catch (JsonProcessingException _) {
                        System.out.println("Failed to create account (Couldn't save account as file).");
                        return;
                    }
                    // TODO
                    break;
                }
                case 2: // EDIT LOCATION
                {
                    Address temp = new Address();
                    System.out.println("Current supplier address: " + sup.getLocation());
                    System.out.println("New supplier address: ");

                    System.out.print("Enter unit no: ");
                    temp.setUnit(sc.nextLine());

                    System.out.print("Enter building: ");
                    temp.setBuilding(sc.nextLine());

                    System.out.print("Enter street: ");
                    temp.setStreet(sc.nextLine());

                    System.out.print("Enter town: ");
                    temp.setTown(sc.nextLine());

                    System.out.print("Enter state: ");
                    temp.setState(sc.nextLine());

                    System.out.print("Enter postal code: ");
                    temp.setPostalCode(sc.nextInt());

                    System.out.println("Successfully updated supplier address");
                    sup.setLocation(temp);
                    try {
                        FileHandler.writeObjectToFile(sup, Main.dirs.get("suppliers") + sup.getId() + ".json");
                    } catch (JsonProcessingException _) {
                        System.out.println("Failed to edit account (Couldn't save account as file).");
                        break;
                    }

                    break;
                }

                default:
                    break;
            }
        }
    }
}