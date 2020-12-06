package main.services;

import org.bson.types.ObjectId;

import main.Globals;
import main.data_layer.DatabaseRepository;
import main.entities.Customer;

public class LoginService {
    DatabaseRepository dbRepo;

    public static boolean verifyLogin(String email, String password) {

        // Get user details from DB to see if the user exists
        // If the user exists assign it to Globals.loggedInUser

        Customer c = new Customer(new ObjectId("123"), email);
        Globals.setLoggedInUser(c);

        return false;
    }
}