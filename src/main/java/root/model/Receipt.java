package root.model;

public class Receipt implements Comparable<Receipt> {
    private static int availableId = 0;
    private final int id;
    private final int registerId;
    private final int cashierId;
    
    public Receipt(int registerId) {
        this(registerId, -1);
    }
    
    public Receipt(int registerId, int cashierId) {
        this.id = availableId++;
        this.registerId = registerId;
        this.cashierId = cashierId;
    }
    
    public int getId() {
        return id;
    }
    
    public int getRegisterId() {
        return registerId;
    }
    
    public int getCashierId() {
        return cashierId;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Receipt receipt = (Receipt) o;
        return id == receipt.id;
    }
    
    @Override
    public String toString() {
        return "Receipt{" + "id=" + id + ", registerId=" + registerId + ", cashierId=" + cashierId + '}';
    }
    
    @Override
    public int compareTo(Receipt receipt) throws NullPointerException {
        if (receipt == null) {
            throw new NullPointerException();
        }
        return Integer.compare(id, receipt.id);
    }
}
