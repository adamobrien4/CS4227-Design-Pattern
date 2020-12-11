package main.factories;

import org.bson.Document;

import main.entities.Customer;
import main.entities.Driver;
import main.entities.RestaurantOwner;
import main.entities.User;

public class UserFactory {
    public User createUser(Document userDoc) {
        if(userDoc.getString("type") == null) {
            return null;
        }

        switch(userDoc.getString("type")) {
            case "customer":
                return Customer.fromDocument(userDoc);
            case "restaurant_owner":
                return RestaurantOwner.fromDocument(userDoc);
            case "delivery_driver":
                return Driver.fromDocument(userDoc);
        }

        return null;
    }
}
