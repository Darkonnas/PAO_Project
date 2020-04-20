package root.model;

public class SelfRegister extends Register {
    public SelfRegister(boolean active, boolean inUse) {
        super(active, inUse);
    }
    
    public SelfRegister() {
        super();
    }
    
    @Override
    public String toString() {
        return "SelfRegister{" + "id=" + id + ", active=" + active + ", inUse=" + inUse + '}';
    }
}
