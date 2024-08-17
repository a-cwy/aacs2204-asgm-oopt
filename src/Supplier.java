import java.util.ArrayList;

public class Supplier extends Inventory {
    private String id;
    private String name;
    private Address location;

    public Supplier (String id, String name, Address location, Product[] prodArr){
        super(prodArr, id);
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public Supplier(String id, String name, Address location, ArrayList<Product> prodArr){
        super(prodArr, id);
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public String getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public Address getLocation(){
        return location;
    }

}
