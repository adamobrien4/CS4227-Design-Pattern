package main.entities.users;

import org.bson.types.ObjectId;

public interface User {

    String CUSTOMER = "customer";
    String RESTAURANT_OWNER = "restaurantOwner";
    String DRIVER = "driver";

    // Stringify the entity
   String toString();

    // Returns the curent users email
    String getEmail();

    // Returns the users id
    ObjectId getId();

    // Returns the current users type
    String getType();

    String getPassword();
}
