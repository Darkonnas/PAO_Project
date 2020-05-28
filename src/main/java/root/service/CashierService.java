package root.service;

import root.model.Cashier;
import root.repository.CashierRepository;

import java.sql.Timestamp;
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
        LogService.getInstance().log("Requested cashiers", new Timestamp(System.currentTimeMillis()));
        return cashierRepository.getCashiers();
    }
    
    public int addCashier(final Cashier cashier) {
        LogService.getInstance().log("Added a cashier", new Timestamp(System.currentTimeMillis()));
        return cashierRepository.add(cashier);
    }
    
    public int removeCashier(final int id) {
        LogService.getInstance().log("Removed a cashier", new Timestamp(System.currentTimeMillis()));
        return cashierRepository.remove(id);
    }
    
    public Cashier getCashierById(final int id) {
        LogService.getInstance().log("Requested cashier by id", new Timestamp(System.currentTimeMillis()));
        return cashierRepository.getCashierById(id);
    }
    
    public Set<Cashier> getCashiersByFirstName(final String firstName) {
        LogService.getInstance().log("Requested cashiers by firstName", new Timestamp(System.currentTimeMillis()));
        return cashierRepository.getCashiersByFirstName(firstName);
    }
    
    public int setCashierFirstName(final int id, final String firstName) {
        LogService.getInstance().log("Set cashier firstName", new Timestamp(System.currentTimeMillis()));
        return cashierRepository.setCashierFirstName(id, firstName);
    }
    
    public Set<Cashier> getCashiersByLastName(final String lastName) {
        LogService.getInstance().log("Requested cashiers by lastName", new Timestamp(System.currentTimeMillis()));
        return cashierRepository.getCashiersByLastName(lastName);
    }
    
    public int setCashierLastName(final int id, final String lastName) {
        LogService.getInstance().log("Set cashier lastName", new Timestamp(System.currentTimeMillis()));
        return cashierRepository.setCashierLastName(id, lastName);
    }
}
