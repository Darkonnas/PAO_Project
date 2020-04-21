package root.model;

import java.util.Objects;

public class Product implements Comparable<Product> {
    private final int id;
    private int categoryId;
    private String name;
    private float price;
    private float discount;
    private int quantity;
    
    public Product(final int categoryId, final String name, final float price, final int quantity) {
        this(categoryId, name, price, 0.0F, quantity);
    }
    
    public Product(final int categoryId, final String name, final float price, final float discount, final int quantity) {
        id = categoryId + name.hashCode();
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
    
    public void setCategoryId(final int categoryId) {
        this.categoryId = categoryId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public float getPrice() {
        return price;
    }
    
    public void setPrice(final float price) {
        this.price = price;
    }
    
    public float getDiscount() {
        return discount;
    }
    
    public void setDiscount(final float discount) {
        this.discount = discount;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(final int quantity) {
        this.quantity = quantity;
    }
    
    @Override
    public int compareTo(final Product t) throws NullPointerException {
        if (null == t) {
            throw new NullPointerException();
        }
        return Integer.compare(id, t.id);
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
