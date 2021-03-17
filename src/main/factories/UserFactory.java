package main.factories;

import main.entities.users.Customer;
import main.entities.users.Driver;
import main.entities.users.RestaurantOwner;
import main.entities.users.User;
import org.bson.types.ObjectId;

import java.util.Map;

public class UserFactory {
    public User createUser(Map<String, String> data) {
        switch(data.get("type")) {
            case User.CUSTOMER:
                return new Customer(new ObjectId(data.get("_id")), data.get("email"), data.get("password"), data.get("address"));
            case User.DRIVER:
                return new Driver(new ObjectId(data.get("_id")), data.get("email"), data.get("password"));
            case User.RESTAURANT_OWNER:
                return new RestaurantOwner(new ObjectId(data.get("_id")), new ObjectId(data.get("restaurant")), data.get("email"), data.get("password"));
            default:
                System.out.println("Unknown user type: " + data.get("type"));
                return null;
        }
    }
}
