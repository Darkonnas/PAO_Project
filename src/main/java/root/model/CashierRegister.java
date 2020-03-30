package root.model;

public class CashierRegister extends Register {
    private int cashierId;
    
    public CashierRegister(int cashierId) {
        super();
        this.cashierId = cashierId;
        this.active = true;
    }
    
    public CashierRegister() {
        this(-1);
        this.active = false;
    }
    
    public int getCashierId() {
        return cashierId;
    }
    
    public void setCashierId(int cashierId) {
        this.cashierId = cashierId;
    }
    
    @Override
    public String toString() {
        return "CashierRegister{" + "cashierId=" + cashierId + ", id=" + id + ", active=" + active + ", inUse=" + inUse + '}';
    }
}
