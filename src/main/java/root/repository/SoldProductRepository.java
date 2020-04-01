package root.repository;

import root.model.SoldProduct;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;

public class SoldProductRepository {
    private Set<SoldProduct> soldProducts;
    
    public SoldProductRepository() {
        soldProducts = new TreeSet<>();
    }
    
    public Set<SoldProduct> getSoldProducts() {
        return soldProducts;
    }
    
    public boolean add(SoldProduct sp) {
        return soldProducts.add(sp);
    }
    
    public boolean remove(int receiptId, int productId) {
        SoldProduct sp = getSoldProductByReceiptIdAndProductId(receiptId, productId);
        if (sp == null) {
            return false;
        }
        return soldProducts.remove(sp);
    }
    
    public SoldProduct getSoldProductByReceiptIdAndProductId(int receiptId, int productId) {
        for (SoldProduct sp : soldProducts) {
            if (receiptId == sp.getReceiptId() && productId == sp.getProductId()) {
                return sp;
            }
        }
        return null;
    }
    
    public List<SoldProduct> getSoldProductsByReceiptId(int receiptId) {
        List<SoldProduct> result = null;
        for (SoldProduct sp : soldProducts) {
            if (receiptId == sp.getProductId()) {
                if (result == null) {
                    result = new Vector<>();
                }
                result.add(sp);
            }
        }
        return result;
    }
    
    public List<SoldProduct> getSoldProductsByProductId(int productId) {
        List<SoldProduct> result = null;
        for (SoldProduct sp : soldProducts) {
            if (productId == sp.getReceiptId()) {
                if (result == null) {
                    result = new Vector<>();
                }
                result.add(sp);
            }
        }
        return result;
    }
    
    public List<SoldProduct> getSoldProductsByQuantity(int quantity) {
        List<SoldProduct> result = null;
        for (SoldProduct sp : soldProducts) {
            if (quantity == sp.getQuantity()) {
                if (result == null) {
                    result = new Vector<>();
                }
                result.add(sp);
            }
        }
        return result;
    }
}
