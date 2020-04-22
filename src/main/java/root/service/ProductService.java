package root.service;

import root.model.Product;
import root.repository.ProductRepository;

import java.sql.Timestamp;
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
        LogService.getInstance().log("Requested products", new Timestamp(System.currentTimeMillis()));
        return productRepository.getProducts();
    }
    
    public boolean addProduct(final Product p) {
        if (null == ProductCategoryService.getInstance().getProductCategoryById(p.getCategoryId())) {
            return false;
        }
        LogService.getInstance().log("Added a product", new Timestamp(System.currentTimeMillis()));
        return productRepository.add(p);
    }
    
    public boolean removeProduct(final int id) {
        LogService.getInstance().log("Removed a product", new Timestamp(System.currentTimeMillis()));
        return productRepository.remove(id);
    }
    
    public Product getProductById(final int id) {
        LogService.getInstance().log("Requested product by id", new Timestamp(System.currentTimeMillis()));
        return productRepository.getProductById(id);
    }
    
    public Set<Product> getProductsByCategoryId(final int categoryId) {
        LogService.getInstance().log("Requested products by categoryId", new Timestamp(System.currentTimeMillis()));
        return productRepository.getProductsByCategoryId(categoryId);
    }
    
    public boolean setProductCategoryId(final int id, final int categoryId) {
        if (null == ProductCategoryService.getInstance().getProductCategoryById(categoryId)) {
            return false;
        }
        LogService.getInstance().log("Set product categoryId", new Timestamp(System.currentTimeMillis()));
        return productRepository.setProductCategoryId(id, categoryId);
    }
    
    public Set<Product> getProductsByName(final String name) {
        LogService.getInstance().log("Requested products by name", new Timestamp(System.currentTimeMillis()));
        return productRepository.getProductsByName(name);
    }
    
    public boolean setProductName(final int id, final String name) {
        LogService.getInstance().log("Set product name", new Timestamp(System.currentTimeMillis()));
        return productRepository.setProductName(id, name);
    }
    
    public Set<Product> getProductsByPrice(final float price) {
        LogService.getInstance().log("Requested products by price", new Timestamp(System.currentTimeMillis()));
        return productRepository.getProductsByPrice(price);
    }
    
    public boolean setProductPrice(final int id, final float price) {
        LogService.getInstance().log("Set product price", new Timestamp(System.currentTimeMillis()));
        return productRepository.setProductPrice(id, price);
    }
    
    public Set<Product> getProductsByDiscount(final float discount) {
        LogService.getInstance().log("Requested products by discount", new Timestamp(System.currentTimeMillis()));
        return productRepository.getProductsByDiscount(discount);
    }
    
    public boolean setProductDiscount(final int id, final float discount) {
        LogService.getInstance().log("Set product discount", new Timestamp(System.currentTimeMillis()));
        return productRepository.setProductDiscount(id, discount);
    }
    
    public Set<Product> getProductsByQuantity(final int quantity) {
        LogService.getInstance().log("Requested products by quantity", new Timestamp(System.currentTimeMillis()));
        return productRepository.getProductsByQuantity(quantity);
    }
    
    public boolean setProductQuantity(final int id, final int quantity) {
        LogService.getInstance().log("Set product quantity", new Timestamp(System.currentTimeMillis()));
        return productRepository.setProductQuantity(id, quantity);
    }
}
