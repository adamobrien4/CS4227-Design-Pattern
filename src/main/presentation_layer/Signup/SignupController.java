package main.presentation_layer.signup;

import main.data_layer.DatabaseRepository;
import main.entities.User;
import main.presentation_layer.PresentationLoader;
import main.services.SignupService;
import main.data_layer.*;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.ResourceBundle;

import com.mongodb.BasicDBObject;
import org.bson.Document;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class SignupController {
    @FXML
    private Button fXsignupButton;

    @FXML
    private TextField FXusernameField;
    @FXML
    private PasswordField FXpasswordField;
    @FXML
    private TextField FXAddressField;
    @FXML
    private PasswordField FXconfirmpasswordField;
    @FXML
    private Button FXalreadyauserButton;
    @FXML
    private URL location;
    @FXML
    private ResourceBundle resources;
    @FXML
    private Label FXsignupmessageField;

    DatabaseRepository db;
    SignupService signupService;

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

    public void handleCreateanAccount(ActionEvent event) throws IOException {
        System.out.println("Button pressed");
        String email = FXusernameField.getText();
        String password = FXpasswordField.getText();
        String address = FXAddressField.getText();
        String confirmPassword = FXconfirmpasswordField.getText();

        Label msg = (Label)FXsignupmessageField;
        msg.setTextFill(Color.RED);
        
        //BasicDBObject whereQuery = new BasicDBObject()
        var whereQuery = new BasicDBObject();
        whereQuery.put("email", email);

        Document d = DatabaseRepository.getDB().getCollection("users").find(whereQuery).first();


        if (email.isEmpty()){
            msg.setText("Email is empty");
            return;
        } else if(address.isEmpty()) {
            msg.setText("Address is empty");
            return;
        } else if(password.isEmpty()) {
            msg.setText("Password is empty");
            return;
        } else if(confirmPassword.isEmpty()) {
            msg.setText("Confrirm Password is empty");
            return;
        } else if(!password.equals(confirmPassword)) {
            msg.setText("Password Mismatch");
            return;
        } else if(d != null) {
            msg.setText("That email is already in use");
            return;
        }

        boolean signupSuccess = signupService.signupUser(db, email, password, User.CUSTOMER);

        if (signupSuccess) {
            PresentationLoader.getInstance().display(PresentationLoader.LOGIN);
        } else {
            msg.setText("Error signing up user");
        }
    }

    @FXML
    public void initialize() {
        // Initialise the controller
        System.out.println("Initialise");

        db = new DatabaseRepository();
        signupService = new SignupService();
    }

}
