package root.service;

import root.model.SoldProduct;
import root.repository.SoldProductRepository;

import java.util.List;
import java.util.Set;

public class SoldProductService {
    private static SoldProductService instance;
    private SoldProductRepository soldProductRepository;
    
    private SoldProductService() {
        soldProductRepository = new SoldProductRepository();
    }
    
    public static SoldProductService getInstance() {
        if (instance == null) {
            instance = new SoldProductService();
        }
        return instance;
    }
    
    public Set<SoldProduct> getSoldProducts() {
        return soldProductRepository.getSoldProducts();
    }
    
    public boolean addSoldProduct(SoldProduct sp) {
        if (ReceiptService.getInstance().getReceiptById(sp.getReceiptId()) == null) {
            return false;
        }
        if (ProductService.getInstance().getProductById(sp.getProductId()) == null) {
            return false;
        }
        return soldProductRepository.add(sp);
    }
    
    public boolean removeSoldProduct(int receiptId, int productId) {
        return soldProductRepository.remove(receiptId, productId);
    }
    
    public SoldProduct getSoldProductByReceiptIdAndProductId(int receiptId, int productId) {
        return soldProductRepository.getSoldProductByReceiptIdAndProductId(receiptId, productId);
    }
    
    public List<SoldProduct> getSoldProductsByReceiptId(int receiptId) {
        return soldProductRepository.getSoldProductsByReceiptId(receiptId);
    }
    
    public List<SoldProduct> getSoldProductsByProductId(int productId) {
        return soldProductRepository.getSoldProductsByProductId(productId);
    }
    
    public List<SoldProduct> getSoldProductsByCount(int count) {
        return soldProductRepository.getSoldProductsByCount(count);
    }
}
