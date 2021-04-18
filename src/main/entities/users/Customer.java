package main.entities.users;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.bson.types.ObjectId;

@JsonPropertyOrder({"_id", "email", "password", "address"})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer implements User {
    @JsonProperty("_id")
    private ObjectId id;
    @JsonProperty("email")
    private String email;
    @JsonProperty("password")
    private String password;
    @JsonProperty("address")
    private String address;
    @JsonProperty("type")
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

    public boolean equals(Customer user) {
        return this.id.toHexString().equals(user.getId().toHexString()) && this.email.equals(user.getEmail()) && this.address.equals(user.getAddress());
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
