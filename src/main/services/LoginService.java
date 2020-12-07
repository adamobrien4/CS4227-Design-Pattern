package main.services;

import com.mongodb.DBRef;

import org.bson.Document;
import org.bson.types.ObjectId;

import main.Globals;
import main.data_layer.DatabaseRepository;
import main.entities.Customer;
import main.entities.User;
import main.utils.PasswordUtils;

public class LoginService {
    private DatabaseRepository dbRepo;

    public LoginService() {
        dbRepo = new DatabaseRepository();
    }

    public boolean verifyLogin(String email, String password) {

        // Get user details from DB to see if the user exists
        // If the user exists assign it to Globals.loggedInUser

        String ePwd = PasswordUtils.encryptPassword(password);
        System.out.println(ePwd);

        Document userDoc = dbRepo.getUserByEmailAndPwd(email, ePwd);

        if(userDoc == null) {
            System.out.println("User is null");
            return false;
        } else {
            System.out.println(userDoc);
        }

        Customer c = Customer.fromDocument(userDoc);
        System.out.println(c);
        Globals.setLoggedInUser(c);

        return true;
    }
}