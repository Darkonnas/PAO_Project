package root.model;

import java.util.Objects;

public class AssistedRegister extends Register {
    private int cashierId;
    
    public AssistedRegister() {
        this(-1);
    }
    
    public AssistedRegister(final int cashierId) {
        this.cashierId = cashierId;
    }
    
    public AssistedRegister(final boolean active, final boolean inUse) {
        this(-1, active, inUse);
    }
    
    public AssistedRegister(final int cashierId, final boolean active, final boolean inUse) {
        super(active, inUse);
        this.cashierId = cashierId;
    }
    
    public int getCashierId() {
        return cashierId;
    }
    
    public void setCashierId(final int cashierId) {
        this.cashierId = cashierId;
    }
    
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (null == obj || getClass() != obj.getClass()) {
            return false;
        }
        final Register register = (Register) obj;
        return id == register.id;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return "AssistedRegister{" + "cashierId=" + cashierId + ", id=" + id + ", active=" + active + ", inUse=" + inUse + '}';
    }
    
    @Override
    public int compareTo(final Register t) throws NullPointerException {
        if (null == t) {
            throw new NullPointerException();
        }
        return Integer.compare(id, t.id);
    }
}
