import java.util.ArrayList;
import java.util.Arrays;

public class TransactionLog {
    // ATTRIBUTES
    private final String ownerID;
    private final ArrayList<Transaction> log;

    public TransactionLog(String ownerID) {
        this.ownerID = ownerID;
        this.log = new ArrayList<>();
    }

    public TransactionLog(String ownerID, Transaction[] tArr) {
        this.ownerID = ownerID;
        this.log = new ArrayList<>(Arrays.asList(tArr));
    }

    public TransactionLog(String ownerID, ArrayList<Transaction> tArr) {
        this.ownerID = ownerID;
        this.log = tArr;
    }

    // METHODS
    public void printLog() {
        // TODO formatted display of entire log
    }

    public ArrayList<Transaction> getOutgoing() {
        ArrayList<Transaction> retarr = new ArrayList<>();

        for (Transaction t : log) {
            if (t.getSellerID().equals(ownerID)) { retarr.add(t); }
        }

        return retarr;
    }

    public ArrayList<Transaction> getIncoming() {
        ArrayList<Transaction> retarr = new ArrayList<>();

        for (Transaction t : log) {
            if (t.getBuyerID().equals(ownerID)) { retarr.add(t); }
        }

        return retarr;
    }

    public void append(Transaction t) {
        this.log.add(t);
    }

    public void addAt(int i, Transaction t) {
        this.log.add(i, t);
    }

    public Transaction remove(int i) {
        return this.log.remove(i);
    }

    // GET SET
    public String getOwnerID() {
        return ownerID;
    }
}
