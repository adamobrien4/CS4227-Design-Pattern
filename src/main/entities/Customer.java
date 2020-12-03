package main.entities;

import org.bson.Document;
import org.bson.types.ObjectId;

public class Customer implements User {
    private ObjectId id;
    private String email;
    private String password;

    public Customer(ObjectId id, String email) {
        this.id = id;
        this.email = email;
    }

    public String toString() {
        return "ID: " + id + "\nEmail: " + email + "\n";
    }

    public String toJson() {
        return "Customer Entity formatted in Json";
    }

    public void fromJson() {
        // Create a new Customer entity given a json string/map
    }

    public static Customer fromDocument(Document document) {
        return new Customer(document.getObjectId("_id"), document.getString("email"));
    }
}
