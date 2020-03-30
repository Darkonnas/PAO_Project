package root.service;

import root.model.SoldProduct;
import root.repository.SoldProductRepository;

import java.util.List;
import java.util.Set;

public class SoldProductService {
    private static SoldProductService instance;
    private static ProductService productService;
    private static ReceiptService receiptService;
    private SoldProductRepository soldProductRepository;
    
    private SoldProductService() {
        soldProductRepository = new SoldProductRepository();
    }
    
    public static SoldProductService getInstance(ProductService productInstance,ReceiptService receiptInstance) {
        if(instance == null) {
            instance = new SoldProductService();
            productService = productInstance;
            receiptService = receiptInstance;
        }
        return instance;
    }
    
    public Set<SoldProduct> getSoldProducts() {
        return soldProductRepository.getSoldProducts();
    }
    
    public boolean addSoldProduct(SoldProduct sp) {
        if(productService.getProductById(sp.getReceiptId()) == null) {
            return false;
        }
        if(receiptService.getReceiptById(sp.getProductId()) == null) {
            return false;
        }
        return soldProductRepository.add(sp);
    }
    
    public boolean removeSoldProducts(int id) {
        return soldProductRepository.remove(id);
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
