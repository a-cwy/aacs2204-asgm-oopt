public class Address {

    private String unit;
    private String floor;
    private String street;
    private String area;
    private String state;
    private int postalCode;

    public Address(){}

    public Address(String unit, String floor, String street, String area, String state, int postalCode){
        this.unit = unit;
        this.floor = floor;
        this.street = street;
        this.area = area;
        this.state = state;
        this.postalCode = postalCode;
    }

    public String getUnit() {
        return unit;
    }

    public String getFloor() {
        return floor;
    }

    public String getStreet() {
        return street;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public String getArea() {
        return area;
    }

    public String getState() {
        return state;
    }

    @Override
    public String toString() {
        return unit + "," + floor + "," + street + "," + area + "," + state + "," + postalCode;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
    public void setFloor(String floor) {
        this.floor = floor;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public void setArea(String area) {
        this.area = area;
    }
    public void setState(String state) {
        this.state = state;
    }
    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

}
