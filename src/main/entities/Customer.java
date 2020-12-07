package main.entities;

import org.bson.Document;
import org.bson.types.ObjectId;

public class Customer implements User {
    private ObjectId id;
    private String email;
    private String password;

    public Customer(ObjectId id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public String toString() {
        return "ID: " + id + "\nEmail: " + email + "\n";
    }

    public String toJson() {
        return "Customer Entity formatted in Json";
    }

    public static Customer fromDocument(Document document) {
        return new Customer(document.getObjectId("_id"), document.getString("email"), document.getString("password"));
    }

    public ObjectId getId() {
        return this.id;
    }
}
