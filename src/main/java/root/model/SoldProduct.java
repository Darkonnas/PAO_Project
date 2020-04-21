package root.model;

import java.util.Objects;

public class SoldProduct implements Comparable<SoldProduct> {
    private final int receiptId;
    private final int productId;
    private final int quantity;
    
    public SoldProduct(final int receiptId, final int productId, final int quantity) {
        this.receiptId = receiptId;
        this.productId = productId;
        this.quantity = quantity;
    }
    
    public int getReceiptId() {
        return receiptId;
    }
    
    public int getProductId() {
        return productId;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    @Override
    public int compareTo(final SoldProduct t) throws NullPointerException {
        if (null == t) {
            throw new NullPointerException();
        }
        if (receiptId < t.receiptId) {
            return -1;
        }
        if (receiptId == t.receiptId) {
            return Integer.compare(receiptId, t.productId);
        }
        return 1;
    }
    
    @Override
    public String toString() {
        return "SoldProduct{" + "receiptId=" + receiptId + ", productId=" + productId + ", quantity=" + quantity + '}';
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(receiptId, productId, quantity);
    }
    
    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (null == obj || getClass() != obj.getClass())
            return false;
        final SoldProduct soldProduct = (SoldProduct) obj;
        return receiptId == soldProduct.receiptId && productId == soldProduct.productId;
    }
}
