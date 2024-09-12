public class Product {
    private String id;
    private String name;
    private double price;
    private int quantity;

    Product(){}

    Product(String id, String name) {
        this.id = id;
        this.name = name;
    }

    Product(String id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void addQuantity(int amt) {
        this.quantity += amt;
    }

    public void reduceQuantity(int amt) {
        this.quantity -= amt;
    }

    public double totalValue() {
        return price * quantity;
    }

    public double valueOf(int amt) {
        return price * amt / quantity;
    }

    @Override
    public String toString() {
        return "ID: " + id + "\nName: " + name + "\nPrice: " + price + "\nQuantity: " + quantity;
    }

}
