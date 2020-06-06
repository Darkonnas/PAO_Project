package root.repository;

import root.model.Cashier;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class CashierRepository extends Repository {
    
    public Set<Cashier> getCashiers() {
        final Set<String> columns = new HashSet<>();
        final Map<String, Object> projections = new HashMap<>();
    
        columns.add("*");
    
        return Collections.unmodifiableSet(query(columns, projections));
    }
    
    public int add(final Cashier cashier) {
        return insert(cashier);
    }
    
    @Override
    protected int insert(final Object obj) {
        final Cashier cashier = (Cashier) obj;
        int inserted = 0;
        try (final BufferedReader reader = new BufferedReader(new FileReader(CONFIG_PATH))) {
            final Properties properties = new Properties();
            properties.load(reader);
            try (final Connection connection = DriverManager.getConnection(properties.getProperty("connection.url"), properties.getProperty("connection.username"), properties.getProperty("connection.password"))) {
                final String sql = String.format("INSERT INTO cashiers VALUES(%d, '%s', '%s')", cashier.getId(), cashier.getFirstName(), cashier.getLastName());
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
    protected Set<Cashier> query(final Set<String> columns, final Map<String, ?> projections) {
        final Set<Cashier> result = new TreeSet<>();
        try (final BufferedReader reader = new BufferedReader(new FileReader(CONFIG_PATH))) {
            final Properties properties = new Properties();
            properties.load(reader);
            try (final Connection connection = DriverManager.getConnection(properties.getProperty("connection.url"), properties.getProperty("connection.username"), properties.getProperty("connection.password"))) {
                StringBuilder sql = new StringBuilder("SELECT ");
                for (final String column : columns) {
                    sql.append(column).append(", ");
                }
                sql = new StringBuilder(sql.substring(0, sql.toString().lastIndexOf(','))).append(" FROM cashiers");
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
                        result.add(new Cashier(queryResult.getInt(1), queryResult.getString(2), queryResult.getString(3)));
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
                StringBuilder sql = new StringBuilder("UPDATE cashiers SET ");
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
    
    public int remove(final int id) {
        final Map<String, Object> projections = new HashMap<>();
        projections.put("id", id);
        
        return delete(projections);
    }
    
    public Cashier getCashierById(final int id) {
        final Set<String> columns = new HashSet<>();
        final Map<String, Object> projections = new HashMap<>();
        projections.put("id", id);
        columns.add("*");
    
        return query(columns, projections).stream().findFirst().orElse(null);
    }
    
    public Set<Cashier> getCashiersByFirstName(final String firstName) {
        final Set<String> columns = new HashSet<>();
        final Map<String, Object> projections = new HashMap<>();
        projections.put("first_name", firstName);
        columns.add("*");
        
        return Collections.unmodifiableSet(query(columns, projections));
    }
    
    public int setCashierFirstName(final int id, final String firstName) {
        final Map<String, Object> updates = new HashMap<>();
        final Map<String, Object> projections = new HashMap<>();
        updates.put("first_name", firstName);
        projections.put("id", id);
        
        return update(updates, projections);
    }
    
    public Set<Cashier> getCashiersByLastName(final String lastName) {
        final Set<String> columns = new HashSet<>();
        final Map<String, Object> projections = new HashMap<>();
        projections.put("last_name", lastName);
        columns.add("*");
        
        return Collections.unmodifiableSet(query(columns, projections));
    }
    
    public int setCashierLastName(final int id, final String lastName) {
        final Map<String, Object> updates = new HashMap<>();
        final Map<String, Object> projections = new HashMap<>();
        updates.put("last_name", lastName);
        projections.put("id", id);
        
        return update(updates, projections);
    }
}
