package root.model;

public abstract class Register implements Comparable<Register> {
    final int id;
    final boolean active;
    final boolean inUse;
    
    Register(final int id, final boolean active, final boolean inUse) {
        this.id = id;
        this.active = active;
        this.inUse = inUse;
    }
    
    public int getId() {
        return id;
    }
    
    public boolean isActive() {
        return active;
    }
    
    public boolean isInUse() {
        return inUse;
    }
    
    @Override
    public abstract int hashCode();
    
    @Override
    public abstract boolean equals(final Object obj);
}
