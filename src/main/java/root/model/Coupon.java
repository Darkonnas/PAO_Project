package root.model;

import java.util.Objects;

public class Coupon implements Comparable<Coupon> {
    private final int id;
    private final float discount;
    private final boolean used;
    
    public Coupon(final int id, final float discount, final boolean used) {
        this.id = id;
        this.discount = discount;
        this.used = used;
    }
    
    public int getId() {
        return id;
    }
    
    public float getDiscount() {
        return discount;
    }
    
    public boolean isUsed() {
        return used;
    }
    
    @Override
    public int compareTo(final Coupon o) throws NullPointerException {
        if (null == o) {
            throw new NullPointerException();
        }
        return Integer.compare(id, o.id);
    }
    
    @Override
    public String toString() {
        return "Coupon{" + "id=" + id + ", discount=" + discount + ", used=" + used + '}';
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
        final Coupon coupon = (Coupon) obj;
        return id == coupon.id;
    }
}
