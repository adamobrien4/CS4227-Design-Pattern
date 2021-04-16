package main.entities.users;

import org.bson.types.ObjectId;

public interface User {

    public static final String CUSTOMER = "customer";
    public static final String RESTAURANT_OWNER = "restaurantOwner";
    public static final String DRIVER = "driver";

    // Stringify the entity
    public String toString();

    // Returns the curent users email
    public String getEmail();

    // Returns the users id
    public ObjectId getId();

    // Returns the current users type
    public String getType();

    public String getPassword();
}
