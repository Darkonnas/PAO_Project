package root.repository;

import root.model.ProductCategory;

import java.util.Set;
import java.util.TreeSet;

public class ProductCategoryRepository {
    private Set<ProductCategory> productCategories;
    
    public ProductCategoryRepository() {
        productCategories = new TreeSet<>();
    
    }
    
    public Set<ProductCategory> getProductCategories() {
        return productCategories;
    }
    
    public boolean add(ProductCategory pc) {
        if (getProductCategoryByName(pc.getName()) != null) {
            return false;
        }
        return productCategories.add(pc);
    }
    
    public ProductCategory getProductCategoryByName(String name) {
        for (ProductCategory pc : productCategories) {
            if (name.equals(pc.getName())) {
                return pc;
            }
        }
        return null;
    }
    
    public boolean remove(int id) {
        ProductCategory pc = getProductCategoryById(id);
        if (pc == null) {
            return false;
        }
        return productCategories.remove(pc);
    }
    
    public ProductCategory getProductCategoryById(int id) {
        for (ProductCategory pc : productCategories) {
            if (id == pc.getId()) {
                return pc;
            }
        }
        return null;
    }
    
    public boolean setProductCategoryName(int id, String name) {
        ProductCategory pc = getProductCategoryById(id);
        if (pc == null) {
            return false;
        }
        if (getProductCategoryByName(name) != null) {
            return false;
        }
        pc.setName(name);
        return true;
    }
    
    public boolean setProductCategoryName(String oldName, String newName) {
        ProductCategory pc = getProductCategoryByName(oldName);
        if (pc == null) {
            return false;
        }
        if (getProductCategoryByName(newName) != null) {
            return false;
        }
        pc.setName(newName);
        return true;
    }
}
