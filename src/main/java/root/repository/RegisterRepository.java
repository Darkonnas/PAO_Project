package root.repository;

import root.model.AssistedRegister;
import root.model.Register;
import root.model.SelfRegister;

import java.util.Set;
import java.util.TreeSet;

public class RegisterRepository {
    private Set<Register> registers;
    
    public RegisterRepository() {
        registers = new TreeSet<>();
    }
    
    public Set<Register> getRegisters() {
        return registers;
    }
    
    public boolean add(Register r) {
        return registers.add(r);
    }
    
    public boolean remove(int id) {
        Register r = getRegisterById(id);
        if (r == null) {
            return false;
        }
        return registers.remove(r);
    }
    
    public Register getRegisterById(int id) {
        for (Register r : registers) {
            if (id == r.getId()) {
                return r;
            }
        }
        return null;
    }
    
    public Set<Register> getRegistersByActiveState(boolean state) {
        Set<Register> result = null;
        for (Register r : registers) {
            if (r.isActive() == state) {
                if (result == null) {
                    result = new TreeSet<>();
                }
                result.add(r);
            }
        }
        return result;
    }
    
    public boolean setRegisterActiveState(int id, boolean state) {
        Register r = getRegisterById(id);
        if (r == null) {
            return false;
        }
        r.setActive(state);
        return true;
    }
    
    public Set<Register> getRegistersByInUseState(boolean state) {
        Set<Register> result = null;
        for (Register r : registers) {
            if (r.isInUse() == state) {
                if (result == null) {
                    result = new TreeSet<>();
                }
                result.add(r);
            }
        }
        return result;
    }
    
    public boolean setRegisterInUseState(int id, boolean state) {
        Register r = getRegisterById(id);
        if (r == null) {
            return false;
        }
        r.setInUse(state);
        return true;
    }
    
    public Set<Register> getAssistedRegisters() {
        Set<Register> result = null;
        for (Register r : registers) {
            if (r.getClass() == AssistedRegister.class) {
                if (result == null) {
                    result = new TreeSet<>();
                }
                result.add(r);
            }
        }
        return result;
    }
    
    public boolean assignCashier(int id, int cashierId) {
        Register r = getRegisterById(id);
        if (r == null || r.getClass() != AssistedRegister.class) {
            return false;
        }
        AssistedRegister assistedRegister = (AssistedRegister) r;
        if (assistedRegister.getCashierId() != -1) {
            return false;
        }
        assistedRegister.setCashierId(cashierId);
        assistedRegister.setActive(true);
        return true;
    }
    
    public boolean dropCashier(int id) {
        Register r = getRegisterById(id);
        if (r == null || r.getClass() != AssistedRegister.class) {
            return false;
        }
        AssistedRegister assistedRegister = (AssistedRegister) r;
        if (assistedRegister.getCashierId() == -1) {
            return false;
        }
        assistedRegister.setCashierId(-1);
        assistedRegister.setActive(false);
        return true;
    }
    
    public Set<Register> getSelfRegisters() {
        Set<Register> result = null;
        for (Register r : registers) {
            if (r.getClass() == SelfRegister.class) {
                if (result == null) {
                    result = new TreeSet<>();
                }
                result.add(r);
            }
        }
        return result;
    }
}
