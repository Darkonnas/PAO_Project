package root.repository;

import root.model.Cashier;
import root.service.CashierIOService;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

public class CashierRepository {
    private final Set<Cashier> cashiers;
    
    public CashierRepository() {
        cashiers = CashierIOService.getInstance().loadCashiers();
    }
    
    public final Set<Cashier> getCashiers() {
        return Collections.unmodifiableSet(cashiers);
    }
    
    public boolean add(final Cashier cashier) {
        return cashiers.add(cashier);
    }
    
    public boolean remove(final int id) {
        final Cashier cashier = getCashierById(id);
        if (null == cashier) {
            return false;
        }
        return cashiers.remove(cashier);
    }
    
    public Cashier getCashierById(final int id) {
        for (final Cashier cashier : cashiers) {
            if (id == cashier.getId()) {
                return cashier;
            }
        }
        return null;
    }
    
    public Set<Cashier> getCashiersByFirstName(final String firstName) {
        Set<Cashier> result = null;
        for (final Cashier cashier : cashiers) {
            if (firstName.equals(cashier.getFirstName())) {
                if (null == result) {
                    result = new TreeSet<>();
                }
                result.add(cashier);
            }
        }
        return result;
    }
    
    public boolean setCashierFirstName(final int id, final String firstName) {
        final Cashier cashier = getCashierById(id);
        if (null == cashier) {
            return false;
        }
        cashier.setFirstName(firstName);
        return true;
    }
    
    public Set<Cashier> getCashiersByLastName(final String lastName) {
        Set<Cashier> result = null;
        for (final Cashier cashier : cashiers) {
            if (lastName.equals(cashier.getLastName())) {
                if (null == result) {
                    result = new TreeSet<>();
                }
                result.add(cashier);
            }
        }
        return result;
    }
    
    public boolean setCashierLastName(final int id, final String lastName) {
        final Cashier cashier = getCashierById(id);
        if (null == cashier) {
            return false;
        }
        cashier.setLastName(lastName);
        return true;
    }
}
