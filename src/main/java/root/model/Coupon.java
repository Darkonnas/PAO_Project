package root.model;

import java.util.Objects;

public class Coupon implements Comparable<Coupon> {
    private static int availableId;
    private final int id;
    private float discount;
    private boolean used;
    
    public Coupon(final float discount) {
        this(discount, false);
    }
    
    public Coupon(final float discount, final boolean used) {
        id = availableId++;
        this.discount = discount;
        this.used = used;
    }
    
    public int getId() {
        return id;
    }
    
    public float getDiscount() {
        return discount;
    }
    
    public void setDiscount(final float discount) {
        this.discount = discount;
    }
    
    public boolean isUsed() {
        return used;
    }
    
    public void setUsed(final boolean used) {
        this.used = used;
    }
    
    @Override
    public int compareTo(final Coupon t) throws NullPointerException {
        if (null == t) {
            throw new NullPointerException();
        }
        return Integer.compare(id, t.id);
    }
    
    @Override
    public String toString() {
        return "Coupon{" + "id=" + id + ", sale=" + discount + ", used=" + used + '}';
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
