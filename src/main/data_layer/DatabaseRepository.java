package main.data_layer;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.ConnectionString;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;

import main.entities.*;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class DatabaseRepository {

    private static MongoClient mongoClient;
    private static MongoDatabase db;
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

        ConnectionString connectionString = new ConnectionString("mongodb+srv://cs4125_user:P3anutButt3r@sandbox.51cvt.mongodb.net/cs4125?retryWrites=true&w=majority");
        CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
        CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
        MongoClientSettings clientSettings = MongoClientSettings.builder().applyConnectionString(connectionString).codecRegistry(codecRegistry).build();


        try {
            mongoClient = MongoClients.create(clientSettings);
            db = mongoClient.getDatabase("cs4125");
        } catch (Exception e) {
            System.out.println("Unable to connect to MongoDB");
            System.out.println(e.toString());
            System.exit(1);
        }
    }

    public static MongoDatabase getDB() {
        return db;
    }

    public static MongoCollection getCollection(String collectionName, Class c) {
        return db.getCollection(collectionName, c);
    }

    public Document getUserByEmailAndPwd(String email, String password) {

        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("email", email);
        whereQuery.put("password", password);
        System.out.println("here is the email and password being queried\t" + email+password);
        System.out.println("this is what get userByEmailAndPWD is returning\t" + db.getCollection("users").find(whereQuery).first());

        return db.getCollection("users").find(whereQuery).first();
    }

    public void insertOrder(Order order) {
        System.out.println("Inserting Order");

        db.getCollection("orders").insertOne(order.toDocument());
        System.out.println("Inserted Order Document");
    }

    public Discount verifyDiscountCode(String code) {
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("code", code);

        Document doc = db.getCollection("discounts").find(whereQuery).first();

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
        MongoCollection<Document> collection = db.getCollection("orders");
        whereQuery.put("status", x);
        FindIterable<Document> cursor = collection.find(whereQuery);
        return cursor;
        
    }
       public static Document getCust(ObjectId x) {
        BasicDBObject whereQuery = new BasicDBObject();
        MongoCollection<Document> collection = db.getCollection("users");
        whereQuery.put("_id", x);
        Document cursor = collection.find(whereQuery).first();
        return cursor;

    }
    public Document getRest(ObjectId x) {
        BasicDBObject whereQuery = new BasicDBObject();
        MongoCollection<Document> collection = db.getCollection("restaurants");
        whereQuery.put("_id", x);
        Document cursor = collection.find(whereQuery).first();
        return cursor;

    }

    public void completeOrder(ObjectId x){
        BasicDBObject whereQuery =new BasicDBObject();
        MongoCollection <Document> collection= db.getCollection("orders");
        whereQuery.put("_id",x);
        collection.updateOne(whereQuery, Updates.set("status","completed"));

    }
    
    public void acceptOrder(ObjectId y) {
        BasicDBObject whereQuery =new BasicDBObject();
        MongoCollection <Document> collection= db.getCollection("orders");
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
        MongoCollection<Document> restaurantCollection = db.getCollection("restaurants");

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
        MongoCollection<Document> usersCollection = db.getCollection("users");

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
