package main.data_layer;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

import main.entities.Order;

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

    public void insertOrder(Order order) {
        System.out.println("Inserting Order");

        // TODO : Insert order into DB
        database.getCollection("orders").insertOne(order.toDocument());
        System.out.println("Inserted Order Docment");
    }

    public void test() {
        // BasicDBObject whereQuery = new BasicDBObject();
        // whereQuery.put("name", "adam");

        Document menu = database.getCollection("menu").find().first();

        System.out.println(menu.get("menu"));
    }

    public boolean close() {
        try {
            mongoClient.close();
            return true;
        } catch (Exception e) {
            System.out.println("Problem closing DatabaseRepository: " + e.toString());
        }
        return false;
    }
}
