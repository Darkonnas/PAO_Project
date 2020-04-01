package root.repository;

import root.model.Product;

import java.util.Set;
import java.util.TreeSet;

public class ProductRepository {
    private Set<Product> products;
    
    public ProductRepository() {
        products = new TreeSet<>();
    }
    
    public Set<Product> getProducts() {
        return products;
    }
    
    public boolean add(Product p) {
        if (getProductById(p.getId()) != null) {
            return false;
        }
        return products.add(p);
    }
    
    public Product getProductById(int id) {
        for (Product p : products) {
            if (id == p.getId()) {
                return p;
            }
        }
        return null;
    }
    
    public boolean remove(int id) {
        Product p = getProductById(id);
        if (p == null) {
            return false;
        }
        return products.remove(p);
    }
    
    public Set<Product> getProductsByCategoryId(int categoryId) {
        Set<Product> result = null;
        for (Product p : products) {
            if (categoryId == p.getCategoryId()) {
                if (result == null) {
                    result = new TreeSet<>();
                }
                result.add(p);
            }
        }
        return result;
    }
    
    public boolean setProductCategoryId(int id, int categoryId) {
        Product p = getProductById(id);
        if (p == null) {
            return false;
        }
        p.setCategoryId(categoryId);
        return true;
    }
    
    public Set<Product> getProductsByName(String name) {
        Set<Product> result = null;
        for (Product p : products) {
            if (name.equals(p.getName())) {
                if (result == null) {
                    result = new TreeSet<>();
                }
                result.add(p);
            }
        }
        return result;
    }
    
    public boolean setProductName(int id, String name) {
        Product p = getProductById(id);
        if (p == null) {
            return false;
        }
        p.setName(name);
        return true;
    }
    
    public Set<Product> getProductsByPrice(float price) {
        Set<Product> result = null;
        for (Product p : products) {
            if (Float.compare(price, p.getPrice()) == 0) {
                if (result == null) {
                    result = new TreeSet<>();
                }
                result.add(p);
            }
        }
        return result;
    }
    
    public boolean setProductPrice(int id, float price) {
        Product pc = getProductById(id);
        if (pc == null) {
            return false;
        }
        pc.setPrice(price);
        return true;
    }
    
    public Set<Product> getProductsBySale(float sale) {
        Set<Product> result = null;
        for (Product p : products) {
            if (Float.compare(sale, p.getSale()) == 0) {
                if (result == null) {
                    result = new TreeSet<>();
                }
                result.add(p);
            }
        }
        return result;
    }
    
    public boolean setProductSale(int id, float sale) {
        Product p = getProductById(id);
        if (p == null) {
            return false;
        }
        p.setSale(sale);
        return true;
    }
    
    public Set<Product> getProductsByCount(int count) {
        Set<Product> result = null;
        for (Product p : products) {
            if (count == p.getCount()) {
                if (result == null) {
                    result = new TreeSet<>();
                }
                result.add(p);
            }
        }
        return result;
    }
    
    public boolean setProductCount(int id, int count) {
        Product p = getProductById(id);
        if (p == null) {
            return false;
        }
        p.setCount(count);
        return true;
    }
}
