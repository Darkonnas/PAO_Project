package root.service;

import root.model.Product;
import root.repository.ProductRepository;

import java.util.Set;

public final class ProductService {
    private static ProductService instance;
    private final ProductRepository productRepository;
    
    private ProductService() {
        productRepository = new ProductRepository();
    }
    
    public static ProductService getInstance() {
        if (null == instance) {
            instance = new ProductService();
        }
        return instance;
    }
    
    public Set<Product> getProducts() {
        return productRepository.getProducts();
    }
    
    public boolean addProduct(final Product p) {
        if (null == ProductCategoryService.getInstance().getProductCategoryById(p.getCategoryId())) {
            return false;
        }
        return productRepository.add(p);
    }
    
    public boolean removeProduct(final int id) {
        return productRepository.remove(id);
    }
    
    public Product getProductById(final int id) {
        return productRepository.getProductById(id);
    }
    
    public Set<Product> getProductsByCategoryId(final int categoryId) {
        return productRepository.getProductsByCategoryId(categoryId);
    }
    
    public boolean setProductCategoryId(final int id, final int categoryId) {
        if (null == ProductCategoryService.getInstance().getProductCategoryById(categoryId)) {
            return false;
        }
        return productRepository.setProductCategoryId(id, categoryId);
    }
    
    public Set<Product> getProductsByName(final String name) {
        return productRepository.getProductsByName(name);
    }
    
    public boolean setProductName(final int id, final String name) {
        return productRepository.setProductName(id, name);
    }
    
    public Set<Product> getProductsByPrice(final float price) {
        return productRepository.getProductsByPrice(price);
    }
    
    public boolean setProductPrice(final int id, final float price) {
        return productRepository.setProductPrice(id, price);
    }
    
    public Set<Product> getProductsByDiscount(final float discount) {
        return productRepository.getProductsByDiscount(discount);
    }
    
    public boolean setProductDiscount(final int id, final float discount) {
        return productRepository.setProductDiscount(id, discount);
    }
    
    public Set<Product> getProductsByQuantity(final int quantity) {
        return productRepository.getProductsByQuantity(quantity);
    }
    
    public boolean setProductQuantity(final int id, final int quantity) {
        return productRepository.setProductQuantity(id, quantity);
    }
}
