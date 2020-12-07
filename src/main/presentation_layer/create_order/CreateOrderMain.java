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

        db = new DatabaseRepository();

        System.out.println(getClass().getResource("CreateOrder.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("CreateOrder.fxml"));

        Customer u = new Customer(new ObjectId(), "adam@gmail.com", "paswd");
        Globals.setLoggedInUser(u);

        Document restDoc = db.getDB().getCollection("restaurants").find().first();
        Restaurant.fromDocument(restDoc);

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 1000, 800));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}