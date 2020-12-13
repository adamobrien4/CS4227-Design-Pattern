package main.services;

import org.bson.Document;

import main.data_layer.DatabaseRepository;
import main.utils.PasswordUtils;

public class SignupService {

    public SignupService() {
        System.out.println("Created new Signup Service");
        DatabaseRepository db;
    }

    public boolean signupUser(DatabaseRepository db, String email, String password, String type) {

        password = PasswordUtils.encryptPassword(password);
        System.out.println("This Ran BEFORE USER DOC     results are:\t");

        
      

        //Document userDoc = new Document("email", email).append("password", password).append("type", type);
        Document userDoc = new Document();
        userDoc.append("email", email)
        .append("password", password)
        .append("type", type);
        
    


        System.out.println("I have recived the document");

        try {
            db.getDB().getCollection("users").insertOne(userDoc);
        } catch (Exception e) {
            return false;
        }
        return true; 

    }

}
