package main.services;

import org.bson.Document;

import main.data_layer.DatabaseRepository;

public class SignupService {

    private SignupService() {
        System.out.println("Created new Signup Service");
    }

    public static boolean signupUser(DatabaseRepository db, String email, String password, String type) {

        Document userDoc = new Document("email", email).append("password", password).append("type", type);

        try {
            db.getDB().getCollection("users").insertOne(userDoc);
        } catch (Exception e) {
            return false;
        }

        return true; 

    }

}
