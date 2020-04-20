package root.model;

public class SoldProduct implements Comparable<SoldProduct> {
    private final int receiptId;
    private final int productId;
    private final int quantity;
    
    public SoldProduct(int receiptId, int productId, int quantity) {
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
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        SoldProduct that = (SoldProduct) o;
        return receiptId == that.receiptId && productId == that.productId;
    }
    
    @Override
    public String toString() {
        return "SoldProduct{" + "receiptId=" + receiptId + ", productId=" + productId + ", quantity=" + quantity + '}';
    }
    
    @Override
    public int compareTo(SoldProduct soldProduct) throws NullPointerException {
        if (soldProduct == null) {
            throw new NullPointerException();
        }
        if (receiptId < soldProduct.receiptId) {
            return -1;
        }
        if (receiptId == soldProduct.receiptId) {
            return Integer.compare(receiptId, soldProduct.productId);
        }
        return 1;
    }
}
