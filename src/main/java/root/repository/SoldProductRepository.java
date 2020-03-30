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
        soldProducts.add(new SoldProduct(0, 63476538, 5));
        soldProducts.add(new SoldProduct(0, 1982479237, 6));
        soldProducts.add(new SoldProduct(1, 1485382747, 2));
        soldProducts.add(new SoldProduct(2, 1802160646, 1));
        soldProducts.add(new SoldProduct(2, 111220270, 2));
        soldProducts.add(new SoldProduct(2, 63476538, 3));
        soldProducts.add(new SoldProduct(3, 468349617, 2));
        soldProducts.add(new SoldProduct(3, 312642015, 1));
        soldProducts.add(new SoldProduct(3, 1982479237, 3));
        soldProducts.add(new SoldProduct(3, 1850062050, 4));
        soldProducts.add(new SoldProduct(3, 1485382747, 2));
    }
    
    public Set<SoldProduct> getSoldProducts() {
        return soldProducts;
    }
    
    public boolean add(SoldProduct sp) {
        return soldProducts.add(sp);
    }
    
    public boolean remove(int id) {
        List<SoldProduct> result = getSoldProductsByProductId(id);
        if (result == null) {
            return false;
        }
        return soldProducts.removeAll(result);
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
    
    public List<SoldProduct> getSoldProductsByCount(int count) {
        List<SoldProduct> result = null;
        for (SoldProduct sp : soldProducts) {
            if (count == sp.getCount()) {
                if (result == null) {
                    result = new Vector<>();
                }
                result.add(sp);
            }
        }
        return result;
    }
}
