package root.repository;

import root.model.Cashier;

import java.util.Set;
import java.util.TreeSet;

public class CashierRepository {
    private Set<Cashier> cashiers;
    
    public CashierRepository() {
        cashiers = new TreeSet<>();
    }
    
    public Set<Cashier> getCashiers() {
        return cashiers;
    }
    
    public boolean add(Cashier c) {
        return cashiers.add(c);
    }
    
    public boolean remove(int id) {
        Cashier c = getCashierById(id);
        if (c == null) {
            return false;
        }
        return cashiers.remove(c);
    }
    
    public Cashier getCashierById(int id) {
        for (Cashier c : cashiers) {
            if (id == c.getId()) {
                return c;
            }
        }
        return null;
    }
    
    public Set<Cashier> getCashiersByFirstName(String firstName) {
        Set<Cashier> result = null;
        for (Cashier c : cashiers) {
            if (firstName.equals(c.getFirstName())) {
                if (result == null) {
                    result = new TreeSet<>();
                }
                result.add(c);
            }
        }
        return result;
    }
    
    public boolean setCashierFirstName(int id, String firstName) {
        Cashier c = getCashierById(id);
        if (c == null) {
            return false;
        }
        c.setFirstName(firstName);
        return true;
    }
    
    public Set<Cashier> getCashiersByLastName(String lastName) {
        Set<Cashier> result = null;
        for (Cashier c : cashiers) {
            if (lastName.equals(c.getLastName())) {
                if (result == null) {
                    result = new TreeSet<>();
                }
                result.add(c);
            }
        }
        return result;
    }
    
    public boolean setCashierLastName(int id, String lastName) {
        Cashier c = getCashierById(id);
        if (c == null) {
            return false;
        }
        c.setLastName(lastName);
        return true;
    }
}
