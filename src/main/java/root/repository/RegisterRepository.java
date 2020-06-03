package root.repository;

import root.model.AssistedRegister;
import root.model.Register;
import root.model.SelfRegister;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class RegisterRepository extends Repository {
    
    public Set<Register> getRegisters() {
        final Set<String> columns = new TreeSet<>();
        final Map<String, Object> projections = new HashMap<>();
        columns.add("*");
        
        return Collections.unmodifiableSet(query(columns, projections));
    }
    
    public int add(final Register r) {
        return insert(r);
    }
    
    @Override
    protected int insert(final Object obj) {
        final Register register;
        if (AssistedRegister.class == obj.getClass())
            register = (AssistedRegister) obj;
        else
            register = (SelfRegister) obj;
        int inserted = 0;
        try (final BufferedReader reader = new BufferedReader(new FileReader(CONFIG_PATH))) {
            final Properties properties = new Properties();
            properties.load(reader);
            try (final Connection connection = DriverManager.getConnection(properties.getProperty("connection.url"), properties.getProperty("connection.username"), properties.getProperty("connection.password"))) {
                final String sql;
                if (AssistedRegister.class == register.getClass())
                    sql = String.format("INSERT INTO registers VALUES(%s, %d, '%s', '%s')", -1 == ((AssistedRegister) register).getCashierId() ? null : ((AssistedRegister) register).getCashierId(), register.getId(), register.isActive(), register.isInUse());
                else
                    sql = String.format("INSERT INTO registers VALUES(%s, %d, '%s', '%s')", null, register.getId(), register.isActive(), register.isInUse());
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
    protected Set<Register> query(final Set<String> columns, final Map<String, ?> projections) {
        final Set<Register> result = new TreeSet<>();
        try (final BufferedReader reader = new BufferedReader(new FileReader(CONFIG_PATH))) {
            final Properties properties = new Properties();
            properties.load(reader);
            try (final Connection connection = DriverManager.getConnection(properties.getProperty("connection.url"), properties.getProperty("connection.username"), properties.getProperty("connection.password"))) {
                StringBuilder sql = new StringBuilder("SELECT ");
                for (final String column : columns) {
                    sql.append(column).append(", ");
                }
                sql = new StringBuilder(sql.substring(0, sql.toString().lastIndexOf(','))).append(" FROM registers");
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
                        if (null == queryResult.getString(1) && "true".equals(queryResult.getString(3)))
                            result.add(new SelfRegister(queryResult.getInt(2), queryResult.getBoolean(3), queryResult.getBoolean(4)));
                        else
                            result.add(new AssistedRegister(null == queryResult.getString(1) ? -1 : queryResult.getInt(1), queryResult.getInt(2), queryResult.getBoolean(3), queryResult.getBoolean(4)));
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
                StringBuilder sql = new StringBuilder("UPDATE registers SET ");
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
                StringBuilder sql = new StringBuilder("DELETE FROM registers");
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
    
    public Register getRegisterById(final int id) {
        final Set<String> columns = new TreeSet<>();
        final Map<String, Object> projections = new HashMap<>();
        columns.add("*");
        projections.put("id", id);
    
        final Optional<Register> result = Collections.unmodifiableSet(query(columns, projections)).stream().findFirst();
    
        if (result.isPresent()) {
            return result.get();
        }
        return null;
    }
    
    public Set<Register> getRegistersByActiveState(final boolean state) {
        final Set<String> columns = new TreeSet<>();
        final Map<String, Object> projections = new HashMap<>();
        columns.add("*");
        projections.put("active", state);
        
        return Collections.unmodifiableSet(query(columns, projections));
    }
    
    public int setRegisterActiveState(final int id, final boolean state) {
        final Map<String, Object> columns = new HashMap<>();
        final Map<String, Object> projections = new HashMap<>();
        columns.put("active", state);
        projections.put("id", id);
        
        return update(columns, projections);
    }
    
    public Set<Register> getRegistersByInUseState(final boolean state) {
        final Set<String> columns = new TreeSet<>();
        final Map<String, Object> projections = new HashMap<>();
        columns.add("*");
        projections.put("in_use", state);
        
        return Collections.unmodifiableSet(query(columns, projections));
    }
    
    public int setRegisterInUseState(final int id, final boolean state) {
        final Map<String, Object> columns = new HashMap<>();
        final Map<String, Object> projections = new HashMap<>();
        columns.put("in_use", state);
        projections.put("id", id);
        
        return update(columns, projections);
    }
    
    public int assignCashier(final int id, final int cashierId) {
        if (getAssistedRegisters().stream().anyMatch(register -> register.getId() == id)) {
            final Map<String, Object> updates = new HashMap<>();
            final Map<String, Object> projections = new HashMap<>();
            updates.put("cashier_id", cashierId);
            updates.put("active", true);
            projections.put("id", id);
            
            return update(updates, projections);
            
        }
        return 0;
    }
    
    public Set<Register> getAssistedRegisters() {
        final Set<String> columns = new TreeSet<>();
        final Map<String, Object> projections1 = new HashMap<>();
        final Map<String, Object> projections2 = new HashMap<>();
        columns.add("*");
        projections2.put("cashier_id", null);
        projections2.put("active", true);
        
        final Set<Register> result = query(columns, projections1);
        result.removeAll(query(columns, projections2));
        
        return Collections.unmodifiableSet(result);
    }
    
    public int dropCashier(final int id) {
        if (getAssistedRegisters().stream().anyMatch(register -> register.getId() == id && -1 != ((AssistedRegister) register).getCashierId())) {
            final Map<String, Object> updates = new HashMap<>();
            final Map<String, Object> projections = new HashMap<>();
            updates.put("cashier_id", null);
            updates.put("active", false);
            projections.put("id", id);
            
            return update(updates, projections);
            
        }
        return 0;
    }
    
    public Set<Register> getSelfRegisters() {
        final Set<String> columns = new TreeSet<>();
        final Map<String, Object> projections = new HashMap<>();
        columns.add("*");
        projections.put("cashier_id", null);
        projections.put("active", true);
        
        return Collections.unmodifiableSet(query(columns, projections));
    }
}
