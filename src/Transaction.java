import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class Transaction implements CompletablePending {
    // ATTRIBUTES
    private final String id;
    private final LocalDateTime dateTime;
    private final String sellerID;
    private final String buyerID;
    private final ArrayList<Product> items;
    private String status;

    public Transaction(String id, String sellerID, String buyerID, Product[] items) {
        this.id = id;
        this.dateTime = LocalDateTime.now();
        this.sellerID = sellerID;
        this.buyerID = buyerID;
        this.items = new ArrayList<>(Arrays.asList(items));
        this.status = "PENDING";
    }

    // METHODS
    public void printTransaction() {
        // TODO formatted display of transaction information
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

    public LocalDateTime getDateTime() {
        return dateTime;
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
}
