package root.repository;

import root.model.SoldProduct;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class SoldProductRepository extends Repository {
    
    public Set<SoldProduct> getSoldProducts() {
        final Set<String> columns = new HashSet<>();
        final Map<String, Object> projections = new HashMap<>();
        columns.add("*");
        
        return Collections.unmodifiableSet(query(columns, projections));
    }
    
    public int add(final SoldProduct sp) {
        return insert(sp);
    }
    
    @Override
    protected int insert(final Object obj) {
        final SoldProduct soldProduct = (SoldProduct) obj;
        int inserted = 0;
        try (final BufferedReader reader = new BufferedReader(new FileReader(CONFIG_PATH))) {
            final Properties properties = new Properties();
            properties.load(reader);
            try (final Connection connection = DriverManager.getConnection(properties.getProperty("connection.url"), properties.getProperty("connection.username"), properties.getProperty("connection.password"))) {
                final String sql = String.format("INSERT INTO sold_products VALUES(%d, %d, %d)", soldProduct.getReceiptId(), soldProduct.getProductId(), soldProduct.getQuantity());
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
    protected Set<SoldProduct> query(final Set<String> columns, final Map<String, ?> projections) {
        final Set<SoldProduct> result = new HashSet<>();
        try (final BufferedReader reader = new BufferedReader(new FileReader(CONFIG_PATH))) {
            final Properties properties = new Properties();
            properties.load(reader);
            try (final Connection connection = DriverManager.getConnection(properties.getProperty("connection.url"), properties.getProperty("connection.username"), properties.getProperty("connection.password"))) {
                StringBuilder sql = new StringBuilder("SELECT ");
                for (final String column : columns) {
                    sql.append(column).append(", ");
                }
                sql = new StringBuilder(sql.substring(0, sql.toString().lastIndexOf(','))).append(" FROM sold_products");
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
                        result.add(new SoldProduct(queryResult.getInt(1), queryResult.getInt(2), queryResult.getInt(3)));
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
                StringBuilder sql = new StringBuilder("UPDATE sold_products SET ");
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
                StringBuilder sql = new StringBuilder("DELETE FROM cashiers");
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
    
    public int remove(final int receiptId, final int productId) {
        final Map<String, Object> projections = new HashMap<>();
        projections.put("receipt_id", receiptId);
        projections.put("product_id", productId);
        
        return delete(projections);
    }
    
    public SoldProduct getSoldProductByReceiptIdAndProductId(final int receiptId, final int productId) {
        final Set<String> columns = new HashSet<>();
        final Map<String, Object> projections = new HashMap<>();
        columns.add("*");
        projections.put("receipt_id", receiptId);
        projections.put("product_id", productId);
        
        return Collections.unmodifiableSet(query(columns, projections)).stream().findFirst().get();
    }
    
    public Set<SoldProduct> getSoldProductsByReceiptId(final int receiptId) {
        final Set<String> columns = new HashSet<>();
        final Map<String, Object> projections = new HashMap<>();
        columns.add("*");
        projections.put("receipt_id", receiptId);
        
        return Collections.unmodifiableSet(query(columns, projections));
    }
    
    public Set<SoldProduct> getSoldProductsByProductId(final int productId) {
        final Set<String> columns = new HashSet<>();
        final Map<String, Object> projections = new HashMap<>();
        columns.add("*");
        projections.put("product_id", productId);
        
        return Collections.unmodifiableSet(query(columns, projections));
    }
    
    public Set<SoldProduct> getSoldProductsByQuantity(final int quantity) {
        final Set<String> columns = new HashSet<>();
        final Map<String, Object> projections = new HashMap<>();
        columns.add("*");
        projections.put("quantity", quantity);
        
        return Collections.unmodifiableSet(query(columns, projections));
    }
}
