package root.service;

import root.model.Receipt;
import root.repository.ReceiptRepository;

import java.sql.Timestamp;
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
        LogService.getInstance().log("Requested receipts", new Timestamp(System.currentTimeMillis()));
        return receiptRepository.getReceipts();
    }
    
    public int addReceipt(final Receipt receipt) {
        LogService.getInstance().log("Added a receipt", new Timestamp(System.currentTimeMillis()));
        return receiptRepository.add(receipt);
    }
    
    public int removeReceipt(final int id) {
        LogService.getInstance().log("Removed a receipt", new Timestamp(System.currentTimeMillis()));
        return receiptRepository.remove(id);
    }
    
    public Receipt getReceiptById(final int id) {
        LogService.getInstance().log("Requested receipt by id", new Timestamp(System.currentTimeMillis()));
        return receiptRepository.getReceiptById(id);
    }
    
    public Set<Receipt> getReceiptsByRegisterId(final int registerId) {
        LogService.getInstance().log("Requested receipts by register id", new Timestamp(System.currentTimeMillis()));
        return receiptRepository.getReceiptsByRegisterId(registerId);
    }
    
    public Set<Receipt> getReceiptsByCashierId(final int cashierId) {
        LogService.getInstance().log("Requested receipts by cashier id", new Timestamp(System.currentTimeMillis()));
        return receiptRepository.getReceiptsByCashierId(cashierId);
    }
    
    public Receipt getReceiptByCouponId(final int id) {
        LogService.getInstance().log("Requested receipt by couponId", new Timestamp(System.currentTimeMillis()));
        return receiptRepository.getReceiptByCouponId(id);
    }
}
