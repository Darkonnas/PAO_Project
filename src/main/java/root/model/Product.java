package root.model;

public class Product implements Comparable<Product> {
    private final int id;
    private int categoryId;
    private String name;
    private float price;
    private float sale;
    private int count;
    
    public Product(int categoryId, String name, float price, float sale, int count) {
        this.id = categoryId + name.hashCode();
        this.name = name;
        this.categoryId = categoryId;
        this.price = price;
        this.sale = sale;
        this.count = count;
    }
    
    public Product(int categoryId, String name, float price, int count) {
        this(categoryId, name, price, 0F, count);
    }
    
    public int getId() {
        return id;
    }
    
    public int getCategoryId() {
        return categoryId;
    }
    
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public float getPrice() {
        return price;
    }
    
    public void setPrice(float price) {
        this.price = price;
    }
    
    public float getSale() {
        return sale;
    }
    
    public void setSale(float sale) {
        this.sale = sale;
    }
    
    public int getCount() {
        return count;
    }
    
    public void setCount(int count) {
        this.count = count;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Product product = (Product) o;
        return id == product.id;
    }
    
    @Override
    public int compareTo(Product product) throws NullPointerException {
        if (product == null) {
            throw new NullPointerException();
        }
        int firstOrder = Integer.compare(categoryId, product.categoryId);
        if (firstOrder != 0) {
            return firstOrder;
        } else {
            return Integer.compare(id, product.id);
        }
    }
    
    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", categoryId=" + categoryId + ", name='" + name + '\'' + ", price=" + price + ", sale=" + sale + ", count=" + count + '}';
    }
}
