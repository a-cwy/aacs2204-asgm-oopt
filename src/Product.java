public class Product {
    private String id;
    private String name;
    private String brand;
    private double price;
    private int quantity;

    Product(String id, String name, String brand) {
        this.id = id;
        this.name = name;
        this.brand = brand;
    }

    Product(String id, String name, String brand, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.brand = brand;
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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

    public String toString() {
        return "ID: " + id + "\nName: " + name + "\nBrand: " + brand +
                "\nPrice: " + price + "\nQuantity: " + quantity;
    }

}
