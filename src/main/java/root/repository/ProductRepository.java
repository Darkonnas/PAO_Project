package root.repository;

import root.model.Product;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class ProductRepository extends Repository {
    
    public Set<Product> getProducts() {
        final Set<String> columns = new TreeSet<>();
        final Map<String, Object> projections = new HashMap<>();
        columns.add("*");
        
        return Collections.unmodifiableSet(query(columns, projections));
    }
    
    public int add(final Product p) {
        return insert(p);
    }
    
    @Override
    protected int insert(final Object obj) {
        final Product product = (Product) obj;
        int inserted = 0;
        try (final BufferedReader reader = new BufferedReader(new FileReader(CONFIG_PATH))) {
            final Properties properties = new Properties();
            properties.load(reader);
            try (final Connection connection = DriverManager.getConnection(properties.getProperty("connection.url"), properties.getProperty("connection.username"), properties.getProperty("connection.password"))) {
                final String sql = String.format("INSERT INTO products VALUES(%d, %d, '%s', %s, %s, %d)", product.getId(), product.getCategoryId(), product.getName(), product.getPrice(), product.getDiscount(), product.getQuantity());
                try (final Statement statement = connection.createStatement()) {
                    inserted = statement.executeUpdate(sql);
                }
            } catch (final SQLException exception) {
                exception.printStackTrace();
            }
        } catch (final IOException e) {
            e.printStackTrace();
        }
        
        return inserted;
    }
    
    @Override
    protected Set<Product> query(final Set<String> columns, final Map<String, ?> projections) {
        final Set<Product> result = new TreeSet<>();
        try (final BufferedReader reader = new BufferedReader(new FileReader(CONFIG_PATH))) {
            final Properties properties = new Properties();
            properties.load(reader);
            try (final Connection connection = DriverManager.getConnection(properties.getProperty("connection.url"), properties.getProperty("connection.username"), properties.getProperty("connection.password"))) {
                StringBuilder sql = new StringBuilder("SELECT ");
                for (final String column : columns) {
                    sql.append(column).append(", ");
                }
                sql = new StringBuilder(sql.substring(0, sql.toString().lastIndexOf(','))).append(" FROM products");
                if (!projections.isEmpty()) {
                    sql.append(" WHERE ");
                    for (final String column : projections.keySet()) {
                        if (null == projections.get(column))
                            sql.append(String.format("%s IS NULL AND ", column));
                        else
                            sql.append(String.format("%s = '%s' AND ", column, projections.get(column)));
                    }
                    sql = new StringBuilder(sql.substring(0, sql.toString().lastIndexOf(" AND ")));
                }
                try (final Statement statement = connection.createStatement()) {
                    final ResultSet queryResult = statement.executeQuery(sql.toString());
                    while (queryResult.next()) {
                        result.add(new Product(queryResult.getInt(1), queryResult.getInt(2), queryResult.getString(3), queryResult.getFloat(4), queryResult.getFloat(5), queryResult.getInt(6)));
                    }
                }
            } catch (final SQLException exception) {
                exception.printStackTrace();
            }
        } catch (final IOException e) {
            e.printStackTrace();
        }
        
        return result;
    }
    
    @Override
    protected int update(final Map<String, ?> updates, final Map<String, ?> projections) {
        int updated = 0;
        try (final BufferedReader reader = new BufferedReader(new FileReader(CONFIG_PATH))) {
            final Properties properties = new Properties();
            properties.load(reader);
            try (final Connection connection = DriverManager.getConnection(properties.getProperty("connection.url"), properties.getProperty("connection.username"), properties.getProperty("connection.password"))) {
                StringBuilder sql = new StringBuilder("UPDATE products SET ");
                for (final String column : updates.keySet()) {
                    if (null == updates.get(column))
                        sql.append(String.format("%s = NULL, ", column));
                    else
                        sql.append(String.format("%s = '%s', ", column, updates.get(column)));
                }
                sql = new StringBuilder(sql.substring(0, sql.toString().lastIndexOf(',')));
                if (!projections.isEmpty()) {
                    sql.append(" WHERE ");
                    for (final String column : projections.keySet()) {
                        if (null == projections.get(column))
                            sql.append(String.format("%s IS NULL AND ", column));
                        else
                            sql.append(String.format("%s = '%s' AND ", column, projections.get(column)));
                    }
                    sql = new StringBuilder(sql.substring(0, sql.toString().lastIndexOf(" AND ")));
                }
                try (final Statement statement = connection.createStatement()) {
                    updated = statement.executeUpdate(sql.toString());
                }
            } catch (final SQLException exception) {
                exception.printStackTrace();
            }
        } catch (final IOException e) {
            e.printStackTrace();
        }
        
        return updated;
    }
    
    @Override
    protected int delete(final Map<String, ?> projections) {
        int deleted = 0;
        try (final BufferedReader reader = new BufferedReader(new FileReader(CONFIG_PATH))) {
            final Properties properties = new Properties();
            properties.load(reader);
            try (final Connection connection = DriverManager.getConnection(properties.getProperty("connection.url"), properties.getProperty("connection.username"), properties.getProperty("connection.password"))) {
                StringBuilder sql = new StringBuilder("DELETE FROM products");
                if (!projections.isEmpty()) {
                    sql.append(" WHERE ");
                    for (final String column : projections.keySet()) {
                        if (null == projections.get(column))
                            sql.append(String.format("%s IS NULL AND ", column));
                        else
                            sql.append(String.format("%s = '%s' AND ", column, projections.get(column)));
                    }
                    sql = new StringBuilder(sql.substring(0, sql.toString().lastIndexOf(" AND ")));
                }
                try (final Statement statement = connection.createStatement()) {
                    deleted = statement.executeUpdate(sql.toString());
                }
            } catch (final SQLException exception) {
                exception.printStackTrace();
            }
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return deleted;
    }
    
    public int remove(final int id) {
        final Map<String, Object> projections = new HashMap<>();
        projections.put("id", id);
        
        return delete(projections);
    }
    
    public Product getProductById(final int id) {
        final Set<String> columns = new TreeSet<>();
        final Map<String, Object> projections = new HashMap<>();
        columns.add("*");
        
        return query(columns, projections).stream().findFirst().get();
    }
    
    public Set<Product> getProductsByCategoryId(final int categoryId) {
        final Set<String> columns = new TreeSet<>();
        final Map<String, Object> projections = new HashMap<>();
        columns.add("*");
        projections.put("category_id", categoryId);
        
        return Collections.unmodifiableSet(query(columns, projections));
    }
    
    public int setProductCategoryId(final int id, final int categoryId) {
        final Map<String, Object> updates = new HashMap<>();
        final Map<String, Object> projections = new HashMap<>();
        updates.put("category_id", categoryId);
        projections.put("id", id);
        
        return update(updates, projections);
    }
    
    public Set<Product> getProductsByName(final String name) {
        final Set<String> columns = new TreeSet<>();
        final Map<String, Object> projections = new HashMap<>();
        columns.add("*");
        projections.put("name", name);
        
        return Collections.unmodifiableSet(query(columns, projections));
    }
    
    public int setProductName(final int id, final String name) {
        final Map<String, Object> updates = new HashMap<>();
        final Map<String, Object> projections = new HashMap<>();
        updates.put("name", name);
        projections.put("id", id);
        
        return update(updates, projections);
    }
    
    public Set<Product> getProductsByPrice(final float price) {
        final Set<String> columns = new TreeSet<>();
        final Map<String, Object> projections = new HashMap<>();
        columns.add("*");
        projections.put("price", price);
        
        return Collections.unmodifiableSet(query(columns, projections));
    }
    
    public int setProductPrice(final int id, final float price) {
        final Map<String, Object> updates = new HashMap<>();
        final Map<String, Object> projections = new HashMap<>();
        updates.put("price", price);
        projections.put("id", id);
        
        return update(updates, projections);
    }
    
    public Set<Product> getProductsByDiscount(final float discount) {
        final Set<String> columns = new TreeSet<>();
        final Map<String, Object> projections = new HashMap<>();
        columns.add("*");
        projections.put("discount", discount);
        
        return Collections.unmodifiableSet(query(columns, projections));
    }
    
    public int setProductDiscount(final int id, final float discount) {
        final Map<String, Object> updates = new HashMap<>();
        final Map<String, Object> projections = new HashMap<>();
        updates.put("discount", discount);
        projections.put("id", id);
        
        return update(updates, projections);
    }
    
    public Set<Product> getProductsByQuantity(final int quantity) {
        final Set<String> columns = new TreeSet<>();
        final Map<String, Object> projections = new HashMap<>();
        columns.add("*");
        projections.put("quantity", quantity);
        
        return Collections.unmodifiableSet(query(columns, projections));
    }
    
    public int setProductQuantity(final int id, final int quantity) {
        final Map<String, Object> updates = new HashMap<>();
        final Map<String, Object> projections = new HashMap<>();
        updates.put("quantity", quantity);
        projections.put("id", id);
        
        return update(updates, projections);
    }
}
