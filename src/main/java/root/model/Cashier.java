package root.model;

import java.io.Serializable;
import java.util.Objects;

public class Cashier implements Comparable<Cashier>, Serializable {
    private static int availableId;
    private final int id;
    private String firstName;
    private String lastName;
    
    public Cashier(final String firstName, final String lastName) {
        id = availableId++;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    public int getId() {
        return id;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }
    
    @Override
    public int compareTo(final Cashier t) throws NullPointerException {
        if (null == t) {
            throw new NullPointerException();
        }
        return Integer.compare(id, t.id);
    }
    
    @Override
    public String toString() {
        return "Cashier{" + "id=" + id + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + '}';
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (null == obj || getClass() != obj.getClass())
            return false;
        final Cashier cashier = (Cashier) obj;
        return id == cashier.id;
    }
}
