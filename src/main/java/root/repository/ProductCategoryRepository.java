package root.repository;

import root.model.ProductCategory;
import root.service.ProductCategoryIOService;

import java.util.Collections;
import java.util.Set;

public class ProductCategoryRepository {
    private final Set<ProductCategory> productCategories;
    
    public ProductCategoryRepository() {
        productCategories = ProductCategoryIOService.getInstance().loadProductCategories();
        
    }
    
    public Set<ProductCategory> getProductCategories() {
        return Collections.unmodifiableSet(productCategories);
    }
    
    public boolean add(final ProductCategory pc) {
        if (null != getProductCategoryByName(pc.getName())) {
            return false;
        }
        return productCategories.add(pc);
    }
    
    public ProductCategory getProductCategoryByName(final String name) {
        for (final ProductCategory pc : productCategories) {
            if (name.equals(pc.getName())) {
                return pc;
            }
        }
        return null;
    }
    
    public boolean remove(final int id) {
        final ProductCategory pc = getProductCategoryById(id);
        if (null == pc) {
            return false;
        }
        return productCategories.remove(pc);
    }
    
    public ProductCategory getProductCategoryById(final int id) {
        for (final ProductCategory pc : productCategories) {
            if (id == pc.getId()) {
                return pc;
            }
        }
        return null;
    }
    
    public boolean setProductCategoryName(final int id, final String name) {
        final ProductCategory pc = getProductCategoryById(id);
        if (null == pc) {
            return false;
        }
        if (null != getProductCategoryByName(name)) {
            return false;
        }
        pc.setName(name);
        return true;
    }
    
    public boolean setProductCategoryName(final String oldName, final String newName) {
        final ProductCategory pc = getProductCategoryByName(oldName);
        if (null == pc) {
            return false;
        }
        if (null != getProductCategoryByName(newName)) {
            return false;
        }
        pc.setName(newName);
        return true;
    }
}
