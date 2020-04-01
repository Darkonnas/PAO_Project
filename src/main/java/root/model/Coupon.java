package root.model;

public class Coupon implements Comparable<Coupon> {
    private static int availableId = 0;
    private final int id;
    private float discount;
    private boolean used;
    
    public Coupon(float discount) {
        this.id = availableId++;
        this.discount = discount;
        this.used = false;
    }
    
    public int getId() {
        return id;
    }
    
    public float getDiscount() {
        return discount;
    }
    
    public void setDiscount(float discount) {
        this.discount = discount;
    }
    
    public boolean isUsed() {
        return used;
    }
    
    public void setUsed(boolean used) {
        this.used = used;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Coupon coupon = (Coupon) o;
        return id == coupon.id;
    }
    
    @Override
    public String toString() {
        return "Coupon{" + "id=" + id + ", sale=" + discount + ", used=" + used + '}';
    }
    
    @Override
    public int compareTo(Coupon coupon) throws NullPointerException {
        if (coupon == null) {
            throw new NullPointerException();
        }
        return Integer.compare(id, coupon.id);
    }
}
