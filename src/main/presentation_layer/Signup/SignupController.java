package main.presentation_layer.signup;


import main.entities.FoodItem;
import main.entities.Order;
import main.utils.PasswordUtils;
import main.data_layer.DatabaseRepository;
import main.entities.BasketItem;
import main.entities.Customer;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.ResourceBundle;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
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
import javafx.scene.control.PasswordField;
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

    private boolean createAccount;

    DatabaseRepository db;


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
        System.out.println("Button pressed");
        createAccount=false;
        String email = FXusernameField.getText();
        String address = FXAddressField.getText();
        String password = FXpasswordField.getText();
        String confirm_password = FXconfirmpasswordField.getText();
        BasicDBObject whereQuery = new BasicDBObject();


        if(password.equals(confirm_password)== false){
            System.out.println("mismatch password");
            createAccount=false;
        }
        else{
             password = PasswordUtils.encryptPassword(password);

        }
        Customer c = new Customer(email, password);
        //db.getDB().getCollection("users").insertOne(c.toDocument());
        System.out.println("Result for first test   " +db.getDB().getCollection("users").find(new Document(whereQuery)));

        System.out.println("Result for empty document   "+db.getDB().getCollection("users").find(whereQuery).first());


    }
        @FXML
        public void initialize() {
            // Initialise the controller
            System.out.println("Initialise");

            db = new DatabaseRepository();
        }
    
    }
    






