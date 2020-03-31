package root.model;

public abstract class Register implements Comparable<Register> {
    protected static int availableId = 0;
    protected final int id;
    protected boolean active;
    protected boolean inUse;
    
    public Register() {
        this.id = availableId++;
        this.active = false;
        this.inUse = false;
    }
    
    public int getId() {
        return id;
    }
    
    public boolean isActive() {
        return active;
    }
    
    public void setActive(boolean active) {
        this.active = active;
    }
    
    public boolean isInUse() {
        return inUse;
    }
    
    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        Register register = (Register) obj;
        return this.id == register.id;
    }
    
    @Override
    public String toString() {
        return "Register{" + "id=" + id + ", active=" + active + ", inUse=" + inUse + '}';
    }
    
    @Override
    public int compareTo(Register register) throws NullPointerException {
        if (register == null) {
            throw new NullPointerException();
        }
        return Integer.compare(id, register.id);
    }
}
