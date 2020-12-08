package main.presentation_layer.create_order;

import org.bson.Document;
import org.bson.types.ObjectId;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.Globals;
import main.data_layer.DatabaseRepository;
import main.entities.Customer;
import main.entities.Restaurant;

public class CreateOrderMain extends Application {

    DatabaseRepository db;

    @Override
    public void start(Stage primaryStage) throws Exception{

        DatabaseRepository.setup();

        db = new DatabaseRepository();

        System.out.println("Getting restaurant doc from db");
        Document restDoc = db.getDB().getCollection("restaurants").find().first();
        System.out.println("Got restaurant doc from db");
        System.out.println(restDoc);
        Globals.setRestaurant(Restaurant.fromDocument(restDoc));

        Customer u = new Customer(new ObjectId(), "adam@gmail.com", "paswd");
        Globals.setLoggedInUser(u);

        System.out.println(getClass().getResource("CreateOrder.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("CreateOrder.fxml"));

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 1000, 800));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}