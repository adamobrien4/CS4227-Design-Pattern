package main.entities;

import org.bson.Document;
import org.bson.types.ObjectId;

public class Customer implements User {
    private ObjectId id;
    private String email;
    private String password;
    private static final String TYPE = "customer";

    public Customer(String email, String password) {
        this.email = email;
        this.password = password;
    }

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

    public Document toDocument() {
        Document doc = new Document("email", this.email)
        .append("password", this.password);
    
        return doc;
    }

    public String getEmail() {
        return this.email;
    }

    public String getType() {
        return Customer.TYPE;
    }
}
