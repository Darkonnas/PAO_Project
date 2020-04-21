package root.service;

import root.model.SoldProduct;
import root.repository.SoldProductRepository;

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
        return soldProductRepository.getSoldProducts();
    }
    
    public boolean addSoldProduct(final SoldProduct sp) {
        if (null == ReceiptService.getInstance().getReceiptById(sp.getReceiptId())) {
            return false;
        }
        if (null == ProductService.getInstance().getProductById(sp.getProductId())) {
            return false;
        }
        return soldProductRepository.add(sp);
    }
    
    public boolean removeSoldProduct(final int receiptId, final int productId) {
        return soldProductRepository.remove(receiptId, productId);
    }
    
    public SoldProduct getSoldProductByReceiptIdAndProductId(final int receiptId, final int productId) {
        return soldProductRepository.getSoldProductByReceiptIdAndProductId(receiptId, productId);
    }
    
    public List<SoldProduct> getSoldProductsByReceiptId(final int receiptId) {
        return soldProductRepository.getSoldProductsByReceiptId(receiptId);
    }
    
    public List<SoldProduct> getSoldProductsByProductId(final int productId) {
        return soldProductRepository.getSoldProductsByProductId(productId);
    }
    
    public List<SoldProduct> getSoldProductsByQuantity(final int quantity) {
        return soldProductRepository.getSoldProductsByQuantity(quantity);
    }
}
