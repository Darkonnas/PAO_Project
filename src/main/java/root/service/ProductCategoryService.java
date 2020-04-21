package root.service;

import root.model.ProductCategory;
import root.repository.ProductCategoryRepository;

import java.util.Set;

public final class ProductCategoryService {
    private static ProductCategoryService instance;
    private final ProductCategoryRepository productCategoryRepository;
    
    private ProductCategoryService() {
        productCategoryRepository = new ProductCategoryRepository();
    }
    
    public static ProductCategoryService getInstance() {
        if (null == instance) {
            instance = new ProductCategoryService();
        }
        return instance;
    }
    
    public Set<ProductCategory> getProductCategories() {
        return productCategoryRepository.getProductCategories();
    }
    
    public boolean addProductCategory(final ProductCategory pc) {
        return productCategoryRepository.add(pc);
    }
    
    public boolean removeProductCategory(final int id) {
        return productCategoryRepository.remove(id);
    }
    
    public ProductCategory getProductCategoryById(final int id) {
        return productCategoryRepository.getProductCategoryById(id);
    }
    
    public ProductCategory getProductCategoryByName(final String name) {
        return productCategoryRepository.getProductCategoryByName(name);
    }
    
    public boolean setProductCategoryName(final int id, final String name) {
        return productCategoryRepository.setProductCategoryName(id, name);
    }
    
    public boolean setProductCategoryName(final String oldName, final String newName) {
        return productCategoryRepository.setProductCategoryName(oldName, newName);
    }
}
