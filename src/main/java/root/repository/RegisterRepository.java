package root.repository;

import root.model.AssistedRegister;
import root.model.Register;
import root.model.SelfRegister;
import root.service.RegisterIOService;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

public class RegisterRepository {
    private final Set<Register> registers;
    
    public RegisterRepository() {
        registers = RegisterIOService.getInstance().loadRegisters();
    }
    
    public Set<Register> getRegisters() {
        return Collections.unmodifiableSet(registers);
    }
    
    public boolean add(final Register r) {
        return registers.add(r);
    }
    
    public boolean remove(final int id) {
        final Register r = getRegisterById(id);
        if (null == r) {
            return false;
        }
        return registers.remove(r);
    }
    
    public Register getRegisterById(final int id) {
        for (final Register r : registers) {
            if (id == r.getId()) {
                return r;
            }
        }
        return null;
    }
    
    public Set<Register> getRegistersByActiveState(final boolean state) {
        Set<Register> result = null;
        for (final Register r : registers) {
            if (r.isActive() == state) {
                if (null == result) {
                    result = new TreeSet<>();
                }
                result.add(r);
            }
        }
        return result;
    }
    
    public boolean setRegisterActiveState(final int id, final boolean state) {
        final Register r = getRegisterById(id);
        if (null == r) {
            return false;
        }
        r.setActive(state);
        return true;
    }
    
    public Set<Register> getRegistersByInUseState(final boolean state) {
        Set<Register> result = null;
        for (final Register r : registers) {
            if (r.isInUse() == state) {
                if (null == result) {
                    result = new TreeSet<>();
                }
                result.add(r);
            }
        }
        return result;
    }
    
    public boolean setRegisterInUseState(final int id, final boolean state) {
        final Register r = getRegisterById(id);
        if (null == r) {
            return false;
        }
        r.setInUse(state);
        return true;
    }
    
    public Set<Register> getAssistedRegisters() {
        Set<Register> result = null;
        for (final Register r : registers) {
            if (AssistedRegister.class == r.getClass()) {
                if (null == result) {
                    result = new TreeSet<>();
                }
                result.add(r);
            }
        }
        return result;
    }
    
    public boolean assignCashier(final int id, final int cashierId) {
        final Register r = getRegisterById(id);
        if (null == r || AssistedRegister.class != r.getClass()) {
            return false;
        }
        final AssistedRegister assistedRegister = (AssistedRegister) r;
        if (-1 != assistedRegister.getCashierId()) {
            return false;
        }
        assistedRegister.setCashierId(cashierId);
        assistedRegister.setActive(true);
        return true;
    }
    
    public boolean dropCashier(final int id) {
        final Register r = getRegisterById(id);
        if (null == r || AssistedRegister.class != r.getClass()) {
            return false;
        }
        final AssistedRegister assistedRegister = (AssistedRegister) r;
        if (-1 == assistedRegister.getCashierId()) {
            return false;
        }
        assistedRegister.setCashierId(-1);
        assistedRegister.setActive(false);
        return true;
    }
    
    public Set<Register> getSelfRegisters() {
        Set<Register> result = null;
        for (final Register r : registers) {
            if (SelfRegister.class == r.getClass()) {
                if (null == result) {
                    result = new TreeSet<>();
                }
                result.add(r);
            }
        }
        return result;
    }
}
