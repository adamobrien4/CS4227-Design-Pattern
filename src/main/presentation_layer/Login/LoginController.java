package main.presentation_layer.login;

import main.data_layer.*;
import main.services.LoginService;
import main.presentation_layer.Presentation.*;
import main.presentation_layer.browse_restaurants.*;

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
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
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
    @FXML
    private Label FXmessageField;

    DatabaseRepository db;
    LoginService loginService;

    @FXML
    // Handles login
    public void handleLogin(ActionEvent event) {

        System.out.println("Button pressed");
        FXmessageField.setText("");

        String email = FXusernameField.getText();
        String password = FXpasswordField.getText();

        System.out.println("email is " + email);
        System.out.println("password is " + password);

        loginService.verifyLogin(email, password);

        if (Globals.getLoggedInUser() == null) {
            FXmessageField.setTextFill(Color.RED);
            FXmessageField.setBackground(
                    new Background(new BackgroundFill(Color.WHITE, new CornerRadii(5.0), new Insets(-5.0))));
            FXmessageField.setText("Invalid login details");

        } else {

            System.out.println("User is loggged in");

            switch (Globals.getLoggedInUser().getType()) {
                case User.CUSTOMER:
                try {
                    UseRemote.browserestaurants();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                    break;
                case User.DRIVER:
                try {
                    UseRemote.driver();
                } catch (IOException e) {
                    e.printStackTrace();
                };
                    break;
                case User.RESTAURANT_OWNER:
                    break;
                default:
                //login
                try {
                    UseRemote.login();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                    break;
            }
            event.consume();
        }
    }

    public void handleSignup(ActionEvent event) throws IOException {
        System.out.println("Button pressed");

        try {
            UseRemote.signup();
        } catch (IOException e) {
            e.printStackTrace();
        }

        event.consume();

    }

    @FXML
    public void initialize() {
        // Initialise the controller
        System.out.println("Initialise");
        db = new DatabaseRepository();

        loginService = new LoginService(db);
    }

}
