package root.repository;

import root.model.Receipt;
import root.service.ReceiptIOService;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

public class ReceiptRepository {
    private final Set<Receipt> receipts;
    
    public ReceiptRepository() {
        receipts = ReceiptIOService.getInstance().loadReceipts();
    }
    
    public Set<Receipt> getReceipts() {
        return Collections.unmodifiableSet(receipts);
    }
    
    public boolean add(final Receipt r) {
        return receipts.add(r);
    }
    
    public boolean remove(final int id) {
        final Receipt r = getReceiptById(id);
        if (null == r) {
            return false;
        }
        return receipts.remove(r);
    }
    
    public Receipt getReceiptById(final int id) {
        for (final Receipt r : receipts) {
            if (id == r.getId()) {
                return r;
            }
        }
        return null;
    }
    
    public Set<Receipt> getReceiptsByRegisterId(final int registerId) {
        Set<Receipt> result = null;
        for (final Receipt r : receipts) {
            if (registerId == r.getRegisterId()) {
                if (null == result) {
                    result = new TreeSet<>();
                }
                result.add(r);
            }
        }
        return result;
    }
    
    public Set<Receipt> getReceiptsByCashierId(final int cashierId) {
        Set<Receipt> result = null;
        for (final Receipt r : receipts) {
            if (cashierId == r.getCashierId()) {
                if (null == result) {
                    result = new TreeSet<>();
                }
                result.add(r);
            }
        }
        return result;
    }
    
    public Receipt getReceiptByCouponId(final int couponId) {
        for (final Receipt r : receipts) {
            if (couponId == r.getCouponId()) {
                return r;
            }
        }
        return null;
    }
}
