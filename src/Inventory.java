import java.util.ArrayList;
import java.util.Arrays;

abstract class  Inventory {
    private final ArrayList <Product> items;
    private final TransactionLog log;

    protected Inventory(){
        this.items = new ArrayList<>();
        this.log = null;
    }
    protected Inventory(Product[] prod, String id){
        this.items = new ArrayList<>(Arrays.asList(prod));
        this.log = new TransactionLog(id);
    }
    protected Inventory(ArrayList<Product> items, String id){
        this.items = items;
        this.log = new TransactionLog(id);
    }

    public double totalValue(){
        double total=0;
        for(Product p : items){
            total += p.getPrice();
        }
        return total;
    }

    public void printInventory(){
        for (Product item : items) {
            System.out.println(item.toString());
        }
    }

    public void addProduct(Product prod) {
        Product temp = this.getProductByName(prod.getName());

        // if product doesn't already exist
        if (temp == null) {
            this.items.add(prod);
            return;
        }

        // if product already exists
        if (temp.getName().equals(prod.getName()) && temp.getPrice() == prod.getPrice()) {
            temp.addQuantity(prod.getQuantity());
            return;
        }

        // if product exists with different price
        System.out.println("Product already exists with different price.");
    }

    public Product getProductByName(String name){
        for(Product p : items){
            if(p.getName().equals(name)){
                return p;
            }
        }
        return null;
    }

    public Product removeProductByName(String name){
        items.removeIf(p -> p.getName().equals(name));
        return null;
    }

    public ArrayList<Product> getItems() {
        return items;
    }

    public TransactionLog getLog() {
        return log;
    }
}
