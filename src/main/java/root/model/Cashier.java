package root.model;

import java.io.Serializable;

public class Cashier implements Comparable<Cashier>, Serializable {
    private static int availableId = 0;
    private final int id;
    private String firstName;
    private String lastName;
    
    public Cashier(String firstName, String lastName) {
        this.id = availableId++;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    public int getId() {
        return id;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Cashier cashier = (Cashier) o;
        return this.id == cashier.id;
    }
    
    @Override
    public String toString() {
        return "Cashier{" + "id=" + id + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + '}';
    }
    
    @Override
    public int compareTo(Cashier c) throws NullPointerException {
        if (c == null) {
            throw new NullPointerException();
        }
        return Integer.compare(this.id, c.id);
    }
}
