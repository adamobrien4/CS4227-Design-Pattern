package main.entities;

import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.Objects;

public class RestaurantOwner implements User {
    private ObjectId id;
    private ObjectId restaurantId;
    private String email;
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

    public String toJson() {
        return "Customer Entity formatted in Json";
    }

    public static RestaurantOwner fromDocument(Document document) {
        return new RestaurantOwner(document.getObjectId("_id"), document.getObjectId("restaurant_id"),document.getString("email"), document.getString("password"));
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
}
