import java.time.LocalDateTime;

public class Payment implements CompletablePending {
    // ATTRIBUTES
    private final String id;
    private final LocalDateTime dateTime;
    private final double amount;
    private String status;

    public Payment(String id, double amount) {
        this.id = id;
        this.dateTime = LocalDateTime.now();
        this.amount = amount;
        this.status = "PENDING";
    }

    // METHODS
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

    public double getAmount() {
        return amount;
    }

    public String getStatus() {
        return status;
    }
}
