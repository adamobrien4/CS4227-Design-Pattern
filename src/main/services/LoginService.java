package main.services;

import main.dao.CustomerDaoImpl;
import org.bson.Document;

import main.Globals;
import main.data_layer.DatabaseRepository;
import main.entities.users.User;
import main.factories.UserFactory;
import main.utils.PasswordUtils;

public class LoginService {
    UserFactory userFactory;

    public LoginService(DatabaseRepository dbRepo) {
//        this.dbRepo = dbRepo;
        this.userFactory = new UserFactory();
    }

    public boolean verifyLogin(String email, String password) {

        // Get user details from DB to see if the user exists
        // If the user exists assign it to Globals.loggedInUser

        
//        String ePwd = PasswordUtils.encryptPassword(password);
//        System.out.println(ePwd);
//
//        Customer customer = CustomerDaoImpl
//
//        if(userDoc == null) {
//            System.out.println("User is null");
//            return false;
//        } else {
//            System.out.println(userDoc);
//        }
//
//        User usr = userFactory.createUser(userDoc);
//
//        System.out.println(usr);
//        Globals.setLoggedInUser(usr);

        return true;
    }
}