package main.entities.users;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.bson.Document;
import org.bson.types.ObjectId;

@JsonPropertyOrder({"_id", "restaurant", "email", "password"})
@JsonIgnoreProperties(ignoreUnknown = true)
public class RestaurantOwner implements User {
    @JsonProperty("_id")
    private ObjectId id;

    @JsonProperty("restaurant")
    private ObjectId restaurantId;

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

    private static final String TYPE = "restaurantOwner";

    public RestaurantOwner(ObjectId id, ObjectId restaurantId, String email, String password) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.email = email;
        this.password = password;
    }

    public String toString() {
        return "ID: " + id + "\nEmail: " + email + "\n";
    }

    public ObjectId getId() {
        return this.id;
    }

    public String getEmail() {
        return this.email;
    }

    public String getType() {
        return RestaurantOwner.TYPE;
    }

    public String getPassword() {
        return password;
    }

    public boolean equals(RestaurantOwner user) {
        return this.id.toHexString().equals(user.getId().toHexString()) && this.email.equals(user.getEmail());
    }
}
