import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Transaction implements CompletablePending {
    // ATTRIBUTES
    private final String id;
    private final Date date;
    private final String sellerID;
    private final String buyerID;
    private final ArrayList<Product> items;
    private final Payment payment;
    private String status;

    public Transaction() {
        this.id = null;
        this.date = null;
        this.sellerID = null;
        this.buyerID = null;
        this.items = new ArrayList<>();
        this.status = null;
        this.payment = null;
    }

    public Transaction(String id, Date date, String sellerID, String buyerID, ArrayList<Product> items) {
        this.id = id;
        this.date = date;
        this.sellerID = sellerID;
        this.buyerID = buyerID;
        this.items = items;
        this.status = "PENDING";

        double totalCost = 0.0;
        for (Product item : items) {
            totalCost += item.getPrice() * item.getQuantity();
        }

        this.payment = new Payment(totalCost);
    }

    // METHODS
    public void printTransaction() {
        System.out.printf("ID: %s\n", id);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        System.out.printf("Date: %s\n", simpleDateFormat.format(date));
        System.out.printf("Seller: %s\n", sellerID);
        System.out.printf("Buyer: %s\n", buyerID);
        System.out.println("Items");
        for (Product item : items) {
            System.out.printf("\t%-40s RM%-5.2f x %-5d = RM%-5.2f\n", item.getName(), item.getPrice(), item.getQuantity(), item.getPrice() * item.getQuantity());
        }
        System.out.printf("%63sRM%-5.2f\n\n", "Total = ", payment.getAmount());
        System.out.printf("Status: %s\n\n", status);
    }

    @Override
    public void succeed() {
        this.status = "SUCCESS";
    }

    @Override
    public void fail() {
        this.status = "FAILURE";
    }

    // GET SET
    public String getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public String getSellerID() {
        return sellerID;
    }

    public String getBuyerID() {
        return buyerID;
    }

    public ArrayList<Product> getItems() {
        return items;
    }

    public String getStatus() {
        return status;
    }

    public Payment getPayment() {
        return payment;
    }
}
