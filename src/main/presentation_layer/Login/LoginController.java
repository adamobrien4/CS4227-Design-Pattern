package main.presentation_layer.login;

import main.data_layer.*;
import main.services.LoginService;

import com.mongodb.DBRef;

import org.bson.Document;
import org.bson.types.ObjectId;

import main.Globals;
import main.data_layer.DatabaseRepository;
import main.entities.Customer;
import main.entities.Driver;
import main.entities.RestaurantOwner;
import main.entities.User;
import main.utils.PasswordUtils;

import java.io.IOException;
import java.util.ResourceBundle;

import main.data_layer.DatabaseRepository;

import main.utils.PasswordUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class LoginController {
    @FXML
    private TextField FXusernameField;
    @FXML
    private PasswordField FXpasswordField;
    @FXML
    private Button FXloginButton;
    @FXML
    private Button FXsignuphereButton;
    @FXML
    private AnchorPane FXsignupPane;
    private ResourceBundle resources;

    DatabaseRepository db;
    LoginService loginService;

    @FXML
    // Handles login
    public void handleLogin(ActionEvent event) {
        System.out.println("Button pressed");
        boolean isloggedin = true;

        isloggedin = loginService.verifyLogin(FXusernameField.getText(),FXpasswordField.getText());

        if (Globals.getLoggedInUser() == null) {
            System.out.println("User is not loggged in");
        } else {
            System.out.println("User is loggged in");

        }

    }

    public void handleSignup(ActionEvent event) throws IOException {
        System.out.println("Button pressed");

        Button btn = (Button) event.getSource();
        Scene scene = btn.getScene();

        try {
            Parent root = FXMLLoader.load(getClass().getResource("../signup/SignupFX.fxml"));
            Stage stage = (Stage) scene.getWindow();
            Scene scene2 = new Scene(root);
            stage.setScene(scene2);
        } catch (IOException io) {
            System.out.println("Error loading Checkout");
            System.out.println(io.toString());
        }

        event.consume();

    }

    @FXML
    public void initialize() {
        // Initialise the controller
        System.out.println("Initialise");
        loginService = new LoginService();
    }

}
