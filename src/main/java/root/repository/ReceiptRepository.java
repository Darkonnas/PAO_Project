package root.repository;

import root.model.Receipt;

import java.util.Set;
import java.util.TreeSet;

public class ReceiptRepository {
    private Set<Receipt> receipts;
    
    public ReceiptRepository() {
        receipts = new TreeSet<>();
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
    
    public Receipt getReceiptByCouponId(int couponId) {
        for (Receipt r : receipts) {
            if (couponId == r.getCouponId()) {
                return r;
            }
        }
        return null;
    }
}
