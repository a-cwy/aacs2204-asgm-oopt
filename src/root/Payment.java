package root;

public class Payment implements CompletablePending {
    // ATTRIBUTES
    private final double amount;
    private String status;

    public Payment () {
        this.amount = 0;
        this.status = null;
    }

    public Payment(double amount) {
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
    public double getAmount() {
        return amount;
    }

    public String getStatus() {
        return status;
    }
}
