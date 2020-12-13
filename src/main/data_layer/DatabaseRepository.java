package main.data_layer;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;

import main.entities.*;
import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class DatabaseRepository {

    private static MongoClient mongoClient;
    private static MongoDatabase database;
    private static boolean isSetup = false;

    public DatabaseRepository() {
        if (isSetup) {
            System.out.println("Database is setup");
        } else {
            System.out.println("Database is NOT setup");
        }
    }

    public static void setup() {
        if (isSetup) {
            return;
        }

        System.out.println("Setting up DB");

        MongoClientURI uri = new MongoClientURI(
                "mongodb+srv://cs4125_user:P3anutButt3r@sandbox.51cvt.mongodb.net/cs4125?retryWrites=true&w=majority");

        try {
            mongoClient = new MongoClient(uri);
            database = mongoClient.getDatabase("cs4125");
            isSetup = true;
        } catch (Exception e) {
            System.out.println("Unable to connect to MongoDB");
            System.exit(0);
        }

    }

    public static MongoDatabase getDB() {
        return database;
    }

    public Document getUserByEmailAndPwd(String email, String password) {

        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("email", email);
        whereQuery.put("password", password);
        System.out.println("here is the email and password being queried\t" + email+password);
        System.out.println("this is what get userByEmailAndPWD is returning\t" + database.getCollection("users").find(whereQuery).first());

        return database.getCollection("users").find(whereQuery).first();
    }

    public void insertOrder(Order order) {
        System.out.println("Inserting Order");

        database.getCollection("orders").insertOne(order.toDocument());
        System.out.println("Inserted Order Docment");
    }

    public Discount verifyDiscountCode(String code) {
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("code", code);

        Document doc = database.getCollection("discounts").find(whereQuery).first();

        if (doc == null) {
            return null;
        }

        return Discount.fromDocument(doc);
    }

    public List<Document> createListFoodItemDocuments( ArrayList<FoodItem> listOfFoodItems) {
        List<Document> listFoodItemDocuments = new ArrayList<>();
        for (FoodItem item : listOfFoodItems) {

            String[] arrAllergens = item.getAllergens();

            listFoodItemDocuments.add(new Document().append("name", item.getName())
                    .append("allergens", asList(arrAllergens)).append("price", item.getPrice()));
        }

        return listFoodItemDocuments;
    }

    
    public FindIterable<Document> getOrders(String x) {
        BasicDBObject whereQuery = new BasicDBObject();
        MongoCollection<Document> collection = database.getCollection("orders");
        whereQuery.put("status", x);
        FindIterable<Document> cursor = collection.find(whereQuery);
        return cursor;
        
    }

    public Document getRest(ObjectId x) {
        BasicDBObject whereQuery = new BasicDBObject();
        MongoCollection<Document> collection = database.getCollection("restaurants");
        whereQuery.put("_id", x);
        Document cursor = collection.find(whereQuery).first();
        return cursor;

    }

    public void completeOrder(ObjectId x){
        BasicDBObject whereQuery =new BasicDBObject();
        MongoCollection <Document> collection= database.getCollection("orders");
        whereQuery.put("_id",x);
        collection.updateOne(whereQuery, Updates.set("status","completed"));

    }
    
    public void acceptOrder(ObjectId y) {
        BasicDBObject whereQuery =new BasicDBObject();
        MongoCollection <Document> collection= database.getCollection("orders");
        whereQuery.put("_id",y);
        collection.updateOne(whereQuery, Updates.set("status","pending"));
	}


    public Document createMenuDocument(Menu menu) {
        ArrayList<FoodItem> listOfMainCoursesItems = menu.getListOfMainCoursesItems();
        ArrayList<FoodItem> listOfDesertItemsItems = menu.getListOfDessertItems();
        ArrayList<FoodItem> listOfSideItems = menu.getListOfSideItems();
        ArrayList<FoodItem> listOfDrinkItems = menu.getListOfDrinksItems();

        List<Document> mainCourseItemsDocuments = createListFoodItemDocuments(listOfMainCoursesItems);
        List<Document> desertItemsDocuments = createListFoodItemDocuments(listOfDesertItemsItems);
        List<Document> sideItemsDocuments = createListFoodItemDocuments(listOfSideItems);
        List<Document> drinkItemsDocuments = createListFoodItemDocuments(listOfDrinkItems);

        Document menuDocument = new Document();
        menuDocument.append("name",menu.getMenuName());
        menuDocument.append("main course",mainCourseItemsDocuments);
        menuDocument.append("desert",desertItemsDocuments);
        menuDocument.append("sides",sideItemsDocuments);
        menuDocument.append("drinks",drinkItemsDocuments);

        return menuDocument;
    }

    public void createRestaurant(Restaurant restaurant, ObjectId id) {
        MongoCollection<Document> restaurantCollection = database.getCollection("restaurants");

        Menu menu = restaurant.getMenu();
        Document menuDocument = createMenuDocument(menu);

        Document restaurantDocument= new Document("_id", id);
        restaurantDocument.append("name", restaurant.getName())
                .append("genre",restaurant.getGenre())
                .append("menu", menuDocument);

        restaurantCollection.insertOne(restaurantDocument);
        System.out.println("Added restaurant to the DB");
    }

    public void createRestaurantAccount(RestaurantOwner restaurantOwner) {
        MongoCollection<Document> usersCollection = database.getCollection("users");

        Document userDocument = new Document();
        userDocument.append("email",restaurantOwner.getEmail())
                .append("password",restaurantOwner.getPassword())
                .append("type","owner");

        usersCollection.insertOne(userDocument);

        System.out.println("account has been created");
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
