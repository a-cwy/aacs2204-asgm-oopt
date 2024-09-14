import java.util.ArrayList;

public class Supplier extends Inventory {
    private final String id;
    private String name;
    private Address location;


    public Supplier(){
        this.id = null;
        this.name = null;
        this.location = null;
    }
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

    public void setName(String name){
        this.name=name;
    }

    public void setLocation(Address location){
        this.location = location;
    }



}
