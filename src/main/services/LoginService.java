package main.services;

import org.bson.Document;

import main.Globals;
import main.data_layer.DatabaseRepository;
import main.entities.User;
import main.factories.UserFactory;
import main.utils.PasswordUtils;

public class LoginService {
    DatabaseRepository dbRepo;
    UserFactory userFactory;

    public LoginService(DatabaseRepository dbRepo) {
        this.dbRepo = dbRepo;
        this.userFactory = new UserFactory();
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

        User usr = userFactory.createUser(userDoc);
        
        System.out.println(usr);
        Globals.setLoggedInUser(usr);

        return true;
    }
}