package root;

import com.fasterxml.jackson.core.JsonProcessingException;
import root.init.Init;
import root.util.FileHandler;

import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    public static final Scanner sc = new Scanner(System.in);
    public static final Map<String, String> dirs = new HashMap<>();
    public static void clearScreen() {
        for (int i = 0; i < 100; i++) {
            System.out.println();
        }
    }
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
            "Add product",
            "Edit product",
            "Remove product",
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
            "Add product",
            "Edit product",
            "Remove product",
            "Display subscribed suppliers", // table format, id, name, location
            "Subscribe supplier",
            "Unsubscribe supplier",
            "Transactions"
    });
    private static final Menu branchMenu = new Menu("Currently viewing [branch] as [user].", new String[]{
            "Display information", // id, name, location, total amt of items, total value of inventory, total subscribed warehouses
            "Edit information",
            "Display products", // table format, include id, name, price, quantity, total subvalue, total value
            "Add product",
            "Edit product",
            "Remove product",
            "Display subscribed warehouses", // table format, id, name, location
            "Subscribe warehouse",
            "Unsubscribe warehouse",
            "Transactions"
    });

    private static final Menu branchEditMenu = new Menu("Currently viewing [branch].", new String[]{
            "Edit name",
            "Edit Location"
    });


    private static final Menu transactionMenu = new Menu("Transactions for [inventory].", new String[]{
            "Create transaction",
            "Update transaction",
            "Display all transactions",
            "Display outgoing transactions",
            "Display incoming transactions",
            "Display pending transactions"
    });
    private static final Menu transactionUpdateMenu = new Menu("Updating transaction [transaction].", new String[]{
            "Complete transaction",
            "Cancel transaction"
    });

    private static final Menu productEditMenu = new Menu("Currently viewing [product].", new String[]{
            "Edit name",
            "Edit price",
            "Edit quantity",
    });

    private static final Menu quantityEditMenu = new Menu("Currently viewing [product] quantity.", new String[]{
            "Add quantity",
            "Reduce quantity",
    });

    private static final Menu warehouseEditMenu = new Menu("Currently editing [warehouse] as [user].", new String[]{
            "Edit Warehouse Name",
            "Edit Warehouse Location"
    });

    public static void main(String[] args) {
        {   // INIT BLOCK
            dirs.put("data", "data\\");
            dirs.put("branches", "data\\branches\\");
            dirs.put("suppliers", "data\\suppliers\\");
            dirs.put("warehouses", "data\\warehouses\\");
            dirs.put("accounts", "accounts\\");

            Init.setDirs(new ArrayList<>(dirs.values()));
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
                    String supplierID;

                    System.out.print("Enter supplier id: ");
                    supplierID = sc.nextLine();
                    Supplier temp = new Supplier();
                    try{
                        temp = FileHandler.readObjectFromFile(temp, Main.dirs.get("suppliers") + supplierID + ".json");
                    } catch (JsonProcessingException _) {}

                    if (temp.getId() == null) {
                        System.out.println("ID does not exist.\n");
                        break;
                    }

                    supplierMenu(temp, acc);
                    break;
                }
                case 2: // VIEW WAREHOUSE
                {
                    // prompt for warehouse id
                    //
                    System.out.print("Enter Warehouse id: ");
                    String warehouseID = sc.nextLine();

                    // read from file using directory :
                    // Main.dirs.get("warehouses") + id + ".json";
                    //
                    Warehouse warehouse = new Warehouse();
                    try {
                        // read warehouse from file
                        warehouse = FileHandler.readObjectFromFile(warehouse, Main.dirs.get("warehouses") + warehouseID + ".json");
                    } catch (JsonProcessingException _) {}

                    if (warehouse.getId() == null) {
                        System.out.println("ID does not exist.\n");
                        break;
                    }
                    warehouseMenu(warehouse, acc);

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

                    System.out.print("Enter Branch id : ");
                    String branchID = sc.nextLine();

                    Branch br = new Branch();
                    try{
                        br = FileHandler.readObjectFromFile(br, Main.dirs.get("branches") + branchID + ".json");
                    } catch (JsonProcessingException _) {

                    }

                    if (br.getId() == null) {
                        System.out.println("ID does not exist.\n");
                        break;
                    }

                    branchMenu(br, acc);

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
                case 5: { // ADD NEW WAREHOUSE
                    String warehouseID;
                    boolean validWarehouseID = false;

                    // loop to check warehouse id
                    do {
                        System.out.print("Enter new Warehouse ID (format WXXXX): ");
                        warehouseID = sc.nextLine();

                        // regex checking
                        if (warehouseID.matches("W\\d{4}")) {
                            // check existence
                            Warehouse temp = new Warehouse();
                            try {
                                temp = FileHandler.readObjectFromFile(temp, Main.dirs.get("warehouses") + warehouseID + ".json");
                                if (temp.getId() != null) {
                                    System.out.println("Warehouse ID already exists!");
                                } else {
                                    validWarehouseID = true;
                                }
                            } catch (JsonProcessingException e) {
                                System.out.println("Error checking warehouse ID!");
                            }
                        } else {
                            System.out.println("Invalid Warehouse ID!");
                        }
                    } while (!validWarehouseID);


                    // input warehouse name
                    System.out.print("Enter Warehouse Name: ");
                    String warehouseName = sc.nextLine();

                    // validate input
                    if (warehouseName.isEmpty()) {
                        System.out.println("Warehouse name cannot be empty!");
                        break;
                    }

                    // input warehouse location
                    Address warehouseAddress = new Address();

                    // enter unit no and validate
                    System.out.print("Enter Unit No: ");
                    String unitNo = sc.nextLine();
                    if (unitNo.isEmpty()) {
                        System.out.println("Unit No cannot be empty.");
                        break;
                    }
                    warehouseAddress.setUnit(unitNo);

                    // enter new building and validate
                    System.out.print("Enter Building: ");
                    String building = sc.nextLine();
                    if (building.isEmpty()) {
                        System.out.println("Building name cannot be empty.");
                        break;
                    }
                    warehouseAddress.setBuilding(building);

                    // enter new street and validate
                    System.out.print("Enter Street: ");
                    String street = sc.nextLine();
                    if (street.isEmpty()) {
                        System.out.println("Street name cannot be empty.");
                        break;
                    }
                    warehouseAddress.setStreet(street);

                    // enter new town and validate
                    System.out.print("Enter Town: ");
                    String town = sc.nextLine();
                    if (town.isEmpty()) {
                        System.out.println("Town name cannot be empty.");
                        break;
                    }
                    warehouseAddress.setTown(town);

                    // enter new state and validate
                    System.out.print("Enter State: ");
                    String state = sc.nextLine();
                    if (state.isEmpty()) {
                        System.out.println("State name cannot be empty.");
                        break;
                    }
                    warehouseAddress.setState(state);

                    // enter new postal code and validate
                    System.out.print("Enter Postal Code: ");
                    int postalCode = sc.nextInt();
                    if (postalCode <= 0 || postalCode > 999999) {
                        System.out.println("Postal code must be a positive number and less than 6 digits.");
                        break;
                    }
                    warehouseAddress.setPostalCode(postalCode);
                    sc.nextLine(); //consume buffer
                    // check to confirm to save
                    String confirm;
                    System.out.println("\nPlease confirm the new Warehouse details:");
                    System.out.println("Warehouse ID: " + warehouseID);
                    System.out.println("Warehouse Name: " + warehouseName);
                    System.out.println("Warehouse Location: " + warehouseAddress);

                    // prompt for user input
                    do {
                        System.out.print("Do you want to save this Warehouse? (Y/N): ");
                        confirm = sc.nextLine().toLowerCase();
                    } while (!confirm.equals("y") && !confirm.equals("n"));

                    if (confirm.equals("y")) {
                        // save new warehouse to file
                        Warehouse newWarehouse = new Warehouse(warehouseID, warehouseName, warehouseAddress);
                        try {
                            FileHandler.writeObjectToFile(newWarehouse, Main.dirs.get("warehouses") + warehouseID + ".json");
                            System.out.println("New Warehouse created successfully!");
                        } catch (JsonProcessingException e) {
                            System.out.println("Error saving the new Warehouse to file!");
                        }
                    } else {
                        System.out.println("Warehouse creation cancelled!");
                    }

                    break;
                }

                case 6: { // ADD NEW Branch
                    System.out.print("Enter ID for new branch > ");
                    String branchID = sc.nextLine();

                    // Check if branch with the same ID already exists
                    try {
                        Branch br = new Branch();
                        br = FileHandler.readObjectFromFile(br, Main.dirs.get("branches") + branchID + ".json");
                        if (br.getId() != null) {
                            System.out.println("ID already taken.");
                            break;
                        }
                    } catch (JsonProcessingException _) {}

                    System.out.print("Enter name for branch > ");
                    String branchName = sc.nextLine();

                    System.out.println("Enter location for branch,");
                    Address branchLocation = new Address();
                    System.out.print("\tUnit > ");
                    branchLocation.setUnit(sc.nextLine());
                    System.out.print("\tBuilding > ");
                    branchLocation.setBuilding(sc.nextLine());
                    System.out.print("\tStreet > ");
                    branchLocation.setStreet(sc.nextLine());
                    System.out.print("\tTown > ");
                    branchLocation.setTown(sc.nextLine());
                    System.out.print("\tState > ");
                    branchLocation.setState(sc.nextLine());
                    System.out.print("\tPostal Code > ");
                    branchLocation.setPostalCode(sc.nextInt());
                    sc.nextLine();  // Consume the newline

                    Branch br = new Branch(branchID, branchName, branchLocation, new Product[]{});

                    try {
                        // Save the new branch to a file
                        FileHandler.writeObjectToFile(br, Main.dirs.get("branches") + branchID + ".json");
                        System.out.println("Branch added successfully!");
                    } catch (JsonProcessingException _) {
                        System.out.println("Error while saving the new branch.");
                    }

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

                    break;
                }
                case 2: // EDIT INFO
                {
                    supplierEditMenu(sup);
                    // write file in editmenu
                    break;
                }
                case 3: // DISPLAY PRODUCTS
                {
                    //i think ok
                    sup.printInventory(sup);

                    break;
                }
                case 4: // ADD PRODUCT
                {
                    System.out.print("Enter product name > ");
                    String name = sc.nextLine();

                    double price;
                    while(true){
                        try {
                            System.out.print("Enter product price > RM");
                            price = Double.parseDouble(sc.nextLine());
                            if(price<=0){
                                System.out.println("Not a valid product price!");
                                continue;
                            }
                            break;
                        }catch(Exception e){
                            System.out.println("Not a valid price value.");
                        }
                    }


                    int quantity;
                    while(true){
                        try{
                            System.out.println("Enter product quantity > ");
                            quantity = Integer.parseInt(sc.nextLine());
                            if(quantity<0){
                                System.out.println("Not a valid product quantity!");
                                continue;
                            }
                            break;
                        }catch(Exception e){
                            System.out.println("Not a valid quantity value.");
                        }
                    }

                    sup.addProduct(new Product(name, price, quantity));

                    try {
                        FileHandler.writeObjectToFile(sup, Main.dirs.get("suppliers") + sup.getId() + ".json");
                    } catch (JsonProcessingException _) {}

                    break;
                }
                case 5: // EDIT PRODUCT
                {
                    System.out.print("Enter product name > ");
                    String name = sc.nextLine();
                    if (sup.getProductByName(name) == null){
                        System.out.println("Product does not exist");
                        break;
                    }

                    productEditMenu(sup.getProductByName(name));
                    try {
                        FileHandler.writeObjectToFile(sup, Main.dirs.get("suppliers") + sup.getId() + ".json");
                    } catch (JsonProcessingException _) {}

                    break;
                }
                case 6: // REMOVE PRODUCT
                {
                    System.out.print("Enter product name to be removed > ");
                    String name = sc.nextLine();
                    if (sup.getProductByName(name) == null){
                        System.out.println("Product does not exist");
                        break;
                    }
                    System.out.println("Product name: "+ sup.getProductByName(name).getName());
                    System.out.println("Product price: "+ sup.getProductByName(name).getPrice());
                    System.out.println("Product quantity: "+ sup.getProductByName(name).getQuantity());
                    System.out.println("Are you sure to remove this product? ");
                    System.out.println("(Y/N)");
                    char answer;

                    while(true){
                        answer = sc.nextLine().charAt(0);
                        answer = Character.toUpperCase(answer);
                        if (answer == 'Y') {
                            sup.removeProductByName(name);
                            try {
                                FileHandler.writeObjectToFile(sup, Main.dirs.get("suppliers") + sup.getId() + ".json");
                            } catch (JsonProcessingException _) {}
                            break;
                        } else if (answer == 'N') {
                            System.out.println("Remove aborted");
                            break;
                        }
                        else{
                            System.out.println("Invalid choice");

                        }
                    }

                    break;
                }
                case 7: // TRANSACTION MENU
                {
                    transactionMenu(sup, acc);

                    try {
                        FileHandler.writeObjectToFile(sup, Main.dirs.get("suppliers") + sup.getId() + ".json");
                    } catch (JsonProcessingException _) {}

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
                    System.out.println("Warehouse ID: " + war.getId());
                    System.out.println("Warehouse Name: " + war.getName());
                    System.out.println("Warehouse Location: " + war.getLocation());
                    System.out.println("Total products: " + war.getItems().size());
                    System.out.println("Total value of inventory: RM" + war.totalValue());

                    // display subscribed suppliers if available
                    if (!war.getSuppliers().isEmpty()) {
                        System.out.println("Subscribed Suppliers:");
                        for (Supplier supplier : war.getSuppliers()) {
                            System.out.println("- " + supplier.getName() + " (ID: " + supplier.getId() + ")");
                        }
                    } else {
                        System.out.println("No subscribed suppliers!");
                    }

                    break;
                }
                case 2: // EDIT INFO
                {
                    // edit warehouse function
                    warehouseEditMenu(war, acc);
                    break;
                }
                case 3: // DISPLAY PRODUCTS
                {
                    war.printInventory(war);
                    break;
                }
                case 4: // ADD PRODUCT
                {
                    // add product function
                    warehouseAddProduct(war);
                    break;
                }
                case 5: // EDIT PRODUCT
                {
                    // edit product function
                    warehouseEditProduct(war);
                    break;
                }
                case 6: // REMOVE PRODUCT
                {
                    // remove product function
                    warehouseRemoveProduct(war);
                    break;
                }
                case 7: // DISPLAY SUBSCRIBED SUPPLIERS
                {
                    // check if there is supplier
                    if (war.getSuppliers().isEmpty()) {
                        System.out.println("No subscribed suppliers!");
                    } else {
                        // table header
                        System.out.printf("%-15s %-20s %-30s\n", "Supplier ID", "Supplier Name", "Location");
                        System.out.println("--------------------------------------------------------------");

                        // print supplier
                        for (Supplier supplier : war.getSuppliers()) {
                            System.out.printf("%-15s %-20s %-30s\n", supplier.getId(), supplier.getName(), supplier.getLocation());
                        }
                    }

                    System.out.println();
                    break;
                }
                case 8: // SUBSCRIBE SUPPLIER
                {
                    // prompt id, existence check, append to subscribed and save file
                    // subscribe function
                    warehouseSubscribeSupplier(war);
                    break;
                }
                case 9: // UNSUBSCRIBE SUPPLIER
                {
                    // prompt id, existence check, remove from subscribed and save file
                    // unsubscribe function
                    warehouseUnsubscribeSupplier(war);
                    break;
                }
                case 10: // TRANSACTION MENU
                {
                    transactionMenu(war, acc);

                    try {
                            FileHandler.writeObjectToFile(war, Main.dirs.get("warehouses") + war.getId() + ".json");
                    } catch (JsonProcessingException _) {

                        return;
                    }

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
                    System.out.println("Branch id : " + br.getId());
                    System.out.println("Branch name : " + br.getName());
                    System.out.println("Location : " + br.getLocation());
                    break;
                }
                case 2: // EDIT INFO
                {
                    branchEditMenu(br);
                    break;
                }
                case 3: // DISPLAY PRODUCTS
                {
                    br.printInventory(br);
                    break;
                }
                case 4: // ADD PRODUCT
                {
                    System.out.print("Enter product name > ");
                    String name = sc.nextLine();


                    double price;
                    while(true){
                        try{
                            System.out.print("Enter product price > RM");
                            price = Double.parseDouble(sc.nextLine());
                            if(price<=0){
                                System.out.println("Invalid price");
                                continue;
                            }
                            break;

                        }catch(Exception e){
                            System.out.println("Invalid price");

                        }
                    }

                    int quantity;
                    while(true){
                        try{
                            System.out.println("Enter quantity > ");
                            quantity = Integer.parseInt(sc.nextLine());
                            if(quantity<0){
                                System.out.println("Invalid quantity");
                                continue;
                            }
                            break;
                        }catch(Exception e){
                            System.out.println("Invalid quantity");
                        }
                    }

                    br.addProduct(new Product(name, price, quantity));

                    try {
                        FileHandler.writeObjectToFile(br, Main.dirs.get("branches") + br.getId() + ".json");
                    } catch (JsonProcessingException _) {}

                    break;
                }
                case 5: // EDIT PRODUCT
                {
                    System.out.print("Enter product name > ");
                    String name = sc.nextLine();
                    try{
                        br.getProductByName(name);
                    }catch(NullPointerException _){
                        System.out.println("Product does not exist");
                        return;
                    }

                    productEditMenu(br.getProductByName(name));
                    try {
                        FileHandler.writeObjectToFile(br, Main.dirs.get("branches") + br.getId() + ".json");
                    } catch (JsonProcessingException _) {}

                    break;
                }
                case 6: // REMOVE PRODUCT
                {
                    System.out.print("Enter product name to be removed > ");
                    String name = sc.nextLine();
                    if (br.getProductByName(name) == null){
                        System.out.println("Product does not exist");
                        break;
                    }
                    System.out.println("Product name: "+ br.getProductByName(name).getName());
                    System.out.println("Product price: "+ br.getProductByName(name).getPrice());
                    System.out.println("Product quantity: "+ br.getProductByName(name).getQuantity());
                    System.out.println("Are you sure to remove this product? ");
                    System.out.println("(Y/N)");
                    char answer;

                    while(true){
                        answer = sc.nextLine().charAt(0);
                        answer = Character.toUpperCase(answer);
                        if (answer == 'Y') {
                            br.removeProductByName(name);
                            try {
                                FileHandler.writeObjectToFile(br, Main.dirs.get("branches") + br.getId() + ".json");
                            } catch (JsonProcessingException _) {}
                            break;
                        } else if (answer == 'N') {
                            System.out.println("Remove aborted");
                            break;
                        }
                        else{
                            System.out.println("Invalid choice");
                        }
                    }
                    break;
                }
                case 7: // DISPLAY SUBSCRIBED WAREHOUSES
                {
                    if (br.getWarehouses().isEmpty()) {
                        System.out.println("No subscribed suppliers!");
                        break;
                    }

                    System.out.println("Subscribed Warehouses");
                    for (Warehouse warehouse : br.getWarehouses()){
                        System.out.println("Warehouse ID: " + warehouse.getId());
                        System.out.println("Warehouse Name: " + warehouse.getName());
                        System.out.println("Location: " + warehouse.getLocation().toString());
                        System.out.println("===================================");
                    }
                    break;
                }
                case 8: // SUBSCRIBE WAREHOUSE
                {
                    // prompt id, existence check, append to subscribed and save file

                    branchSubscribeWarehouseById(br);
                    break;
                }
                case 9: // UNSUBSCRIBE WAREHOUSE
                {
                    // prompt id, existence check, remove from subscribed and save file
                    branchUnsubscribeWarehouseByID(br);

                    //System.out.print("Enter the warehouse id to unsubscribe: ");
                    //String warehouseId = sc.nextLine();
//
                    //// Attempt to unsubscribe by ID
                    //br.unsubscribeWarehouseById(warehouseId);
                    //System.out.println("Unsubscribed warehouse by ID: " + warehouseId);
//
                    //// Save the updated branch data
                    //try {
                    //    FileHandler.writeObjectToFile(br, Main.dirs.get("branch") + br.getId() + ".json");
                    //} catch (JsonProcessingException e) {
                    //    System.out.println("Failed to save branch data.");
                    //}

                    break;
                }
                case 10: // TRANSACTION MENU
                {
                    transactionMenu(br, acc);

                    try {
                        FileHandler.writeObjectToFile(br, Main.dirs.get("branches") + br.getId() + ".json");
                    } catch (JsonProcessingException _) {
                        return;
                    }

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
                                br = FileHandler.readObjectFromFile(br, Main.dirs.get("branches") + buyerID + ".json");
                            } catch (JsonProcessingException _) {}
                            break;
                        }
                        default:
                            System.out.println("ID does not exist.");
                            break;
                    }

                    if (sup.getName() == null && war.getName() == null && br.getName() == null) {
                        System.out.println("ID does not exist.");
                        break;
                    }

                    //create items list
                    ArrayList<Product> items = new ArrayList<>();

                    char cont = 'y';
                    while (cont == 'y') {
                        System.out.print("Enter product name > ");
                        String name = sc.nextLine();


                        double price;
                        while(true){
                            System.out.print("Enter product price > RM");
                            try{
                                price = Double.parseDouble(sc.nextLine());
                                if(price<=0){
                                    System.out.println("Invalid price");
                                    continue;
                                }
                                break;
                            }catch(Exception e){
                                System.out.println("Invalid price");
                            }
                        }

                        int quantity;
                        while(true){
                            System.out.println("Enter quantity > ");
                            try{
                                quantity = Integer.parseInt(sc.nextLine());
                                if(quantity<=0){
                                    System.out.println("Invalid quantity");
                                    continue;
                                }
                                break;
                            }catch(Exception e){
                                System.out.println("Invalid quantity");
                            }
                        }


                        boolean possibleTransaction = false;
                        for (Product prod : inv.getItems()) {
                            if (prod.getName().equals(name) && prod.getPrice() == price) {
                                if (prod.getQuantity() < quantity) {
                                    System.out.println("Not enough stock to perform transaction.");
                                    break;
                                }

                                possibleTransaction = true;
                                break;
                            }
                        }

                        if (!possibleTransaction) {
                            System.out.println("Product does not exist.");
                            continue;
                        }

                        boolean existsInTransaction = false;
                        for (Product prod : items) {
                            if (prod.getName().equals(name) && prod.getPrice() == price) {
                                prod.addQuantity(quantity);
                                existsInTransaction = true;
                                break;
                            } else if (prod.getName().equals(name) && prod.getPrice() != price) {
                                System.out.println("Product already exists within transaction with different price.");
                                existsInTransaction = true;
                                break;
                            }
                        }
                        if (!existsInTransaction) items.addLast(new Product(name, price, quantity));

                        System.out.print("Add more? (Y/N) > ");
                        String temp = sc.nextLine();
                        cont = Character.toLowerCase(temp.charAt(0));
                    }

                    //create a new transaction
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy-HHmmss");
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
                        if (transaction.getId().equals(tranID)) {
                            tran = transaction;
                            break;
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

                    transactionUpdateMenu.setHeader("Updating transaction [" + tran.getId() + "].");
                    int updateType = -1;
                    while (updateType != 0) {
                        tran.printTransaction();
                        System.out.println();
                        updateType = transactionUpdateMenu.prompt();

                        switch (updateType) {
                            case 1: // COMPLETE TRANSACTION
                            {
                                // check if is incoming
                                if (!tran.getBuyerID().equals(inv.getLog().getOwnerID())) {
                                    System.out.println("Can only complete incoming transactions.");
                                    break;
                                }

                                System.out.printf("Payment of RM%.2f.\n", tran.getPayment().getAmount());
                                System.out.print("Proceed with payment? (Y/N) > ");
                                char temp = Character.toLowerCase(sc.nextLine().charAt(0));

                                if (temp != 'y') {
                                    System.out.println("Payment cancelled.");
                                    break;
                                }

                                //find buyer and add items to buyer
                                Supplier supS = new Supplier();
                                Warehouse warS = new Warehouse();
                                Branch brS = new Branch();
                                switch (tran.getSellerID().charAt(0)) {
                                    case 'S':
                                    {
                                        try {
                                            supS = FileHandler.readObjectFromFile(supS, Main.dirs.get("suppliers") + tran.getSellerID() + ".json");

                                            for (Product item : tran.getItems()) {
                                                supS.getProductByName(item.getName()).reduceQuantity(item.getQuantity());
                                                inv.addProduct(item);
                                            }

                                            for (Transaction transaction : supS.getLog().getLog()) {
                                                if (transaction.getId().equals(tran.getId())) {
                                                    tran.getPayment().succeed();
                                                    tran.succeed();
                                                    transaction.getPayment().succeed();
                                                    transaction.succeed();
                                                    break;
                                                }
                                            }

                                            FileHandler.writeObjectToFile(supS, Main.dirs.get("suppliers") + tran.getSellerID() + ".json");
                                        } catch (JsonProcessingException _) {}
                                        break;
                                    }
                                    case 'W':
                                    {
                                        try {
                                            warS = FileHandler.readObjectFromFile(warS, Main.dirs.get("suppliers") + tran.getSellerID() + ".json");

                                            for (Product item : tran.getItems()) {
                                                warS.getProductByName(item.getName()).reduceQuantity(item.getQuantity());
                                                inv.addProduct(item);
                                            }

                                            for (Transaction transaction : warS.getLog().getLog()) {
                                                if (transaction.getId().equals(tran.getId())) {
                                                    tran.getPayment().succeed();
                                                    tran.succeed();
                                                    transaction.getPayment().succeed();
                                                    transaction.succeed();
                                                    break;
                                                }
                                            }

                                            FileHandler.writeObjectToFile(warS, Main.dirs.get("suppliers") + tran.getSellerID() + ".json");
                                        } catch (JsonProcessingException _) {}
                                        break;
                                    }
                                    case 'B':
                                    {
                                        try {
                                            brS = FileHandler.readObjectFromFile(brS, Main.dirs.get("suppliers") + tran.getSellerID() + ".json");

                                            for (Product item : tran.getItems()) {
                                                brS.getProductByName(item.getName()).reduceQuantity(item.getQuantity());
                                                inv.addProduct(item);
                                            }

                                            for (Transaction transaction : brS.getLog().getLog()) {
                                                if (transaction.getId().equals(tran.getId())) {
                                                    tran.getPayment().succeed();
                                                    tran.succeed();
                                                    transaction.getPayment().succeed();
                                                    transaction.succeed();
                                                    break;
                                                }
                                            }

                                            FileHandler.writeObjectToFile(brS, Main.dirs.get("suppliers") + tran.getSellerID() + ".json");
                                        } catch (JsonProcessingException _) {}
                                        break;
                                    }
                                    default:
                                        break;
                                }

                                System.out.println("Payment completed.");
                                System.out.println("Transaction completed.");
                                updateType = 0;

                                break;
                            }
                            case 2: // CANCEL TRANSACTION
                            {
                                tran.getPayment().fail();
                                tran.fail();

                                // find buyer and fail transaction
                                Supplier sup = new Supplier();
                                Warehouse war = new Warehouse();
                                Branch br = new Branch();
                                switch (tran.getBuyerID().charAt(0)) {
                                    case 'S':
                                    {
                                        try {
                                            sup = FileHandler.readObjectFromFile(sup, Main.dirs.get("suppliers") + tran.getBuyerID() + ".json");
                                            for (Transaction transaction : sup.getLog().getLog()) {
                                                if (transaction.getId().equals(tranID)) {
                                                    transaction.getPayment().fail();
                                                    transaction.fail();
                                                }
                                            }
                                            FileHandler.writeObjectToFile(sup, Main.dirs.get("suppliers") + tran.getBuyerID() + ".json");
                                        } catch (JsonProcessingException _) {}
                                        break;
                                    }
                                    case 'W':
                                    {
                                        try {
                                            war = FileHandler.readObjectFromFile(war, Main.dirs.get("warehouses") + tran.getBuyerID() + ".json");
                                            for (Transaction transaction : war.getLog().getLog()) {
                                                if (transaction.getId().equals(tranID)) {
                                                    transaction.getPayment().fail();
                                                    transaction.fail();
                                                }
                                            }
                                            FileHandler.writeObjectToFile(war, Main.dirs.get("warehouses") + tran.getBuyerID() + ".json");
                                        } catch (JsonProcessingException _) {}
                                        break;
                                    }
                                    case 'B':
                                    {
                                        try {
                                            br = FileHandler.readObjectFromFile(br, Main.dirs.get("branches") + tran.getBuyerID() + ".json");
                                            for (Transaction transaction : br.getLog().getLog()) {
                                                if (transaction.getId().equals(tranID)) {
                                                    transaction.getPayment().fail();
                                                    transaction.fail();
                                                }
                                            }
                                            FileHandler.writeObjectToFile(br, Main.dirs.get("branches") + tran.getBuyerID() + ".json");
                                        } catch (JsonProcessingException _) {}
                                        break;
                                    }
                                    default:
                                        break;
                                }

                                System.out.println("Transaction cancelled.");
                                break;
                            }
                        }
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
                    for (Transaction transaction : inv.getLog().outgoing()) {
                        transaction.printTransaction();
                    }

                    break;
                }
                case 5: // DISPLAY INCOMING TRANSACTIONS
                {
                    for (Transaction transaction : inv.getLog().incoming()) {
                        transaction.printTransaction();
                    }

                    break;
                }
                case 6: // DISPLAY PENDING TRANSACTIONS
                {
                    for (Transaction transaction : inv.getLog().getLog()) {
                        if (!transaction.getStatus().equals("PENDING")) continue;
                        transaction.printTransaction();
                    }

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
                   System.out.println("Successfully updated supplier name");
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

                    // enter new unit no and validate
                    System.out.print("Enter new Unit No: ");
                    String unitNo = sc.nextLine();
                    if (unitNo.isEmpty()) {
                        System.out.println("Unit No cannot be empty.");
                        break;
                    }
                    temp.setUnit(unitNo);

                    // enter new building and validate
                    System.out.print("Enter new Building: ");
                    String building = sc.nextLine();
                    if (building.isEmpty()) {
                        System.out.println("Building name cannot be empty.");
                        break;
                    }
                    temp.setBuilding(building);

                    // enter new street and validate
                    System.out.print("Enter new Street: ");
                    String street = sc.nextLine();
                    if (street.isEmpty()) {
                        System.out.println("Street name cannot be empty.");
                        break;
                    }
                    temp.setStreet(street);

                    // enter new town and validate
                    System.out.print("Enter new Town: ");
                    String town = sc.nextLine();
                    if (town.isEmpty()) {
                        System.out.println("Town name cannot be empty.");
                        break;
                    }
                    temp.setTown(town);

                    // enter new state and validate
                    System.out.print("Enter new State: ");
                    String state = sc.nextLine();
                    if (state.isEmpty()) {
                        System.out.println("State name cannot be empty.");
                        break;
                    }
                    temp.setState(state);
                    int postalCode;
                    // enter new postal code and validate
                    while(true){
                        System.out.print("Enter new Postal Code: ");
                        try{
                            postalCode = Integer.parseInt(sc.nextLine());
                            if (postalCode <= 0 || postalCode > 999999) {
                                System.out.println("Postal code must be a positive number and less than 6 digits.");
                                continue;
                            }
                            break;

                        }catch(Exception e){
                            System.out.println("Not a valid postal code.");
                        }
                    }

                    temp.setPostalCode(postalCode);

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

    private static void productEditMenu(Product prod) {
        productEditMenu.setHeader(String.format("Edit Product menu for [%s].", prod.getName()));


        int choice = -1;
        while (choice != 0) {


            System.out.println("Product name: " + prod.getName());
            System.out.println("Product price: " + prod.getPrice());
            System.out.println("Product quantity: " + prod.getQuantity());
            choice = productEditMenu.prompt();

            switch (choice) {
                case 1: // EDIT NAME
                {
                    System.out.print("New product name: ");
                    prod.setName(sc.nextLine());
                    break;
                }
                case 2: // EDIT PRICE
                {

                    double price;
                    while(true){
                        System.out.println("Enter new product price > ");
                        try{
                            price = Double.parseDouble(sc.nextLine());
                            if(price <= 0) {
                                System.out.println("Invalid price!");
                                continue;
                            }
                            break;
                        }catch(Exception e){
                            System.out.println("Not a valid price.");
                        }
                    }

                    char answer;

                    while(true) {
                        System.out.println("Confirm to edit price?(Y/N)");
                        answer = sc.nextLine().charAt(0);
                        answer = Character.toUpperCase(answer);
                        if (answer == 'Y') {
                            prod.setPrice(price);
                            break;
                        } else if (answer == 'N') {
                            System.out.println("Remove aborted");
                            break;
                        } else {
                            System.out.println("Invalid choice");
                        }
                    }
                    break;
                }
                case 3: //Edit quantity
                {
                    quantityEditMenu(prod);
                    break;
                }

                default:
                    break;
            }
        }
    }
    private static void quantityEditMenu(Product prod) {
        quantityEditMenu.setHeader(String.format("Editing quantity for pdocut [%s].", prod.getName()));


        int choice = -1;
        while (choice != 0) {
            choice = quantityEditMenu.prompt();

            switch (choice) {
                case 1: //Add quantity
                {
                    System.out.print("Quantity to add: ");
                    int quantity=0;
                    try{
                        quantity = Integer.parseInt(sc.nextLine());
                    }catch(Exception e){
                        System.out.println("Not a valid quantity. Press any key to continue.");
                        sc.nextLine();
                        Main.clearScreen();
                    }
                    char answer;
                    while(true) {
                        System.out.println("Confirm to add quantity?(Y/N)");
                        answer = sc.nextLine().charAt(0);
                        answer = Character.toUpperCase(answer);
                        if (answer == 'Y') {
                            prod.addQuantity(quantity);
                            break;
                        } else if (answer == 'N') {
                            System.out.println("Add quantity reverted");
                            break;
                        } else {
                            System.out.println("Invalid choice");
                        }
                    }
                    break;
                }
                case 2: // reduce quantity
                {
                    System.out.print("Quantity to reduce: ");
                    int quantity=0;
                    try{
                        quantity = Integer.parseInt(sc.nextLine());
                    }catch(Exception e){
                        System.out.println("Not a valid quantity. Press any key to continue.");
                        sc.nextLine();
                        Main.clearScreen();
                    }
                    char answer;
                    while(true) {
                        System.out.println("Confirm to reduce quantity?(Y/N)");
                        answer = sc.nextLine().charAt(0);
                        answer = Character.toUpperCase(answer);
                        if (answer == 'Y') {
                            prod.reduceQuantity(quantity);
                            break;
                        } else if (answer == 'N') {
                            System.out.println("Reduce quantity reverted");
                            break;
                        } else {
                            System.out.println("Invalid choice");
                        }
                    }
                    break;
                }

                default:
                    break;
            }
        }
    }

    private static void warehouseEditMenu(Warehouse war, Account acc) {
        // display menu
        warehouseEditMenu.setHeader(String.format("Currently editing [%s] as [%s].", war.getId(), acc.getUsername()));
        int editChoice = -1;

        while (editChoice != 0) {
            editChoice = warehouseEditMenu.prompt();

            switch (editChoice) {
                case 1: { // change warehouse name
                    System.out.println("Current Warehouse Name: " + war.getName());
                    System.out.print("Enter new warehouse name: ");
                    String newName = sc.nextLine();

                    // validate name
                    if (newName.isEmpty()) {
                        System.out.println("Enter a valid warehouse name!");
                        break;
                    }

                    // check to confirm
                    String confirm;
                    System.out.println("New Warehouse Name: " + newName);
                    do {
                        System.out.print("Are you sure you want to update the name? (Y/N): ");
                        confirm = sc.nextLine().toLowerCase();
                    } while (!confirm.equals("y") && !confirm.equals("n"));

                    if (confirm.equals("y")) {
                        war.setName(newName);
                        try {
                            FileHandler.writeObjectToFile(war, Main.dirs.get("warehouses") + war.getId() + ".json");
                            System.out.println("Warehouse name updated successfully.");
                        } catch (JsonProcessingException e) {
                            System.out.println("Error updating warehouse name.");
                        }
                    } else {
                        System.out.println("Warehouse name update cancelled.");
                    }

                    break;
                }

                case 2: { // change warehouse address
                    Address newAddress = new Address();
                    System.out.println("Current Warehouse Location: " + war.getLocation());

                    System.out.println("\nNew Warehouse Location: ");

                    // enter new unit no and validate
                    System.out.print("Enter new Unit No: ");
                    String unitNo = sc.nextLine();
                    if (unitNo.isEmpty()) {
                        System.out.println("Unit No cannot be empty.");
                        break;
                    }
                    newAddress.setUnit(unitNo);

                    // enter new building and validate
                    System.out.print("Enter new Building: ");
                    String building = sc.nextLine();
                    if (building.isEmpty()) {
                        System.out.println("Building name cannot be empty.");
                        break;
                    }
                    newAddress.setBuilding(building);

                    // enter new street and validate
                    System.out.print("Enter new Street: ");
                    String street = sc.nextLine();
                    if (street.isEmpty()) {
                        System.out.println("Street name cannot be empty.");
                        break;
                    }
                    newAddress.setStreet(street);

                    // enter new town and validate
                    System.out.print("Enter new Town: ");
                    String town = sc.nextLine();
                    if (town.isEmpty()) {
                        System.out.println("Town name cannot be empty.");
                        break;
                    }
                    newAddress.setTown(town);

                    // enter new state and validate
                    System.out.print("Enter new State: ");
                    String state = sc.nextLine();
                    if (state.isEmpty()) {
                        System.out.println("State name cannot be empty.");
                        break;
                    }
                    newAddress.setState(state);
                    int postalCode =0;
                    // enter new postal code and validate
                    while(true){
                        System.out.print("Enter new Postal Code: ");

                        try{
                            postalCode = Integer.parseInt(sc.nextLine());
                            if(postalCode<=0 || postalCode> 99999){
                                System.out.println("Invalid postal code");
                                continue;
                            }

                            break;
                        }catch(Exception e){
                            System.out.println("Not a valid postal code.");
                        }
                        newAddress.setPostalCode(postalCode);

                        break;
                    }
                    if (postalCode <= 0 || postalCode > 999999) {
                        System.out.println("Postal code must be a positive number and less than 6 digits.");
                        break;
                    }
                    newAddress.setPostalCode(postalCode);

                    // check to confirm to change
                    String confirm;
                    System.out.println("\nNew Address:");
                    System.out.println(newAddress);
                    do {
                        System.out.print("Are you sure you want to update the address? (Y/N): ");
                        confirm = sc.nextLine().toLowerCase();
                    } while (!confirm.equals("y") && !confirm.equals("n"));

                    if (confirm.equals("y")) {
                        war.setLocation(newAddress);
                        try {
                            FileHandler.writeObjectToFile(war, Main.dirs.get("warehouses") + war.getId() + ".json");
                            System.out.println("Warehouse location updated successfully.");
                        } catch (JsonProcessingException e) {
                            System.out.println("Error updating warehouse location.");
                        }
                    } else {
                        System.out.println("Saving cancelled!");
                    }

                    break;
                }

                default: {
                    System.out.println("Please enter a valid option!\n");
                    break;
                }
            }
        }
    }

    private static void warehouseAddProduct(Warehouse war) {
        char cont = 'y';

        // continue loop to add product
        do {
            System.out.print("Enter new product name: ");
            String name = sc.nextLine();

            double price;
            while(true){
                try {
                    System.out.print("Enter new product price: RM");
                    price = Double.parseDouble(sc.nextLine());
                    if(price<=0){
                        System.out.println("Not a valid product price!");
                        continue;
                    }
                    break;
                }catch(Exception e){
                    System.out.println("Not a valid price value.");
                }
            }

            int quantity;
            while(true){
                try {
                    System.out.print("Enter new product quantity: ");
                    quantity = Integer.parseInt(sc.nextLine());
                    break;
                }catch(Exception e){
                    System.out.println("Not a valid quantity value.");
                }
            }
            // validate the inputs
            if (name.isEmpty()) {
                System.out.println("Invalid product information!\n");
                break;
            }

            // check to confirm to add
            String confirmation;
            System.out.printf("%-20s %-10s %-10s\n", "Product Name", "Price", "Quantity");
            System.out.printf("%-20s RM%-10.2f %-10d\n", name, price, quantity);
            do {
                System.out.print("Do you want to add this product? (Y/N): ");
                confirmation = sc.nextLine().trim().toLowerCase();
            } while (!confirmation.equals("y") && !confirmation.equals("n"));

            if (confirmation.equals("y")) {
                // save new product to warehouse
                war.addProduct(new Product(name, price, quantity));

                try {
                    FileHandler.writeObjectToFile(war, Main.dirs.get("warehouses") + war.getId() + ".json");
                    System.out.println("Product added successfully.");
                } catch (JsonProcessingException e) {
                    System.out.println("Error saving product to warehouse.");
                }
            } else {
                System.out.println("Product addition canceled.");
                break;
            }

            // check if user want to add more
            String response;
            do {
                System.out.print("Do you want to add another product? (Y/N): ");
                response = sc.nextLine().toLowerCase();
                if (!response.equals("y") && !response.equals("n")) {
                    System.out.println("Invalid input. Please enter 'Y' for yes or 'N' for no.");
                }
            } while (!response.equals("y") && !response.equals("n"));

            // continue if the user answers 'y'
            cont = response.charAt(0);
        } while (cont == 'y');

        System.out.println();
    }

    private static void warehouseEditProduct(Warehouse war) {
        char cont = 'y';

        // loop to edit more product
        do {
            System.out.print("Enter the product name to edit: ");
            String productName = sc.nextLine();

            // check if the product exists
            Product product = war.getProductByName(productName);
            if (product == null) {
                System.out.println("Product not found. Please try again.\n");
                break;
            }

            // display editing product details
            System.out.printf("\nEditing Product: %s\n", product.getName());
            System.out.printf("%-20s %-15s %-10s\n", "Product Name", "Price", "Quantity");
            System.out.println("=============================================");
            System.out.printf("%-20s RM%-13.2f %-10d\n", product.getName(), product.getPrice(), product.getQuantity());

            // ask if user wants to edit product
            String editConfirmation;
            do {
                System.out.print("Do you want to edit this product? (Y/N): ");
                editConfirmation = sc.nextLine().toLowerCase();
            } while (!editConfirmation.equals("y") && !editConfirmation.equals("n"));

            if (editConfirmation.equals("y")) {
                // get existing product details
                String newName = product.getName();
                double newPrice = product.getPrice();
                int newQuantity = product.getQuantity();

                // edit product name
                System.out.print("Enter new product name (or press Enter to keep the current name): ");
                String nameInput = sc.nextLine();
                if (!nameInput.isEmpty()) {
                    newName = nameInput;
                }

                // edit product price
                System.out.print("Enter new product price (or press Enter to keep the current price): RM");
                // use string to check input is null or not
                String priceInput = sc.nextLine();
                if (!priceInput.isEmpty()) {
                    try {
                        // parse string into double
                        newPrice = Double.parseDouble(priceInput);

                        //validate input
                        if (newPrice <= 0) {
                            System.out.println("Invalid price!");
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid price format!");
                        break;
                    }
                }

                // edit product quantity
                System.out.print("Enter new product quantity (or press Enter to keep the current quantity): ");
                String quantityInput = sc.nextLine();
                // use string to check input is null or not
                if (!quantityInput.isEmpty()) {
                    try {
                        // parse string into int
                        newQuantity = Integer.parseInt(quantityInput);

                        //validate input
                        if (newQuantity <= 0) {
                            System.out.println("Invalid quantity!");
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid quantity format!");
                        break;
                    }
                }

                // print the new product details and ask for confirmation
                System.out.println("\nEdited product:");
                System.out.printf("%-20s %-15s %-10s\n", "Product Name", "Price", "Quantity");
                System.out.println("=============================================");
                System.out.printf("%-20s RM%-13.2f %-10d\n", newName, newPrice, newQuantity);

                // check to confirm to edit
                String confirmation;
                do {
                    System.out.print("Do you want to save these changes? (Y/N): ");
                    confirmation = sc.nextLine().toLowerCase();
                } while (!confirmation.equals("y") && !confirmation.equals("n"));

                if (confirmation.equals("y")) {
                    product.setName(newName);
                    product.setPrice(newPrice);
                    product.setQuantity(newQuantity);

                    try {
                        FileHandler.writeObjectToFile(war, Main.dirs.get("warehouses") + war.getId() + ".json");
                        System.out.println("Product updated successfully.");
                    } catch (JsonProcessingException e) {
                        System.out.println("Error saving changes to the warehouse.");
                    }
                } else {
                    System.out.println("No changes made to this product.");
                }
            } else {
                System.out.println("Product editing cancelled.");
            }

            // check if user wants to edit another product
            String response;
            do {
                System.out.print("Do you want to edit another product? (Y/N): ");
                response = sc.nextLine().toLowerCase();
            } while (!response.equals("y") && !response.equals("n"));

            // continue if the user answers 'y'
            cont = response.charAt(0);
        } while (cont == 'y');

        System.out.println();
    }

    private static void warehouseRemoveProduct(Warehouse war) {
        System.out.print("Enter the product name to remove: ");
        String productName = sc.nextLine();

        // check if the product exists
        Product product = war.getProductByName(productName);
        if (product != null) {
            // display the product details
            System.out.printf("\nRemoving Product: %s\n", product.getName());
            System.out.printf("%-20s %-15s %-10s\n", "Product Name", "Price", "Quantity");
            System.out.println("=============================================");
            System.out.printf("%-20s RM%-13.2f %-10d\n", product.getName(), product.getPrice(), product.getQuantity());

            // ask for confirmation
            String confirm;
            do {
                System.out.print("Are you sure you want to remove this product? (Y/N): ");
                confirm = sc.nextLine().toLowerCase();
            } while (!confirm.equals("y") && !confirm.equals("n"));

            if (confirm.equals("y")) {
                // remove product
                war.removeProductByName(productName);

                try {
                    FileHandler.writeObjectToFile(war, Main.dirs.get("warehouses") + war.getId() + ".json");
                    System.out.println("Product removed successfully.");
                } catch (JsonProcessingException e) {
                    System.out.println("Error saving changes to the warehouse.");
                }
            } else {
                System.out.println("Product removal cancelled.");
            }
        } else {
            System.out.println("Product not found. Please try again.\n");
        }
    }

    private static void warehouseSubscribeSupplier(Warehouse war) {
        System.out.print("Enter supplier ID to subscribe: ");
        String supplierID = sc.nextLine();

        // check if supplier exists
        Supplier supplier = new Supplier();
        try {
            supplier = FileHandler.readObjectFromFile(supplier, Main.dirs.get("suppliers") + supplierID + ".json");
        } catch (JsonProcessingException _) {}

        if (supplier.getId() != null) {
            // display the supplier details
            System.out.println("\nSupplier to Subscribe:");
            System.out.println("Supplier ID: " + supplier.getId());
            System.out.println("Supplier Name: " + supplier.getName());
            System.out.println("Supplier Location: " + supplier.getLocation());
            System.out.println();

            // ask for confirmation
            String confirm;
            do {
                System.out.print("Do you want to subscribe to this supplier? (Y/N): ");
                confirm = sc.nextLine().toLowerCase();
            } while (!confirm.equals("y") && !confirm.equals("n"));

            if (confirm.equals("y")) {
                // subscribe supplier
                war.subscribeSupplier(supplier);

                // save to file
                try {
                    FileHandler.writeObjectToFile(war, Main.dirs.get("warehouses") + war.getId() + ".json");
                    System.out.println("Supplier subscribed successfully!\n");
                } catch (JsonProcessingException e) {
                    System.out.println("Error subscribing supplier.");
                }
            } else {
                System.out.println("Subscription cancelled!");
            }

        } else {
            System.out.println("Supplier not found!\n");
        }
    }

    private static void warehouseUnsubscribeSupplier(Warehouse war) {
        System.out.print("Enter supplier ID to unsubscribe: ");
        String supplierID = sc.nextLine();

        // find supplier in warehouse
        Supplier supplier = war.getSupplierByID(supplierID);

        // check if supplier exists
        if (supplier != null) {
            // display the supplier details
            System.out.println("\nSupplier Details to Unsubscribe:");
            System.out.println("Supplier ID: " + supplier.getId());
            System.out.println("Supplier Name: " + supplier.getName());
            System.out.println("Supplier Location: " + supplier.getLocation());
            System.out.println();

            // ask for confirmation
            String confirm;
            do {
                System.out.print("Do you want to unsubscribe from this supplier? (Y/N): ");
                confirm = sc.nextLine().toLowerCase();
            } while (!confirm.equals("y") && !confirm.equals("n"));

            if (confirm.equals("y")) {
                // unsubscribe supplier
                war.unsubscribeSupplierByID(supplierID);

                // save to file
                try {
                    FileHandler.writeObjectToFile(war, Main.dirs.get("warehouses") + war.getId() + ".json");
                    System.out.println("Supplier unsubscribed successfully!\n");
                } catch (JsonProcessingException e) {
                    System.out.println("Error unsubscribing supplier!");
                }
            } else {
                System.out.println("Unsubscription cancelled!");
            }

        } else {
            System.out.println("Supplier not found in subscribed suppliers!\n");
        }
    }

    private static void branchEditMenu(Branch br) {
        branchEditMenu.setHeader(String.format("Currently viewing [%s].", br.getId()));

        int choice = -1;
        while (choice != 0) {
            choice = branchEditMenu.prompt();

            switch (choice) {
                case 1: // EDIT NAME
                {
                    System.out.println("Current branch name: " + br.getName());
                    System.out.print("New branch name: ");
                    br.setName(sc.nextLine());
                    System.out.print("Successfully updated branch name");
                    try {
                        FileHandler.writeObjectToFile(br, Main.dirs.get("branches") + br.getId() + ".json");
                    } catch (JsonProcessingException _) {
                        System.out.println("Failed to update branch (Couldn't save branch as file).");
                        return;
                    }
                    // TODO
                    break;
                }
                case 2: // EDIT LOCATION
                {
                    Address temp = new Address();
                    System.out.println("Current supplier address: " + br.getLocation());
                    System.out.println("New supplier address: ");

                    // enter new unit no and validate
                    System.out.print("Enter new Unit No: ");
                    String unitNo = sc.nextLine();
                    if (unitNo.isEmpty()) {
                        System.out.println("Unit No cannot be empty.");
                        break;
                    }
                    temp.setUnit(unitNo);

                    // enter new building and validate
                    System.out.print("Enter new Building: ");
                    String building = sc.nextLine();
                    if (building.isEmpty()) {
                        System.out.println("Building name cannot be empty.");
                        break;
                    }
                    temp.setBuilding(building);

                    // enter new street and validate
                    System.out.print("Enter new Street: ");
                    String street = sc.nextLine();
                    if (street.isEmpty()) {
                        System.out.println("Street name cannot be empty.");
                        break;
                    }
                    temp.setStreet(street);

                    // enter new town and validate
                    System.out.print("Enter new Town: ");
                    String town = sc.nextLine();
                    if (town.isEmpty()) {
                        System.out.println("Town name cannot be empty.");
                        break;
                    }
                    temp.setTown(town);

                    // enter new state and validate
                    System.out.print("Enter new State: ");
                    String state = sc.nextLine();
                    if (state.isEmpty()) {
                        System.out.println("State name cannot be empty.");
                        break;
                    }
                    temp.setState(state);
                    int postalCode;
                    // enter new postal code and validate
                    while(true){
                        System.out.print("Enter new Postal Code: ");
                        try{
                            postalCode = Integer.parseInt(sc.nextLine());
                            if (postalCode <= 0 || postalCode > 999999) {
                                System.out.println("Postal code must be a positive number and less than 6 digits.");
                                continue;
                            }
                            break;

                        }catch(Exception e){
                            System.out.println("Not a valid postal code.");
                        }
                    }

                    temp.setPostalCode(postalCode);

                    System.out.println("Successfully updated supplier address");
                    br.setLocation(temp);
                    try {
                        FileHandler.writeObjectToFile(br, Main.dirs.get("branches") + br.getId() + ".json");
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

    private static void branchSubscribeWarehouseById(Branch br) {
        System.out.print("Enter warehouse ID to subscribe: ");
        String warehouseID = sc.nextLine();

        // Check if warehouse exists
        Warehouse warehouse = new Warehouse();
        try {
            warehouse = FileHandler.readObjectFromFile(warehouse, Main.dirs.get("warehouses") + warehouseID + ".json");
        } catch (JsonProcessingException _) {}

        if (warehouse.getId() != null) {
            // Display the warehouse details
            System.out.println("\nWarehouse to Subscribe:");
            System.out.println("Warehouse ID: " + warehouse.getId());
            System.out.println("Warehouse Name: " + warehouse.getName());
            System.out.println("Warehouse Location: " + warehouse.getLocation());
            System.out.println();

            // Ask for confirmation
            String confirm;
            do {
                System.out.print("Do you want to subscribe to this warehouse? (Y/N): ");
                confirm = sc.nextLine().toLowerCase();
            } while (!confirm.equals("y") && !confirm.equals("n"));

            if (confirm.equals("y")) {
                // Subscribe to the warehouse
                br.subscribeWarehouse(warehouse);

                // Save to file
                try {
                    FileHandler.writeObjectToFile(br, Main.dirs.get("branches") + br.getId() + ".json");
                    System.out.println("Warehouse subscribed successfully!\n");
                } catch (JsonProcessingException e) {
                    System.out.println("Error subscribing to the warehouse.");
                }
            } else {
                System.out.println("Subscription cancelled!");
            }

        } else {
            System.out.println("Warehouse not found!\n");
        }
    }

    private static void branchUnsubscribeWarehouseByID(Branch br) {
        System.out.print("Enter warehouse ID to unsubscribe: ");
        String warehouseID = sc.nextLine();

        // Find warehouse in the branch's subscribed warehouses
        Warehouse warehouse = br.findWarehousesById(warehouseID);

        // Check if warehouse exists
        if (warehouse != null) {
            // Display the warehouse details
            System.out.println("\nWarehouse Details to Unsubscribe:");
            System.out.println("Warehouse ID: " + warehouse.getId());
            System.out.println("Warehouse Name: " + warehouse.getName());
            System.out.println("Warehouse Location: " + warehouse.getLocation());
            System.out.println();

            // Ask for confirmation
            String confirm;
            do {
                System.out.print("Do you want to unsubscribe from this warehouse? (Y/N): ");
                confirm = sc.nextLine().toLowerCase();
            } while (!confirm.equals("y") && !confirm.equals("n"));

            if (confirm.equals("y")) {
                // Unsubscribe warehouse
                br.unsubscribeWarehouseById(warehouseID);

                // Save to file
                try {
                    FileHandler.writeObjectToFile(br, Main.dirs.get("branches") + br.getId() + ".json");
                    System.out.println("Warehouse unsubscribed successfully!\n");
                } catch (JsonProcessingException e) {
                    System.out.println("Error unsubscribing warehouse!");
                }
            } else {
                System.out.println("Unsubscription cancelled!");
            }

        } else {
            System.out.println("Warehouse not found in subscribed warehouses!\n");
        }
    }

}

