package root.model;

import java.util.Objects;

public class Product implements Comparable<Product> {
    private final int id;
    private final int categoryId;
    private final String name;
    private final float price;
    private final Float discount;
    private final int quantity;
    
    public Product(final int id, final int categoryId, final String name, final float price, final Float discount, final int quantity) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
        this.price = price;
        this.discount = discount;
        this.quantity = quantity;
    }
    
    public int getId() {
        return id;
    }
    
    public int getCategoryId() {
        return categoryId;
    }
    
    public String getName() {
        return name;
    }
    
    public float getPrice() {
        return price;
    }
    
    public Float getDiscount() {
        return discount;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    @Override
    public int compareTo(final Product o) throws NullPointerException {
        if (null == o) {
            throw new NullPointerException();
        }
        return Integer.compare(id, o.id);
    }
    
    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", categoryId=" + categoryId + ", name='" + name + '\'' + ", price=" + price + ", discount=" + discount + ", quantity=" + quantity + '}';
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
        final Product product = (Product) obj;
        return id == product.id;
    }
}
