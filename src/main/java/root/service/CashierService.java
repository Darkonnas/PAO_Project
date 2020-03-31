package root.service;

import root.model.Cashier;
import root.repository.CashierRepository;

import java.util.Set;

public class CashierService {
    private static CashierService instance;
    private CashierRepository cashierRepository;
    
    private CashierService() {
        cashierRepository = new CashierRepository();
    }
    
    public static CashierService getInstance() {
        if (instance == null) {
            instance = new CashierService();
        }
        return instance;
    }
    
    public Set<Cashier> getCashiers() {
        return cashierRepository.getCashiers();
    }
    
    public boolean addCashier(Cashier c) {
        return cashierRepository.add(c);
    }
    
    public boolean removeCashier(int id) {
        return cashierRepository.remove(id);
    }
    
    public Cashier getCashierById(int id) {
        return cashierRepository.getCashierById(id);
    }
    
    public Set<Cashier> getCashiersByFirstName(String firstName) {
        return cashierRepository.getCashiersByFirstName(firstName);
    }
    
    public boolean setCashierFirstName(int id, String firstName) {
        return cashierRepository.setCashierFirstName(id, firstName);
    }
    
    public Set<Cashier> getCashiersByLastName(String lastName) {
        return cashierRepository.getCashiersByLastName(lastName);
    }
    
    public boolean setCashierLastName(int id, String lastName) {
        return cashierRepository.setCashierLastName(id, lastName);
    }
}
