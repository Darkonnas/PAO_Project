package root.repository;

import root.model.Product;

import java.util.Set;
import java.util.TreeSet;

public class ProductRepository {
    private Set<Product> products;
    
    public ProductRepository() {
        products = new TreeSet<>();
        products.add(new Product(0, "Apple", 4.99F, 30));
        products.add(new Product(0, "Banana", 7.59F, 20));
        products.add(new Product(1, "Blue Shirt", 10.69F, 30F, 10));
        products.add(new Product(2, "The New York Times", 5.99F, 50));
        products.add(new Product(2, "The Sun", 6.39F, 46));
        products.add(new Product(3, "Domestos", 7.99F, 35));
        products.add(new Product(3, "Mr Proper", 9.29F, 10F, 20));
        products.add(new Product(4, "Soy milk", 5.49F, 20));
        products.add(new Product(5, "Graham Bread", 3.49F, 25));
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
