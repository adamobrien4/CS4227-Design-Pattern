package main.entities;

import org.bson.Document;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

public class Customer implements User {
    private ObjectId id;
    @BsonProperty(value = "email")
    private String email;
    @BsonProperty(value = "password")
    private String password;
    @BsonProperty(value = "address")
    private String address;
    private static final String TYPE = "customer";

    public Customer() {

    }

    public Customer(String email, String password, String address) {
        this.email = email;
        this.password = password;
        this.address = address;
    }

    public Customer(ObjectId id, String email, String password, String address) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.address = address;
    }

    public String toString() {
        return "ID: " + id + "\nEmail: " + email + "\nAddress: " + address;
    }

    public static Customer fromDocument(Document document) {
        return new Customer(document.getObjectId("_id"), document.getString("email"), document.getString("password"), document.getString("address"));
    }

    public ObjectId getId() {
        return this.id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {this.email = email;}

    public String getAddress() { 
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return Customer.TYPE;
    }

    // TODO: Remove in future
    public Document toDocument() {
        Document doc = new Document("email", this.email)
                .append("password", this.password);

        return doc;
    }
}
