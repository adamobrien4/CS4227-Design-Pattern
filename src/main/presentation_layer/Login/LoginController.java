package main.presentation_layer.login;

import java.io.IOException;
import java.util.*;
import java.util.ResourceBundle;

import org.bson.Document;

import main.data_layer.DatabaseRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class LoginController {
    @FXML
    private TextField FXusernameField;
    @FXML
    private TextField FXpasswordField;
    @FXML
    private Button FXloginButton;
    @FXML
    private Button FXsignuphereButton;
    @FXML
    private AnchorPane FXsignupPane;
    private ResourceBundle resources;
    
    DatabaseRepository db;

    @FXML
    // Handles login
    public void handleLogin(ActionEvent event) {
        if (FXusernameField.getText().equals("test") && FXpasswordField.getText().equals("test")) {
            System.out.println("Success");
        } else {
            System.out.println("fail");

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
    }

}
