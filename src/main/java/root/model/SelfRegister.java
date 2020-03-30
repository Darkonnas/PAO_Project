package root.model;

public class SelfRegister extends Register {
    public SelfRegister() {
        super();
        this.active = true;
    }
    
    @Override
    public String toString() {
        return "SelfRegister{" + "id=" + id + ", active=" + active + ", inUse=" + inUse + '}';
    }
}
