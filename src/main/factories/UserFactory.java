package main.factories;

import org.bson.Document;

import main.entities.users.Customer;
import main.entities.users.Driver;
import main.entities.users.RestaurantOwner;
import main.entities.users.User;

public class UserFactory {

    public UserFactory(){}

    public User createUser(Document userDoc) {
//        if(userDoc.getString("type") == null) {
//            return null;
//        }
//
//        switch(userDoc.getString("type")) {
//            case User.CUSTOMER:
//                return Customer.fromDocument(userDoc);
//            case User.RESTAURANT_OWNER:
//                return RestaurantOwner.fromDocument(userDoc);
//            case User.DRIVER:
//                return Driver.fromDocument(userDoc);
//        }

        return null;
    }
}
