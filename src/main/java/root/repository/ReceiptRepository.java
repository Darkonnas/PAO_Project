package root.repository;

import root.model.Receipt;
import root.service.CouponService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class ReceiptRepository extends Repository {
    
    public Set<Receipt> getReceipts() {
        final Set<String> columns = new HashSet<>();
        final Map<String, Object> projections = new HashMap<>();
        columns.add("*");
        
        return Collections.unmodifiableSet(query(columns, projections));
    }
    
    public int add(final Receipt r) {
        return insert(r) + CouponService.getInstance().setCouponUsedState(r.getCouponId(), true);
    }
    
    @Override
    protected int insert(final Object obj) {
        final Receipt receipt = (Receipt) obj;
        int inserted = 0;
        try (final BufferedReader reader = new BufferedReader(new FileReader(CONFIG_PATH))) {
            final Properties properties = new Properties();
            properties.load(reader);
            try (final Connection connection = DriverManager.getConnection(properties.getProperty("connection.url"), properties.getProperty("connection.username"), properties.getProperty("connection.password"))) {
                final String sql = String.format("INSERT INTO receipts VALUES(%d, %d, %s, %s)", receipt.getId(), receipt.getRegisterId(), receipt.getCashierId(), receipt.getCouponId());
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
    protected Set<Receipt> query(final Set<String> columns, final Map<String, ?> projections) {
        final Set<Receipt> result = new TreeSet<>();
        try (final BufferedReader reader = new BufferedReader(new FileReader(CONFIG_PATH))) {
            final Properties properties = new Properties();
            properties.load(reader);
            try (final Connection connection = DriverManager.getConnection(properties.getProperty("connection.url"), properties.getProperty("connection.username"), properties.getProperty("connection.password"))) {
                StringBuilder sql = new StringBuilder("SELECT ");
                for (final String column : columns) {
                    sql.append(column).append(", ");
                }
                sql = new StringBuilder(sql.substring(0, sql.toString().lastIndexOf(','))).append(" FROM receipts");
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
                        result.add(new Receipt(queryResult.getInt(1), queryResult.getInt(2), queryResult.getInt(3), queryResult.getInt(4)));
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
                StringBuilder sql = new StringBuilder("UPDATE receipts SET ");
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
                StringBuilder sql = new StringBuilder("DELETE FROM receipts");
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
    
    public Receipt getReceiptById(final int id) {
        final Set<String> columns = new HashSet<>();
        final Map<String, Object> projections = new HashMap<>();
        columns.add("*");
        projections.put("id", id);
    
        final Optional<Receipt> result = query(columns, projections).stream().findFirst();
    
        if (result.isPresent()) {
            return result.get();
        }
        return null;
    }
    
    public Set<Receipt> getReceiptsByRegisterId(final int registerId) {
        final Set<String> columns = new HashSet<>();
        final Map<String, Object> projections = new HashMap<>();
        columns.add("*");
        projections.put("register_id", registerId);
    
        return Collections.unmodifiableSet(query(columns, projections));
    }
    
    public Set<Receipt> getReceiptsByCashierId(final Integer cashierId) {
        final Set<String> columns = new HashSet<>();
        final Map<String, Object> projections = new HashMap<>();
        columns.add("*");
        projections.put("cashier_id", cashierId);
        
        return Collections.unmodifiableSet(query(columns, projections));
    }
    
    public Set<Receipt> getReceiptsByCouponId(final Integer couponId) {
        final Set<String> columns = new HashSet<>();
        final Map<String, Object> projections = new HashMap<>();
        columns.add("*");
        projections.put("coupon_id", couponId);
        
        return Collections.unmodifiableSet(query(columns, projections));
    }
}
