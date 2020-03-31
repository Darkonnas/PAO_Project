package root.repository;

import root.model.Receipt;

import java.util.Set;
import java.util.TreeSet;

public class ReceiptRepository {
    private Set<Receipt> receipts;
    
    public ReceiptRepository() {
        receipts = new TreeSet<>();
        receipts.add(new Receipt(0, 0));
        receipts.add(new Receipt(2, 2));
        receipts.add(new Receipt(4));
        receipts.add(new Receipt(5));
        receipts.add(new Receipt(3, 3));
    }
    
    public Set<Receipt> getReceipts() {
        return receipts;
    }
    
    public boolean add(Receipt r) {
        return receipts.add(r);
    }
    
    public boolean remove(int id) {
        Receipt r = getReceiptById(id);
        if (r == null) {
            return false;
        }
        return receipts.remove(r);
    }
    
    public Receipt getReceiptById(int id) {
        for (Receipt r : receipts) {
            if (id == r.getId()) {
                return r;
            }
        }
        return null;
    }
    
    public Set<Receipt> getReceiptsByRegisterId(int registerId) {
        Set<Receipt> result = null;
        for (Receipt r : receipts) {
            if (registerId == r.getRegisterId()) {
                if (result == null) {
                    result = new TreeSet<>();
                }
                result.add(r);
            }
        }
        return result;
    }
    
    public Set<Receipt> getReceiptsByCashierId(int cashierId) {
        Set<Receipt> result = null;
        for (Receipt r : receipts) {
            if (cashierId == r.getCashierId()) {
                if (result == null) {
                    result = new TreeSet<>();
                }
                result.add(r);
            }
        }
        return result;
    }
    
}
