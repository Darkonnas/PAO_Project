package root.service;

import root.model.Receipt;
import root.repository.ReceiptRepository;

import java.util.Set;

public class ReceiptService {
    private static ReceiptService instance;
    private static RegisterService registerService;
    private static CashierService cashierService;
    private ReceiptRepository receiptRepository;
    
    private ReceiptService() {
        receiptRepository = new ReceiptRepository();
    }
    
    public static ReceiptService getInstance(RegisterService registerInstance, CashierService cashierInstance) {
        if(instance == null) {
            instance = new ReceiptService();
            registerService = registerInstance;
            cashierService = cashierInstance;
        }
        return instance;
    }
    
    public Set<Receipt> getReceipts() {
        return receiptRepository.getReceipts();
    }
    
    public boolean addReceipt(Receipt r) {
        if(registerService.getRegisterById(r.getId()) == null) {
            return false;
        }
        if(r.getCashierId() != -1 && cashierService.getCashierById(r.getCashierId()) == null) {
            return false;
        }
        return receiptRepository.add(r);
    }
    
    public boolean removeReceipt(int id) {
        return receiptRepository.remove(id);
    }
    
    public Receipt getReceiptById(int id) {
        return receiptRepository.getReceiptById(id);
    }
    
    public Set<Receipt> getReceiptsByRegisterId(int registerId) {
        return receiptRepository.getReceiptsByRegisterId(registerId);
    }
    
    public Set<Receipt> getReceiptsByCashierId(int cashierId) {
        return receiptRepository.getReceiptsByCashierId(cashierId);
    }
    
}
