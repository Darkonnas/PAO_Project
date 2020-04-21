package root.repository;

import root.model.Product;
import root.service.ProductIOService;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

public class ProductRepository {
    private final Set<Product> products;
    
    public ProductRepository() {
        products = ProductIOService.getInstance().loadProducts();
    }
    
    public Set<Product> getProducts() {
        return Collections.unmodifiableSet(products);
    }
    
    public boolean add(final Product p) {
        if (null != getProductById(p.getId())) {
            return false;
        }
        return products.add(p);
    }
    
    public Product getProductById(final int id) {
        for (final Product p : products) {
            if (id == p.getId()) {
                return p;
            }
        }
        return null;
    }
    
    public boolean remove(final int id) {
        final Product p = getProductById(id);
        if (null == p) {
            return false;
        }
        return products.remove(p);
    }
    
    public Set<Product> getProductsByCategoryId(final int categoryId) {
        Set<Product> result = null;
        for (final Product p : products) {
            if (categoryId == p.getCategoryId()) {
                if (null == result) {
                    result = new TreeSet<>();
                }
                result.add(p);
            }
        }
        return result;
    }
    
    public boolean setProductCategoryId(final int id, final int categoryId) {
        final Product p = getProductById(id);
        if (null == p) {
            return false;
        }
        p.setCategoryId(categoryId);
        return true;
    }
    
    public Set<Product> getProductsByName(final String name) {
        Set<Product> result = null;
        for (final Product p : products) {
            if (name.equals(p.getName())) {
                if (null == result) {
                    result = new TreeSet<>();
                }
                result.add(p);
            }
        }
        return result;
    }
    
    public boolean setProductName(final int id, final String name) {
        final Product p = getProductById(id);
        if (null == p) {
            return false;
        }
        p.setName(name);
        return true;
    }
    
    public Set<Product> getProductsByPrice(final float price) {
        Set<Product> result = null;
        for (final Product p : products) {
            if (0 == Float.compare(price, p.getPrice())) {
                if (null == result) {
                    result = new TreeSet<>();
                }
                result.add(p);
            }
        }
        return result;
    }
    
    public boolean setProductPrice(final int id, final float price) {
        final Product pc = getProductById(id);
        if (null == pc) {
            return false;
        }
        pc.setPrice(price);
        return true;
    }
    
    public Set<Product> getProductsByDiscount(final float discount) {
        Set<Product> result = null;
        for (final Product p : products) {
            if (0 == Float.compare(discount, p.getDiscount())) {
                if (null == result) {
                    result = new TreeSet<>();
                }
                result.add(p);
            }
        }
        return result;
    }
    
    public boolean setProductDiscount(final int id, final float discount) {
        final Product p = getProductById(id);
        if (null == p) {
            return false;
        }
        p.setDiscount(discount);
        return true;
    }
    
    public Set<Product> getProductsByQuantity(final int quantity) {
        Set<Product> result = null;
        for (final Product p : products) {
            if (quantity == p.getQuantity()) {
                if (null == result) {
                    result = new TreeSet<>();
                }
                result.add(p);
            }
        }
        return result;
    }
    
    public boolean setProductQuantity(final int id, final int quantity) {
        final Product p = getProductById(id);
        if (null == p) {
            return false;
        }
        p.setQuantity(quantity);
        return true;
    }
}
