package root.service;

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
        LogService.getInstance().log("Requested registers", new Timestamp(System.currentTimeMillis()), Thread.currentThread().getName());
        return registerRepository.getRegisters();
    }
    
    public int addRegister(final Register r) {
        LogService.getInstance().log("Added a register", new Timestamp(System.currentTimeMillis()), Thread.currentThread().getName());
        return registerRepository.add(r);
    }
    
    public int removeRegister(final int id) {
        LogService.getInstance().log("Removed a register", new Timestamp(System.currentTimeMillis()), Thread.currentThread().getName());
        return registerRepository.remove(id);
    }
    
    public Register getRegisterById(final int id) {
        LogService.getInstance().log("Requested register by id", new Timestamp(System.currentTimeMillis()), Thread.currentThread().getName());
        return registerRepository.getRegisterById(id);
    }
    
    public Set<Register> getRegistersByActiveState(final boolean state) {
        LogService.getInstance().log("Requested registers by active state", new Timestamp(System.currentTimeMillis()), Thread.currentThread().getName());
        return registerRepository.getRegistersByActiveState(state);
    }
    
    public int setRegisterActiveState(final int id, final boolean state) {
        LogService.getInstance().log("Set register active state", new Timestamp(System.currentTimeMillis()), Thread.currentThread().getName());
        return registerRepository.setRegisterActiveState(id, state);
    }
    
    public Set<Register> getRegistersByInUseState(final boolean state) {
        LogService.getInstance().log("Requested registers by inUse state", new Timestamp(System.currentTimeMillis()), Thread.currentThread().getName());
        return registerRepository.getRegistersByInUseState(state);
    }
    
    public int setRegisterInUseState(final int id, final boolean state) {
        LogService.getInstance().log("Set register inUse state", new Timestamp(System.currentTimeMillis()), Thread.currentThread().getName());
        return registerRepository.setRegisterInUseState(id, state);
    }
    
    public Set<Register> getAssistedRegisters() {
        LogService.getInstance().log("Requested assisted registers", new Timestamp(System.currentTimeMillis()), Thread.currentThread().getName());
        return registerRepository.getAssistedRegisters();
    }
    
    public int assignCashier(final int id, final Integer cashierId) {
        LogService.getInstance().log("Assigned assisted register cashier", new Timestamp(System.currentTimeMillis()), Thread.currentThread().getName());
        return registerRepository.assignCashier(id, cashierId);
    }
    
    public int dropCashier(final int id) {
        LogService.getInstance().log("Dropped assisted register cashier", new Timestamp(System.currentTimeMillis()), Thread.currentThread().getName());
        return registerRepository.dropCashier(id);
    }
    
    public Set<Register> getSelfRegisters() {
        LogService.getInstance().log("Requested self registers", new Timestamp(System.currentTimeMillis()), Thread.currentThread().getName());
        return registerRepository.getSelfRegisters();
    }
}
