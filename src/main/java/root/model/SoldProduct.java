package root.model;

public class SoldProduct implements Comparable<SoldProduct> {
    private final int receiptId;
    private final int productId;
    private final int count;
    
    public SoldProduct(int receiptId, int productId, int count) {
        this.receiptId = receiptId;
        this.productId = productId;
        this.count = count;
    }
    
    public int getReceiptId() {
        return receiptId;
    }
    
    public int getProductId() {
        return productId;
    }
    
    public int getCount() {
        return count;
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
        return "SoldProduct{" + "receiptId=" + receiptId + ", productId=" + productId + ", count=" + count + '}';
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
