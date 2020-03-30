package root.service;

import root.model.Product;
import root.repository.ProductRepository;

import java.util.Set;

public class ProductService {
    private static ProductService instance;
    private static ProductCategoryService productCategoryService;
    private ProductRepository productRepository;
    
    private ProductService() {
        productRepository = new ProductRepository();
    }
    
    public static ProductService getInstance(ProductCategoryService productCategoryInstance) {
        if(instance == null) {
            instance = new ProductService();
            productCategoryService = productCategoryInstance;
        }
        return instance;
    }
    
    public Set<Product> getProducts() {
        return productRepository.getProducts();
    }
    
    public boolean addProduct(Product p) {
        if(productCategoryService.getProductCategoryById(p.getCategoryId()) == null) {
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
        if(productCategoryService.getProductCategoryById(categoryId) == null) {
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
    
    public Set<Product> getProductsBySale(float sale) {
        return productRepository.getProductsBySale(sale);
    }
    
    public boolean setProductSale(int id, float sale) {
        return productRepository.setProductSale(id, sale);
    }
    
    public Set<Product> getProductsByCount(int count) { return productRepository.getProductsByCount(count);}
    
    public boolean setProductCount(int id, int count) { return productRepository.setProductCount(id, count);}
}
