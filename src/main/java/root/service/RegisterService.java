package root.service;

import root.model.CashierRegister;
import root.model.Register;
import root.repository.RegisterRepository;

import java.util.Set;

public class RegisterService {
    private static RegisterService instance;
    private static CashierService cashierService;
    private RegisterRepository registerRepository;
    
    private RegisterService() {
        registerRepository = new RegisterRepository();
    }
    
    public static RegisterService getInstance(CashierService cashierInstance) {
        if (instance == null) {
            instance = new RegisterService();
            cashierService = cashierInstance;
        }
        return instance;
    }
    
    public Set<Register> getRegisters() {
        return registerRepository.getRegisters();
    }
    
    public boolean addRegister(Register r) {
        if (r.getClass() == CashierRegister.class) {
            CashierRegister cashierRegister = (CashierRegister) r;
            if (cashierService.getCashierById(cashierRegister.getCashierId()) == null) {
                return false;
            }
        }
        return registerRepository.add(r);
    }
    
    public boolean removeRegister(int id) {
        return registerRepository.remove(id);
    }
    
    public Register getRegisterById(int id) {
        return registerRepository.getRegisterById(id);
    }
    
    public Set<Register> getRegistersByActiveState(boolean state) {
        return registerRepository.getRegistersByActiveState(state);
    }
    
    public boolean setRegisterActiveState(int id, boolean state) {
        return registerRepository.setRegisterActiveState(id, state);
    }
    
    public Set<Register> getRegistersByInUseState(boolean state) {
        return registerRepository.getRegistersByInUseState(state);
    }
    
    public boolean setRegisterInUseState(int id, boolean state) {
        return registerRepository.setRegisterInUseState(id, state);
    }
    
    public Set<Register> getCashierRegisters() {
        return registerRepository.getCashierRegisters();
    }
    
    public boolean assignCashier(int id, int cashierId) {
        if (cashierService.getCashierById(cashierId) == null) {
            return false;
        }
        return registerRepository.assignCashier(id, cashierId);
    }
    
    public boolean dropCashier(int id) {
        return registerRepository.dropCashier(id);
    }
    
    public Set<Register> getSelfRegisters() {
        return registerRepository.getSelfRegisters();
    }
}
