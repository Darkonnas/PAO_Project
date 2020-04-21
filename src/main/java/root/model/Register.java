package root.model;

public abstract class Register implements Comparable<Register> {
    private static int availableId;
    final int id;
    boolean active;
    boolean inUse;
    
    Register() {
        this(false, false);
    }
    
    Register(final boolean active, final boolean inUse) {
        id = availableId++;
        this.active = active;
        this.inUse = inUse;
    }
    
    public int getId() {
        return id;
    }
    
    public boolean isActive() {
        return active;
    }
    
    public void setActive(final boolean active) {
        this.active = active;
    }
    
    public boolean isInUse() {
        return inUse;
    }
    
    public void setInUse(final boolean inUse) {
        this.inUse = inUse;
    }
    
    @Override
    public abstract int hashCode();
    
    @Override
    public abstract boolean equals(final Object obj);
}
