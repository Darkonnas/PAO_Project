package root.service;

import root.model.SoldProduct;
import root.repository.SoldProductRepository;

import java.sql.Timestamp;
import java.util.Set;

public final class SoldProductService {
    private static SoldProductService instance;
    private final SoldProductRepository soldProductRepository;
    
    private SoldProductService() {
        soldProductRepository = new SoldProductRepository();
    }
    
    public static SoldProductService getInstance() {
        if (null == instance) {
            instance = new SoldProductService();
        }
        return instance;
    }
    
    public Set<SoldProduct> getSoldProducts() {
        LogService.getInstance().log("Requested sold products", new Timestamp(System.currentTimeMillis()), Thread.currentThread().getName());
        return soldProductRepository.getSoldProducts();
    }
    
    public int addSoldProduct(final SoldProduct sp) {
        LogService.getInstance().log("Added a sold product", new Timestamp(System.currentTimeMillis()), Thread.currentThread().getName());
        return soldProductRepository.add(sp);
    }
    
    public int removeSoldProduct(final int receiptId, final int productId) {
        LogService.getInstance().log("Removed a sold product", new Timestamp(System.currentTimeMillis()), Thread.currentThread().getName());
        return soldProductRepository.remove(receiptId, productId);
    }
    
    public SoldProduct getSoldProductByReceiptIdAndProductId(final int receiptId, final int productId) {
        LogService.getInstance().log("Requested sold product by receiptId and productId", new Timestamp(System.currentTimeMillis()), Thread.currentThread().getName());
        return soldProductRepository.getSoldProductByReceiptIdAndProductId(receiptId, productId);
    }
    
    public Set<SoldProduct> getSoldProductsByReceiptId(final int receiptId) {
        LogService.getInstance().log("Requested sold products by receiptId", new Timestamp(System.currentTimeMillis()), Thread.currentThread().getName());
        return soldProductRepository.getSoldProductsByReceiptId(receiptId);
    }
    
    public Set<SoldProduct> getSoldProductsByProductId(final int productId) {
        LogService.getInstance().log("Requested sold products by productId", new Timestamp(System.currentTimeMillis()), Thread.currentThread().getName());
        return soldProductRepository.getSoldProductsByProductId(productId);
    }
    
    public Set<SoldProduct> getSoldProductsByQuantity(final int quantity) {
        LogService.getInstance().log("Requested sold products by quantity", new Timestamp(System.currentTimeMillis()), Thread.currentThread().getName());
        return soldProductRepository.getSoldProductsByQuantity(quantity);
    }
}
