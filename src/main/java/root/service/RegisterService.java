package root.service;

import root.model.AssistedRegister;
import root.model.Register;
import root.repository.RegisterRepository;

import java.sql.Timestamp;
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
        LogService.getInstance().log("Requested registers", new Timestamp(System.currentTimeMillis()));
        return registerRepository.getRegisters();
    }
    
    public boolean addRegister(final Register r) {
        if (AssistedRegister.class == r.getClass()) {
            final AssistedRegister assistedRegister = (AssistedRegister) r;
            if (-1 != assistedRegister.getCashierId() && null == CashierService.getInstance().getCashierById(assistedRegister.getCashierId())) {
                return false;
            }
        }
        LogService.getInstance().log("Added a register", new Timestamp(System.currentTimeMillis()));
        return registerRepository.add(r);
    }
    
    public boolean removeRegister(final int id) {
        LogService.getInstance().log("Removed a register", new Timestamp(System.currentTimeMillis()));
        return registerRepository.remove(id);
    }
    
    public Register getRegisterById(final int id) {
        LogService.getInstance().log("Requested register by id", new Timestamp(System.currentTimeMillis()));
        return registerRepository.getRegisterById(id);
    }
    
    public Set<Register> getRegistersByActiveState(final boolean state) {
        LogService.getInstance().log("Requested registers by active state", new Timestamp(System.currentTimeMillis()));
        return registerRepository.getRegistersByActiveState(state);
    }
    
    public boolean setRegisterActiveState(final int id, final boolean state) {
        LogService.getInstance().log("Set register active state", new Timestamp(System.currentTimeMillis()));
        return registerRepository.setRegisterActiveState(id, state);
    }
    
    public Set<Register> getRegistersByInUseState(final boolean state) {
        LogService.getInstance().log("Requested registers by inUse state", new Timestamp(System.currentTimeMillis()));
        return registerRepository.getRegistersByInUseState(state);
    }
    
    public boolean setRegisterInUseState(final int id, final boolean state) {
        LogService.getInstance().log("Set register inUse state", new Timestamp(System.currentTimeMillis()));
        return registerRepository.setRegisterInUseState(id, state);
    }
    
    public Set<Register> getAssistedRegisters() {
        LogService.getInstance().log("Requested assisted registers", new Timestamp(System.currentTimeMillis()));
        return registerRepository.getAssistedRegisters();
    }
    
    public boolean assignCashier(final int id, final int cashierId) {
        if (null == CashierService.getInstance().getCashierById(cashierId)) {
            return false;
        }
        LogService.getInstance().log("Assigned assisted register cashier", new Timestamp(System.currentTimeMillis()));
        return registerRepository.assignCashier(id, cashierId);
    }
    
    public boolean dropCashier(final int id) {
        LogService.getInstance().log("Dropped assisted register cashier", new Timestamp(System.currentTimeMillis()));
        return registerRepository.dropCashier(id);
    }
    
    public Set<Register> getSelfRegisters() {
        LogService.getInstance().log("Requested self registers", new Timestamp(System.currentTimeMillis()));
        return registerRepository.getSelfRegisters();
    }
}
