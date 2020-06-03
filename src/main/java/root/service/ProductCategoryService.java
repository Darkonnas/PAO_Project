package root.service;

import root.model.ProductCategory;
import root.repository.ProductCategoryRepository;

import java.sql.Timestamp;
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
        LogService.getInstance().log("Requested product categories", new Timestamp(System.currentTimeMillis()), Thread.currentThread().getName());
        return productCategoryRepository.getProductCategories();
    }
    
    public int addProductCategory(final ProductCategory pc) {
        LogService.getInstance().log("Added a product category", new Timestamp(System.currentTimeMillis()), Thread.currentThread().getName());
        return productCategoryRepository.add(pc);
    }
    
    public int removeProductCategory(final int id) {
        LogService.getInstance().log("Removed a product category", new Timestamp(System.currentTimeMillis()), Thread.currentThread().getName());
        return productCategoryRepository.remove(id);
    }
    
    public ProductCategory getProductCategoryById(final int id) {
        LogService.getInstance().log("Requested product category by id", new Timestamp(System.currentTimeMillis()), Thread.currentThread().getName());
        return productCategoryRepository.getProductCategoryById(id);
    }
    
    public ProductCategory getProductCategoryByName(final String name) {
        LogService.getInstance().log("Requested product category by name", new Timestamp(System.currentTimeMillis()), Thread.currentThread().getName());
        return productCategoryRepository.getProductCategoryByName(name);
    }
    
    public int setProductCategoryName(final int id, final String name) {
        LogService.getInstance().log("Set product category name", new Timestamp(System.currentTimeMillis()), Thread.currentThread().getName());
        return productCategoryRepository.setProductCategoryName(id, name);
    }
    
    public int setProductCategoryName(final String oldName, final String newName) {
        LogService.getInstance().log("Set product category name", new Timestamp(System.currentTimeMillis()), Thread.currentThread().getName());
        return productCategoryRepository.setProductCategoryName(oldName, newName);
    }
}
