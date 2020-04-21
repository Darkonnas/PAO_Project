package root.model;

import java.util.Objects;

public class ProductCategory implements Comparable<ProductCategory> {
    private static int availableId;
    private final int id;
    private String name;
    
    public ProductCategory(final String name) {
        id = availableId++;
        this.name = name;
    }
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    @Override
    public int compareTo(final ProductCategory t) throws NullPointerException {
        if (null == t) {
            throw new NullPointerException();
        }
        return Integer.compare(id, t.id);
    }
    
    @Override
    public String toString() {
        return "ProductCategory{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (null == obj || getClass() != obj.getClass()) {
            return false;
        }
        final ProductCategory productCategory = (ProductCategory) obj;
        return id == productCategory.id;
    }
}
