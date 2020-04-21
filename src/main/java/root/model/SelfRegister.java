package root.model;

import java.util.Objects;

public class SelfRegister extends Register {
    public SelfRegister(final boolean active, final boolean inUse) {
        super(active, inUse);
    }
    
    public SelfRegister() {
    }
    
    @Override
    public int compareTo(final Register t) throws NullPointerException {
        if (null == t) {
            throw new NullPointerException();
        }
        return Integer.compare(id, t.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return "SelfRegister{" + "id=" + id + ", active=" + active + ", inUse=" + inUse + '}';
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
}
