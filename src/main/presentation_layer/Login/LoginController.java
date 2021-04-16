package main.presentation_layer.login;

import main.services.LoginService;
import main.presentation_layer.presentation.*;

import main.Globals;
import main.entities.users.User;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

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

        // Attempt to login user
        if (!loginService.verifyLogin(email, password)) {
            FXmessageField.setTextFill(Color.RED);
            FXmessageField.setBackground(
                    new Background(new BackgroundFill(Color.WHITE, new CornerRadii(5.0), new Insets(-5.0))));
            FXmessageField.setText("Invalid login details");
            return;
        }

        System.out.println("User is loggged in");

        switch (Globals.getLoggedInUser().getType()) {
            case User.CUSTOMER:
                try {
                    UseRemote.browseRestaurants();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case User.DRIVER:
                try {
                    UseRemote.driver();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
            case User.RESTAURANT_OWNER:
                try {
                    UseRemote.createMenu();
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
        loginService = new LoginService();
    }

}
