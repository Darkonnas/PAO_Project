package root.model;

import java.util.Objects;

public class Receipt implements Comparable<Receipt> {
    private final int id;
    private final int registerId;
    private final Integer cashierId;
    private final Integer couponId;
    
    public Receipt(final int id, final int registerId, final Integer cashierId, final Integer couponId) {
        this.id = id;
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
    
    public Integer getCashierId() {
        return cashierId;
    }
    
    public Integer getCouponId() {
        return couponId;
    }
    
    @Override
    public int compareTo(final Receipt o) throws NullPointerException {
        if (null == o) {
            throw new NullPointerException();
        }
        return Integer.compare(id, o.id);
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
