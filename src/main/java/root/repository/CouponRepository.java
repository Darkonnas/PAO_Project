package root.repository;

import root.model.Coupon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class CouponRepository extends Repository {
    
    public Set<Coupon> getCoupons() {
        final Set<String> columns = new TreeSet<>();
        final Map<String, Object> projections = new HashMap<>();
        columns.add("*");
        
        return Collections.unmodifiableSet(query(columns, projections));
    }
    
    public int add(final Coupon coupon) {
        return insert(coupon);
    }
    
    @Override
    protected int insert(final Object obj) {
        final Coupon coupon = (Coupon) obj;
        int inserted = 0;
        try (final BufferedReader reader = new BufferedReader(new FileReader(CONFIG_PATH))) {
            final Properties properties = new Properties();
            properties.load(reader);
            try (final Connection connection = DriverManager.getConnection(properties.getProperty("connection.url"), properties.getProperty("connection.username"), properties.getProperty("connection.password"))) {
                final String sql = String.format("INSERT INTO coupons VALUES(%d, %s, '%s')", coupon.getId(), coupon.getDiscount(), coupon.isUsed());
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
    protected Set<Coupon> query(final Set<String> columns, final Map<String, ?> projections) {
        final Set<Coupon> result = new TreeSet<>();
        try (final BufferedReader reader = new BufferedReader(new FileReader(CONFIG_PATH))) {
            final Properties properties = new Properties();
            properties.load(reader);
            try (final Connection connection = DriverManager.getConnection(properties.getProperty("connection.url"), properties.getProperty("connection.username"), properties.getProperty("connection.password"))) {
                StringBuilder sql = new StringBuilder("SELECT ");
                for (final String column : columns) {
                    sql.append(column).append(", ");
                }
                sql = new StringBuilder(sql.substring(0, sql.toString().lastIndexOf(','))).append(" FROM coupons");
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
                        result.add(new Coupon(queryResult.getInt(1), queryResult.getFloat(2), queryResult.getBoolean(3)));
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
                StringBuilder sql = new StringBuilder("UPDATE coupons SET ");
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
                StringBuilder sql = new StringBuilder("DELETE FROM coupons");
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
    
    public Coupon getCouponById(final int id) {
        final Set<String> columns = new TreeSet<>();
        final Map<String, Object> projections = new HashMap<>();
        columns.add("*");
        projections.put("id", id);
    
        final Optional<Coupon> result = query(columns, projections).stream().findFirst();
    
        if (result.isPresent()) {
            return result.get();
        }
        return null;
    }
    
    public Set<Coupon> getCouponsByDiscount(final float discount) {
        final Set<String> columns = new TreeSet<>();
        final Map<String, Object> projections = new HashMap<>();
        columns.add("*");
        projections.put("discount", discount);
        
        return Collections.unmodifiableSet(query(columns, projections));
    }
    
    public int setCouponDiscount(final int id, final float discount) {
        final Map<String, Object> updates = new HashMap<>();
        final Map<String, Object> projections = new HashMap<>();
        updates.put("discount", discount);
        projections.put("id", id);
        
        return update(updates, projections);
    }
    
    public Set<Coupon> getCouponsByUsedState(final boolean state) {
        final Set<String> columns = new TreeSet<>();
        final Map<String, Object> projections = new HashMap<>();
        columns.add("*");
        projections.put("used", state);
        
        return Collections.unmodifiableSet(query(columns, projections));
    }
    
    public int setCouponUsedState(final int id, final boolean state) {
        final Map<String, Object> updates = new HashMap<>();
        final Map<String, Object> projections = new HashMap<>();
        updates.put("used", state);
        projections.put("id", id);
        
        return update(updates, projections);
    }
}
