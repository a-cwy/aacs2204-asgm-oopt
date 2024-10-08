package root;

import java.util.ArrayList;
import java.util.Arrays;

public class Warehouse extends Inventory{
        private final String id;
        private String name;
        private Address location;
        private ArrayList<Supplier> suppliers;

        public Warehouse(){
                this.id = null;
                this.name = null;
                this.location = null;
                this.suppliers = null;
        }

        public Warehouse(String id, String name, Address location) {
                super(new ArrayList<>(), id); //initialize with empty arraylist of product
                this.id = id;
                this.name = name;
                this.location = location;
                this.suppliers = new ArrayList<>();
        }

        public Warehouse(String id, String name, Address location, Product prodArr) {
                super(new ArrayList<>(Arrays.asList(prodArr)), id);  //for single product
                this.id = id;
                this.name = name;
                this.location = location;
                this.suppliers = new ArrayList<>();
        }

        public Warehouse(String id, String name, Address location, ArrayList<Product> prodArr) {
                super(prodArr, id);  //for an arraylist of products
                this.id = id;
                this.name = name;
                this.location = location;
                this.suppliers = new ArrayList<>();
        }

        public Supplier getSupplierByID(String id){
                for(Supplier sup : suppliers){
                        if(sup.getId().equals(id)){
                                return sup;
                        }
                }
                return null;
        }

        public Supplier getSupplierByName(String name){
                for(Supplier sup : suppliers){
                        if(sup.getId().equals(id)){
                                return sup;
                        }
                }
                return null;
        }


        public void subscribeSupplier(Supplier sup) {
                suppliers.add(sup);
        }

        public Supplier unsubscribeSupplierByID(String id) {
                for (Supplier sup : suppliers) {
                        if (sup.getId().equals(id)) {
                                suppliers.remove(sup);
                                return sup;
                        }
                }
                return null;
        }

        public Supplier unsubscribeSupplierByName(String name) {
                for (Supplier sup : suppliers) {
                        if (sup.getName().equals(name)) {
                                suppliers.remove(sup);
                                return sup;
                        }
                }
                return null;
        }

        public void printInventory(Warehouse war){
                Main.clearScreen();
                System.out.println("Inventory of warehouse " + war.getId());
                System.out.printf("%-2s %-30s %-15s %-10s %-15s\n"," ", "Product Name", "Price (RM)", "Quantity", "Total Value (RM)");
                System.out.println("--------------------------------------------------------------------------");

                super.printInventory();

        }

        @Override
        public String toString() {
                return "Warehouse ID: " + id + ", Name: " + name + ", Location: " + location.toString();
        }

        public String getId() {
                return id;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public Address getLocation() {
                return location;
        }

        public void setLocation(Address location){
                this.location = location;
        }

        public ArrayList<Supplier> getSuppliers() {
                return suppliers;
        }
}
