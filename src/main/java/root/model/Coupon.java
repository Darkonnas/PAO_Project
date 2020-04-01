package root.model;

public class Coupon implements Comparable<Coupon> {
    private static int availableId = 0;
    private final int id;
    private float sale;
    private boolean used;
    
    public Coupon(float sale) {
        this.id = availableId++;
        this.sale = sale;
        this.used = false;
    }
    
    public int getId() {
        return id;
    }
    
    public float getSale() {
        return sale;
    }
    
    public void setSale(float sale) {
        this.sale = sale;
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
        return "Coupon{" + "id=" + id + ", sale=" + sale + ", used=" + used + '}';
    }
    
    @Override
    public int compareTo(Coupon coupon) throws NullPointerException {
        if (coupon == null) {
            throw new NullPointerException();
        }
        return Integer.compare(id, coupon.id);
    }
}
