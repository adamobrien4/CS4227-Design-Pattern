package main.presentation_layer.create_restaurant;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.data_layer.DatabaseRepository;
import main.entities.Menu;
import main.entities.Restaurant;
import main.entities.RestaurantOwner;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.util.ArrayList;

import javafx.stage.Stage;

public class CreateRestaurantController {

    @FXML
    public TextField usernameRField;
    public Button backBtn;
    public Button createRestaurantBtn;
    public TextField passwordRField;
    public TextField confirmPasswordRField;
    public TextField restaurantNameTf;
    public TextField restaurantGenreTf;
    public Text messageTxt;


    public void handleBackBtn(ActionEvent event) throws IOException {
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

        ArrayList<String> listTxtFields = new ArrayList<>();
        listTxtFields.add(email);
        listTxtFields.add(password);
        listTxtFields.add(confirm_password);
        listTxtFields.add(restaurantName);
        listTxtFields.add(restaurantGenre);


        if(validateFields(listTxtFields) ) {
            // Restaurant account and restaurant itself will have same id
            ObjectId restaurantObjectID = new ObjectId();
            RestaurantOwner restaurantOwner = new RestaurantOwner(restaurantObjectID, restaurantObjectID, email, password);
            DatabaseRepository.setup();
            DatabaseRepository dr = new DatabaseRepository();
            // Adds the restaurant owner's account to the DB
            dr.createRestaurantAccount(restaurantOwner);

            // a default pre populated menu
            Menu sampleMenu = new Menu();
            Restaurant restaurant = new Restaurant(restaurantObjectID, restaurantName, sampleMenu, restaurantGenre);

            // Adds the restaurant Object to the DB

            dr.createRestaurant(restaurant, restaurantObjectID);
            messageTxt.setText("A restaurant has been created");
            messageTxt.setVisible(true);
        }
        else {
            messageTxt.setText("Error: Re enter details");
            messageTxt.setVisible(true);
        }



    }

    private boolean validateFields(ArrayList<String> listTxtFields) {
        boolean result = true;
        for(String txt: listTxtFields) {
            if (txt.length() < 1)
                result = false;
        }
        return result;
    }

    @FXML
    public void initialize() {
        // Initialise the controller
        System.out.println("Initialise");
    }

}
