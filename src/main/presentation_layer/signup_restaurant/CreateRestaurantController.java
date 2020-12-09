package main.presentation_layer.signup_restaurant;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.data_layer.DatabaseRepository;
import main.entities.Menu;
import main.entities.Restaurant;
import main.entities.RestaurantOwner;
import org.bson.types.ObjectId;

import java.io.IOException;

public class CreateRestaurantController {

    @FXML
    public TextField usernameRField;
    public Button alreadyauserRButton;
    public Button signupRButton;
    public TextField passwordRField;
    public TextField confirmPasswordRField;
    public TextField restaurantNameTf;
    public TextField restaurantGenreTf;


    public void handleAlreadyaUser(ActionEvent event) throws IOException {
        System.out.println("Button pressed");
        Button btn = (Button) event.getSource();
        Scene scene = btn.getScene();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../login/LoginFX.fxml"));
            Stage stage = (Stage) scene.getWindow();
            Scene scene2 = new Scene(root);
            stage.setScene(scene2);
        } catch (IOException io) {
            System.out.println("Error loading Checkout");
            System.out.println(io.toString());
        }
        event.consume();
    }

    public void handleCreateRestaurant(ActionEvent event)throws IOException {
        String email = usernameRField.getText();
        String password = passwordRField.getText();
        String confirm_password = confirmPasswordRField.getText();
        String restaurantName = restaurantNameTf.getText();
        String restaurantGenre = restaurantGenreTf.getText();

        if(password.equals(confirm_password)== false){
            System.out.println("mismatch password");
        }

        // Restaurant account and restaurant itself will have same id
        ObjectId restaurantObjectID = new ObjectId();
        RestaurantOwner restaurantOwner = new RestaurantOwner(restaurantObjectID,restaurantObjectID,email,password);
        DatabaseRepository.setup();
        DatabaseRepository dr = new DatabaseRepository();
        // Adds the restaurant owner's account to the DB
        dr.createRestaurantAccount(restaurantOwner);

        // a default pre populated menu
        Menu sampleMenu = new Menu();
        Restaurant restaurant = new Restaurant(restaurantObjectID,restaurantName,sampleMenu,restaurantGenre);

        // Adds the restaurant Object to the DB
        dr.createRestaurant(restaurant,restaurantObjectID);

    }

    @FXML
    public void initialize() {
        // Initialise the controller
        System.out.println("Initialise");
    }

}
