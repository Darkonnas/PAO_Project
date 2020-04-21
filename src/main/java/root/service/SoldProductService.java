package root.service;

import root.model.SoldProduct;
import root.repository.SoldProductRepository;

import java.sql.Timestamp;
import java.util.List;

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
    
    public List<SoldProduct> getSoldProducts() {
        LogService.getInstance().log("Requested all sold products", new Timestamp(System.currentTimeMillis()));
        return soldProductRepository.getSoldProducts();
    }
    
    public boolean addSoldProduct(final SoldProduct sp) {
        if (null == ReceiptService.getInstance().getReceiptById(sp.getReceiptId())) {
            return false;
        }
        if (null == ProductService.getInstance().getProductById(sp.getProductId())) {
            return false;
        }
        LogService.getInstance().log("Added a sold product", new Timestamp(System.currentTimeMillis()));
        return soldProductRepository.add(sp);
    }
    
    public boolean removeSoldProduct(final int receiptId, final int productId) {
        LogService.getInstance().log("Removed a sold product", new Timestamp(System.currentTimeMillis()));
        return soldProductRepository.remove(receiptId, productId);
    }
    
    public SoldProduct getSoldProductByReceiptIdAndProductId(final int receiptId, final int productId) {
        LogService.getInstance().log("Requested sold product by receiptId and productId", new Timestamp(System.currentTimeMillis()));
        return soldProductRepository.getSoldProductByReceiptIdAndProductId(receiptId, productId);
    }
    
    public List<SoldProduct> getSoldProductsByReceiptId(final int receiptId) {
        LogService.getInstance().log("Requested sold products by receiptId", new Timestamp(System.currentTimeMillis()));
        return soldProductRepository.getSoldProductsByReceiptId(receiptId);
    }
    
    public List<SoldProduct> getSoldProductsByProductId(final int productId) {
        LogService.getInstance().log("Requested sold products by productId", new Timestamp(System.currentTimeMillis()));
        return soldProductRepository.getSoldProductsByProductId(productId);
    }
    
    public List<SoldProduct> getSoldProductsByQuantity(final int quantity) {
        LogService.getInstance().log("Requested sold products by quantity", new Timestamp(System.currentTimeMillis()));
        return soldProductRepository.getSoldProductsByQuantity(quantity);
    }
}
