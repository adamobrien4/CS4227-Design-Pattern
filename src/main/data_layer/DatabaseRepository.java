package main.data_layer;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class DatabaseRepository {

    MongoClient mongoClient;
    MongoDatabase database;

    public DatabaseRepository() {
        MongoClientURI uri = new MongoClientURI("mongodb+srv://cs4125_user:P3anutButt3r@sandbox.51cvt.mongodb.net/cs4125?retryWrites=true&w=majority");

        try {
            mongoClient = new MongoClient(uri);
            database = mongoClient.getDatabase("cs4125");
        } catch (Exception e) {
            System.out.println("Unable to connect to MongoDB");
            System.exit(0);
        }
        
    }

    public Document getUserByName(String username) {

        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("name", username);

        return database.getCollection("users").find(whereQuery).first();
    }
}
