package main.entities;

public interface User {

    public static final String CUSTOMER = "customer";
    public static final String RESTAURANT_OWNER = "restaurantOwner";
    public static final String DRIVER = "driver";

    // Stringify the entity
    public String toString();

    // Convert the entity to Json format
    public String toJson();

    public String getEmail();

    public String getType();
}
