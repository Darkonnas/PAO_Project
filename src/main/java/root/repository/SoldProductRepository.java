package root.repository;

import root.model.SoldProduct;
import root.service.SoldProductIOService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SoldProductRepository {
    private final List<SoldProduct> soldProducts;
    
    public SoldProductRepository() {
        soldProducts = SoldProductIOService.getInstance().loadSoldProducts();
    }
    
    public List<SoldProduct> getSoldProducts() {
        return Collections.unmodifiableList(soldProducts);
    }
    
    public boolean add(final SoldProduct sp) {
        return soldProducts.add(sp);
    }
    
    public boolean remove(final int receiptId, final int productId) {
        final SoldProduct sp = getSoldProductByReceiptIdAndProductId(receiptId, productId);
        if (null == sp) {
            return false;
        }
        return soldProducts.remove(sp);
    }
    
    public SoldProduct getSoldProductByReceiptIdAndProductId(final int receiptId, final int productId) {
        for (final SoldProduct sp : soldProducts) {
            if (receiptId == sp.getReceiptId() && productId == sp.getProductId()) {
                return sp;
            }
        }
        return null;
    }
    
    public List<SoldProduct> getSoldProductsByReceiptId(final int receiptId) {
        List<SoldProduct> result = null;
        for (final SoldProduct sp : soldProducts) {
            if (receiptId == sp.getProductId()) {
                if (null == result) {
                    result = new ArrayList<>();
                }
                result.add(sp);
            }
        }
        return result;
    }
    
    public List<SoldProduct> getSoldProductsByProductId(final int productId) {
        List<SoldProduct> result = null;
        for (final SoldProduct sp : soldProducts) {
            if (productId == sp.getReceiptId()) {
                if (null == result) {
                    result = new ArrayList<>();
                }
                result.add(sp);
            }
        }
        return result;
    }
    
    public List<SoldProduct> getSoldProductsByQuantity(final int quantity) {
        List<SoldProduct> result = null;
        for (final SoldProduct sp : soldProducts) {
            if (quantity == sp.getQuantity()) {
                if (null == result) {
                    result = new ArrayList<>();
                }
                result.add(sp);
            }
        }
        return result;
    }
}
