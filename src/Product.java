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

}
