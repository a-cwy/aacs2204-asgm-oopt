public class SalesTransaction extends Transaction {
    // ATTRIBUTES
    private final double amount;
    private Payment payment;

    public SalesTransaction(String id, String sellerID, String buyerID, Product[] items) {
        super(id, sellerID, buyerID, items);

        // Iterate items and calculate total amount
        double tempAmt = 0.0;
        for (Product item : items) {
            tempAmt += item.totalValue();
        }
        this.amount = tempAmt;

        String pID = "P" + id.substring(1);
        this.payment = new Payment(pID, this.amount);
    }

    // METHODS
    @Override
    public void printTransaction() {
        // TODO rework to include amount and payment id
    }

    public void printInvoice() {
        // TODO formatted display of invoice for payment
    }

    // GET SET
    public double getAmount() {
        return amount;
    }

    public Payment getPayment() {
        return payment;
    }
}
