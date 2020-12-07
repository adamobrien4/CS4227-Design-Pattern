package main.presentation_layer.signup;


import main.entities.FoodItem;
import main.entities.Order;
import main.data_layer.DatabaseRepository;
import main.entities.BasketItem;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.ResourceBundle;

import org.bson.Document;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
public class SignupController {
    @FXML
    private Button fXsignupButton;
      
    @FXML
    private TextField FXusernameField;
    @FXML
    private TextField FXpasswordField;
    @FXML
    private TextField FXAddressField;
    @FXML
    private TextField FXconfirmpasswordField;
    @FXML
    private Button FXalreadyauserButton;
    @FXML
    private URL location;
    @FXML
    private ResourceBundle resources;

    private boolean createAccount;


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
    public void handleCreateanAccount(ActionEvent event)throws IOException {
        createAccount=false;
        String username = FXusernameField.getText();
        String address = FXAddressField.getText();
        String password = FXpasswordField.getText();
        String confirm_password = FXconfirmpasswordField.getText();

        if(password.equals(confirm_password)== false){
            System.out.println("mismatch password");
            

        }

    }

        @FXML
        public void initialize() {
            // Initialise the controller
            System.out.println("Initialise");
        }
    
    }
    






