package root.service;

import root.model.Coupon;
import root.model.Receipt;
import root.repository.ReceiptRepository;

import java.util.Set;

public final class ReceiptService {
    private static ReceiptService instance;
    private final ReceiptRepository receiptRepository;
    
    private ReceiptService() {
        receiptRepository = new ReceiptRepository();
    }
    
    public static ReceiptService getInstance() {
        if (null == instance) {
            instance = new ReceiptService();
        }
        return instance;
    }
    
    public Set<Receipt> getReceipts() {
        return receiptRepository.getReceipts();
    }
    
    public boolean addReceipt(final Receipt receipt) {
        if (null == RegisterService.getInstance().getRegisterById(receipt.getRegisterId())) {
            return false;
        }
        if (-1 != receipt.getCashierId() && null == CashierService.getInstance().getCashierById(receipt.getCashierId())) {
            return false;
        }
        if (-1 != receipt.getCouponId()) {
            final Coupon coupon = CouponService.getInstance().getCouponById(receipt.getCouponId());
            if (null == coupon) {
                return false;
            }
            if (coupon.isUsed()) {
                return false;
            }
            coupon.setUsed(true);
        }
        return receiptRepository.add(receipt);
    }
    
    public boolean removeReceipt(final int id) {
        return receiptRepository.remove(id);
    }
    
    public Receipt getReceiptById(final int id) {
        return receiptRepository.getReceiptById(id);
    }
    
    public Set<Receipt> getReceiptsByRegisterId(final int registerId) {
        return receiptRepository.getReceiptsByRegisterId(registerId);
    }
    
    public Set<Receipt> getReceiptsByCashierId(final int cashierId) {
        return receiptRepository.getReceiptsByCashierId(cashierId);
    }
    
    public Receipt getReceiptByCouponId(final int id) {
        return receiptRepository.getReceiptByCouponId(id);
    }
}
