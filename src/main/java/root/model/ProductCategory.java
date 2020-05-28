package root.model;

import java.util.Objects;

public class ProductCategory implements Comparable<ProductCategory> {
    private final int id;
    private final String name;
    
    public ProductCategory(final int id, final String name) {
        this.id = id;
        this.name = name;
    }
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    @Override
    public int compareTo(final ProductCategory o) throws NullPointerException {
        if (null == o) {
            throw new NullPointerException();
        }
        return Integer.compare(id, o.id);
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
