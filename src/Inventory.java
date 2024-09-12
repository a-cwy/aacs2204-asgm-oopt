import java.util.ArrayList;
import java.util.Arrays;

abstract class  Inventory {
    private ArrayList <Product> items;
    private TransactionLog log;

    protected Inventory(){
        this.items = new ArrayList();
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

    public void addProduct(Product prod){
        items.add(prod);
    }

    public Product getProductByID(String id){
        for(Product p : items){
            if(p.getId().equals(id)){
                return p;
            }
        }
        return null;
    }

    public Product getProductByName(String name){
        for(Product p : items){
            if(p.getName().equals(name)){
                return p;
            }
        }
        return null;
    }

    public Product getProductByIndex(int index){
        return items.get(index);
    }

    public Product removeProductByID(String id){
        items.removeIf(p -> p.getId().equals(id));
        return null;
    }

    public Product removeProductByName(String name){
        items.removeIf(p -> p.getName().equals(name));
        return null;
    }

    public Product removeProductByIndex(int index){
        return items.remove(index);
    }

    public ArrayList<Product> getItems() {
        return items;
    }

    public TransactionLog getLog() {
        return log;
    }
}
