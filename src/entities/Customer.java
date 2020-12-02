package entities;

public class Customer implements User {
    private int id;
    private String email;

    public String toString() {
        return "ID: " + id + "\nEmail: " + email + "\n";
    }

    public String toJson() {
        return "Customer Entity formatted in Json";
    }

    public void fromJson() {
        // Create a new Customer entity given a json string/map
    }
}
