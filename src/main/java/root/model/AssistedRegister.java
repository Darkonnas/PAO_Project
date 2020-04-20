package root.model;

public class AssistedRegister extends Register {
    private int cashierId;
    
    public AssistedRegister() {
        this(-1);
    }
    
    public AssistedRegister(int cashierId) {
        super();
        this.cashierId = cashierId;
    }
    
    public AssistedRegister(boolean active, boolean inUse) {
        this(-1, active, inUse);
    }
    
    public AssistedRegister(int cashierId, boolean active, boolean inUse) {
        super(active, inUse);
        this.cashierId = cashierId;
    }
    
    public int getCashierId() {
        return cashierId;
    }
    
    public void setCashierId(int cashierId) {
        this.cashierId = cashierId;
    }
    
    @Override
    public String toString() {
        return "AssistedRegister{" + "cashierId=" + cashierId + ", id=" + id + ", active=" + active + ", inUse=" + inUse + '}';
    }
}
