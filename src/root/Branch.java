package root;

import java.util.ArrayList;
import java.util.Arrays;

public class Branch extends Inventory {

    private final String id;
    private String name;
    private Address location;
    private final ArrayList<Warehouse> warehouses;
    private final ArrayList<Product> items;

    public Branch () {
        this.id = null;
        this.name = null;
        this.location = null;
        this.warehouses = new ArrayList<>();
        this.items = new ArrayList<>();
    }

    public Branch(String id, String name, Address location) {
        this(id, name, location,new ArrayList<>(),new ArrayList<>()); //call another constructor of the same name class
    }

    public Branch(String id, String name, Address location, Product[] prodArr) {
        this(id, name, location, new ArrayList<>(), new ArrayList<>(Arrays.asList(prodArr)));
        // Array.asList() --> convert Product[] in to ArrayList<Product> get a view of the array, but can't add or remove
        // new ArrayList<>(...) --> allow you to add and remove as needed
    }

    public Branch(String id, String name, Address location, ArrayList<Warehouse> warehouses,ArrayList<Product> items) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.warehouses = warehouses;
        this.items = items;
    }

    public double totalValue(){
        double retval = 0.0;

        for(Product prod : this.items) {
            retval += prod.totalValue();
        }

        return retval;
    }


    public void subscribeWarehouse(Warehouse warehouse){
        warehouses.add(warehouse);
    }


    public Warehouse findWarehousesById(String id){
        for (Warehouse warehouse : warehouses){
            if (warehouse.getId().equals(id)){
                return warehouse;
            }
        }
        throw new IllegalArgumentException("Invalid Warehouse ID"); // Invalid input values
    }

    public Warehouse findWarehousesByName(String name){
        for (Warehouse warehouse : warehouses){
            if (warehouse.getName().equals(name)){ // compare getName and name
                return warehouse;
            }
        }
        throw new IllegalArgumentException("Invalid Warehouse name"); //Invalid input values
    }

    public void unsubscribeWarehouseById(String id){
        for (Warehouse warehouse : warehouses){
            if (warehouse.getId().equals(id)){
                warehouses.remove(warehouse);
                return;
            }
        }
        throw new IllegalArgumentException("Invalid Warehouse ID");
    }


    public Warehouse unsubscribeWarehouseByName(String name){
        for (Warehouse warehouse : warehouses){
            if (warehouse.getName().equals(name)){
                warehouses.remove(warehouse);
                return warehouse;
            }
        }
        throw new IllegalArgumentException("Invalid Warehouse name");
    }

    public void printInventory(Branch branch){
        Main.clearScreen();
        System.out.println("Inventory of branch " + branch.getId());
        System.out.printf("%-2s %-30s %-15s %-10s %-15s\n"," ", "Product Name", "Price (RM)", "Quantity", "Total Value (RM)");
        System.out.println("--------------------------------------------------------------------------");

        super.printInventory();

    }

//    public void addProduct(Product product,int amt){
//        product.addQuantity(amt);}
//
//    public Product getProductById(String id){
//        for (Product product : items){
//            if (product.getId().equals(id)){
//                return product;
//            }
//        }
//        throw new IllegalArgumentException("Invalid Product ID");
//    }
//
//    public Product getProductByIndex(int index){
//        if (index >= 0 && index < items.size()){
//            return items.get(index);
//        }
//        throw new IndexOutOfBoundsException("Invalid Product index");
//    }
//
//    public Product getProductByName(String name){
//        for (Product product : items){
//            if (product.getName().equals(name)){
//                return product;
//            }
//        }
//        throw new IllegalArgumentException("Invalid Product name");
//    }
//
//    public Product removeProductById(String id){
//        for (Product product : items){
//            if (product.getId().equals(id)){
//                items.remove(product);
//                return product;
//            }
//        }
//        throw new IllegalArgumentException("Invalid Product ID");
//    }
//
//    public Product removeProductByIndex(int index){
//        if (index > 0 && index < items.size()){
//            return items.remove(index);
//        }
//        throw new IndexOutOfBoundsException("Invalid Product index");
//    }
//
//    public Product removeProductByName(String name){
//        for (Product product : items){
//            if (product.getName().equals(name)){
//                items.remove(product);
//                return product;
//            }
//        }
//        throw new IllegalArgumentException("Invalid Product name");
//    }

    @Override
    public String toString() {
        return "Branch{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", location=" + location +
                ", warehouses=" + warehouses +
                ", items=" + items +
                /*", log=" + log +*/
                '}';
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Address getLocation() {
        return location;
    }

    public ArrayList<Warehouse> getWarehouses() {
        return warehouses;
    }

    public ArrayList<Product> getItems() {
        return items;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(Address location){
        this.location = location;
    }

    /*public void printInventory(Branch br){
        Main.clearScreen();
        System.out.println("Inventory of branch " + br.getId());
        System.out.printf("%2s %-20s %-10s %-10s\n", "","Name", "Price", "Quantity");
        super.printInventory();

    }*/
}
