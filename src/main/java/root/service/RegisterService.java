package root.service;

import root.model.AssistedRegister;
import root.model.Register;
import root.repository.RegisterRepository;

import java.util.Set;

public final class RegisterService {
    private static RegisterService instance;
    private final RegisterRepository registerRepository;
    
    private RegisterService() {
        registerRepository = new RegisterRepository();
    }
    
    public static RegisterService getInstance() {
        if (null == instance) {
            instance = new RegisterService();
        }
        return instance;
    }
    
    public Set<Register> getRegisters() {
        return registerRepository.getRegisters();
    }
    
    public boolean addRegister(final Register r) {
        if (AssistedRegister.class == r.getClass()) {
            final AssistedRegister assistedRegister = (AssistedRegister) r;
            if (-1 != assistedRegister.getCashierId() && null == CashierService.getInstance().getCashierById(assistedRegister.getCashierId())) {
                return false;
            }
        }
        return registerRepository.add(r);
    }
    
    public boolean removeRegister(final int id) {
        return registerRepository.remove(id);
    }
    
    public Register getRegisterById(final int id) {
        return registerRepository.getRegisterById(id);
    }
    
    public Set<Register> getRegistersByActiveState(final boolean state) {
        return registerRepository.getRegistersByActiveState(state);
    }
    
    public boolean setRegisterActiveState(final int id, final boolean state) {
        return registerRepository.setRegisterActiveState(id, state);
    }
    
    public Set<Register> getRegistersByInUseState(final boolean state) {
        return registerRepository.getRegistersByInUseState(state);
    }
    
    public boolean setRegisterInUseState(final int id, final boolean state) {
        return registerRepository.setRegisterInUseState(id, state);
    }
    
    public Set<Register> getAssistedRegisters() {
        return registerRepository.getAssistedRegisters();
    }
    
    public boolean assignCashier(final int id, final int cashierId) {
        if (null == CashierService.getInstance().getCashierById(cashierId)) {
            return false;
        }
        return registerRepository.assignCashier(id, cashierId);
    }
    
    public boolean dropCashier(final int id) {
        return registerRepository.dropCashier(id);
    }
    
    public Set<Register> getSelfRegisters() {
        return registerRepository.getSelfRegisters();
    }
}
