package root.model;

import java.util.Objects;

public class Receipt implements Comparable<Receipt> {
    private static int availableId;
    private final int id;
    private final int registerId;
    private final int cashierId;
    private final int couponId;
    
    public Receipt(final int registerId, final int cashierId, final int couponId) {
        id = availableId++;
        this.registerId = registerId;
        this.cashierId = cashierId;
        this.couponId = couponId;
    }
    
    public int getId() {
        return id;
    }
    
    public int getRegisterId() {
        return registerId;
    }
    
    public int getCashierId() {
        return cashierId;
    }
    
    public int getCouponId() {
        return couponId;
    }
    
    @Override
    public int compareTo(final Receipt t) throws NullPointerException {
        if (null == t) {
            throw new NullPointerException();
        }
        return Integer.compare(id, t.id);
    }
    
    @Override
    public String toString() {
        return "Receipt{" + "id=" + id + ", registerId=" + registerId + ", cashierId=" + cashierId + ", couponId=" + couponId + '}';
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (null == obj || getClass() != obj.getClass())
            return false;
        final Receipt receipt = (Receipt) obj;
        return id == receipt.id;
    }
}
