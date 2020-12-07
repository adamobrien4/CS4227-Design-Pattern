package main.presentation_layer.browse_restaurants;

import org.bson.types.ObjectId;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.Globals;
import main.entities.Customer;
import main.services.LoginService;

public class BrowseRestaurantMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        System.out.println(getClass().getResource("BrowseRestaurant.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("BrowseRestaurant.fxml"));

        LoginService ls = new LoginService();

        ls.verifyLogin("adam@gmail.com", "my_password");

        Customer u = new Customer(new ObjectId(), "adam@gmail.com", "paswd");
        Globals.setLoggedInUser(u);

        /*primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();*/
    }


    public static void main(String[] args) {
        launch(args);
    }
}