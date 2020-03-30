package root.model;

public class ProductCategory implements Comparable<ProductCategory> {
    private static int availableId = 0;
    private final int id;
    private String name;
    
    public ProductCategory(String name) {
        this.id = availableId++;
        this.name = name;
    }
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ProductCategory productCategory = (ProductCategory) o;
        return id == productCategory.id;
    }
    
    @Override
    public int compareTo(ProductCategory productCategory) throws NullPointerException {
        if (productCategory == null) {
            throw new NullPointerException();
        }
        return Integer.compare(id, productCategory.id);
    }
    
    @Override
    public String toString() {
        return "ProductCategory{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}
