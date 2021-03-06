package main.services;

import main.dao.UserDaoImpl;
import main.Globals;
import main.entities.users.User;

public class LoginService {
    UserDaoImpl userDaoImpl;

    public LoginService() {
        this.userDaoImpl = new UserDaoImpl();
    }

    public boolean verifyLogin(String email, String password) {

        // Get user details from DB to see if the user exists
        // If the user exists assign it to Globals.loggedInUser

        User user = userDaoImpl.getByEmailAndPassword(email, password);

        if(user != null) {
            // Found user in database
            Globals.setLoggedInUser(user);
            return true;
        }
        return false;
    }
}