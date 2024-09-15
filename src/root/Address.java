package root;

public class Address {

    private String unit;
    private String building;
    private String street;
    private String town;
    private String state;
    private int postalCode;

    public Address(){}

    public Address(String unit, String building, String street, String state, int postalCode){
        this.unit = unit;
        this.building = building;
        this.street = street;
        this.state = state;
        this.postalCode = postalCode;
    }

    public String getUnit() {
        return unit;
    }

    public String getBuilding() {
        return building;
    }

    public String getStreet() {
        return street;
    }

    public String getTown() {
        return town;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public String getState() {
        return state;
    }

    @Override
    public String toString() {
        return unit + "," + building + "," + street + "," + town + "," + state + "," + postalCode;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
    public void setBuilding(String building) {
        this.building = building;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public void setTown(String town) {
        this.town = town;
    }
    public void setState(String state) {
        this.state = state;
    }
    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

}
