package root.model;

import java.util.Objects;

public class AssistedRegister extends Register {
    private final Integer cashierId;
    
    public AssistedRegister(final Integer cashierId, final int id, final boolean active, final boolean inUse) {
        super(id, active, inUse);
        this.cashierId = cashierId;
    }
    
    public Integer getCashierId() {
        return cashierId;
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
    public int compareTo(final Register o) throws NullPointerException {
        if (null == o) {
            throw new NullPointerException();
        }
        return Integer.compare(id, o.id);
    }
}
