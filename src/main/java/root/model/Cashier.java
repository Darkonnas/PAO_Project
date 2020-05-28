package root.model;

import java.io.Serializable;
import java.util.Objects;

public class Cashier implements Comparable<Cashier>, Serializable {
    private final int id;
    private final String firstName;
    private final String lastName;
    
    public Cashier(final int id, final String firstName, final String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    public int getId() {
        return id;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    @Override
    public int compareTo(final Cashier o) throws NullPointerException {
        if (null == o) {
            throw new NullPointerException();
        }
        return Integer.compare(id, o.id);
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
