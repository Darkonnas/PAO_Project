package root.service;

import root.model.ProductCategory;
import root.repository.ProductCategoryRepository;

import java.util.Set;

public class ProductCategoryService {
    private static ProductCategoryService instance;
    private ProductCategoryRepository productCategoryRepository;
    
    private ProductCategoryService() {
        productCategoryRepository = new ProductCategoryRepository();
    }
    
    public static ProductCategoryService getInstance() {
        if (instance == null) {
            instance = new ProductCategoryService();
        }
        return instance;
    }
    
    public Set<ProductCategory> getProductCategories() {
        return productCategoryRepository.getProductCategories();
    }
    
    public boolean addProductCategory(ProductCategory pc) {
        return productCategoryRepository.add(pc);
    }
    
    public boolean removeProductCategory(int id) {
        return productCategoryRepository.remove(id);
    }
    
    public ProductCategory getProductCategoryById(int id) {
        return productCategoryRepository.getProductCategoryById(id);
    }
    
    public ProductCategory getProductCategoryByName(String name) {
        return productCategoryRepository.getProductCategoryByName(name);
    }
    
    public boolean setProductCategoryName(int id, String name) {
        return productCategoryRepository.setProductCategoryName(id, name);
    }
    
    public boolean setProductCategoryName(String oldName, String newName) {
        return productCategoryRepository.setProductCategoryName(oldName, newName);
    }
}
