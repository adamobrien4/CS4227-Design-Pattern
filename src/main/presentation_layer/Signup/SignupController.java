package main.presentation_layer.signup;

import main.presentation_layer.presentation.*;
import main.entities.users.Customer;
import main.services.SignupService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.utils.PasswordUtils;

public class SignupController {
    @FXML
    public Button createRestaurantBtn;
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

    SignupService signupService;
    UseRemote ur;
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

        event.consume();

        Label msg = FXsignupmessageField;
        msg.setTextFill(Color.RED);
        msg.setBackground(new Background(
            new BackgroundFill(Color.WHITE, new CornerRadii(5.0), new Insets(-5.0))));

        // Signup Form Validation
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
            msg.setText("Confirm Password is empty");
            return;
        } else if(!password.equals(confirmPassword)) {
            msg.setText("Password Mismatch");
            return;
        }

        boolean signupSuccess = false;

        try{
            signupSuccess = signupService.signupUser(new Customer(email, PasswordUtils.encryptPassword(password), address));
        } catch (Exception ex) {
            ex.printStackTrace();
            msg.setText(ex.getMessage());
            return;
        }

        System.out.println("This Ran after Signup.success results are:\t" + signupSuccess);

        if (signupSuccess) {
            try {
                UseRemote.login();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            msg.setText("Error signing up user");
        }
    }

    public void handleCreateRestaurant(ActionEvent event) {
        Button btn = (Button) event.getSource();
        Scene scene = btn.getScene();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../create_restaurant/CreateRestaurant.fxml"));
            Stage stage = (Stage) scene.getWindow();
            Scene scene2 = new Scene(root);
            stage.setScene(scene2);
        } catch (IOException io) {
            System.out.println("Error loading Checkout");
            System.out.println(io.toString());
        }
        event.consume();


    }
    public void handleAge(ActionEvent event){
        System.out.println("I ran");
        //double age =ageslider.getValue();
        //AgeText.setText("Age: "+age);
       // event.consume();

    }

    @FXML
    public void initialize() {
        // Initialise the controller
        System.out.println("Initialise");
        signupService = new SignupService();
    }

}
