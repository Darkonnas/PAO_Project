package root.service;

import root.model.Product;
import root.repository.ProductRepository;

import java.util.Set;

public class ProductService {
    private static ProductService instance;
    private ProductRepository productRepository;
    
    private ProductService() {
        productRepository = new ProductRepository();
    }
    
    public static ProductService getInstance() {
        if (instance == null) {
            instance = new ProductService();
        }
        return instance;
    }
    
    public Set<Product> getProducts() {
        return productRepository.getProducts();
    }
    
    public boolean addProduct(Product p) {
        if (ProductCategoryService.getInstance().getProductCategoryById(p.getCategoryId()) == null) {
            return false;
        }
        return productRepository.add(p);
    }
    
    public boolean removeProduct(int id) {
        return productRepository.remove(id);
    }
    
    public Product getProductById(int id) {
        return productRepository.getProductById(id);
    }
    
    public Set<Product> getProductsByCategoryId(int categoryId) {
        return productRepository.getProductsByCategoryId(categoryId);
    }
    
    public boolean setProductCategoryId(int id, int categoryId) {
        if (ProductCategoryService.getInstance().getProductCategoryById(categoryId) == null) {
            return false;
        }
        return productRepository.setProductCategoryId(id, categoryId);
    }
    
    public Set<Product> getProductsByName(String name) {
        return productRepository.getProductsByName(name);
    }
    
    public boolean setProductName(int id, String name) {
        return productRepository.setProductName(id, name);
    }
    
    public Set<Product> getProductsByPrice(float price) {
        return productRepository.getProductsByPrice(price);
    }
    
    public boolean setProductPrice(int id, float price) {
        return productRepository.setProductPrice(id, price);
    }
    
    public Set<Product> getProductsByDiscount(float discount) {
        return productRepository.getProductsByDiscount(discount);
    }
    
    public boolean setProductDiscount(int id, float discount) {
        return productRepository.setProductDiscount(id, discount);
    }
    
    public Set<Product> getProductsByQuantity(int quantity) {
        return productRepository.getProductsByQuantity(quantity);
    }
    
    public boolean setProductQuantity(int id, int quantity) {
        return productRepository.setProductQuantity(id, quantity);
    }
}
