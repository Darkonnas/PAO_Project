package root.service;

import root.model.Cashier;
import root.repository.CashierRepository;

import java.util.Set;

public final class CashierService {
    private static CashierService instance;
    private final CashierRepository cashierRepository;
    
    private CashierService() {
        cashierRepository = new CashierRepository();
    }
    
    public static CashierService getInstance() {
        if (null == instance) {
            instance = new CashierService();
        }
        return instance;
    }
    
    public Set<Cashier> getCashiers() {
        return cashierRepository.getCashiers();
    }
    
    public boolean addCashier(final Cashier cashier) {
        return cashierRepository.add(cashier);
    }
    
    public boolean removeCashier(final int id) {
        return cashierRepository.remove(id);
    }
    
    public Cashier getCashierById(final int id) {
        return cashierRepository.getCashierById(id);
    }
    
    public Set<Cashier> getCashiersByFirstName(final String firstName) {
        return cashierRepository.getCashiersByFirstName(firstName);
    }
    
    public boolean setCashierFirstName(final int id, final String firstName) {
        return cashierRepository.setCashierFirstName(id, firstName);
    }
    
    public Set<Cashier> getCashiersByLastName(final String lastName) {
        return cashierRepository.getCashiersByLastName(lastName);
    }
    
    public boolean setCashierLastName(final int id, final String lastName) {
        return cashierRepository.setCashierLastName(id, lastName);
    }
}
