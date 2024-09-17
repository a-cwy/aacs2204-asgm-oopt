package root;

public class Product {
    private String name;
    private double price;
    private int quantity;

    Product(){}

    Product(String name) {
        this.name = name;
    }

    Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
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

    public void setQuantity(int amt) {
        this.quantity = amt;
    }

    public double totalValue() {
        return price * quantity;
    }

    public double valueOf(int amt) {
        return price * amt / quantity;
    }

    @Override
    public String toString() {
        return "\nName: " + name + "\nPrice: " + price + "\nQuantity: " + quantity;
        //return String.format("%-30s %-10.2f %-5d", name, price, quantity);
    }

}
