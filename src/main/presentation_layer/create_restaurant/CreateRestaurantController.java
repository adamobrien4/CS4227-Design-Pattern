package main.presentation_layer.create_restaurant;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.entities.Businesses.LocationTypes.*;
import main.entities.Businesses.Owners.*;
import main.entities.Menu;
import main.entities.users.RestaurantOwner;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.util.ArrayList;

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
    public ComboBox<String> LocationType;
    public ComboBox<String> ownertype;

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
        Scene scene = ((Node) event.getSource()).getScene();
        String email = usernameRField.getText();
        String password = passwordRField.getText();
        String confirm_password = confirmPasswordRField.getText();
        String restaurantName = restaurantNameTf.getText();
        String restaurantGenre = restaurantGenreTf.getText();
        String locationString = LocationType.getValue();
        String ownerString = ownertype.getValue();
        Location location =null;
        ArrayList<String> listTxtFields = new ArrayList<>();
        listTxtFields.add(email);
        listTxtFields.add(password);
        listTxtFields.add(confirm_password);
        listTxtFields.add(restaurantName);
        listTxtFields.add(restaurantGenre);



        if(validateFields(listTxtFields) ) {
            // Restaurant account and restaurant itself will have same id
            ObjectId restaurantObjectID = new ObjectId();
            if (locationString.equals("Family Friendly")){
                location = new FamilyFriendly(restaurantObjectID, restaurantName, restaurantGenre, new ObjectId());
            }
            else if(locationString.equals("Over 18's")){
                location = new Over18s(restaurantObjectID, restaurantName, restaurantGenre, new ObjectId());
            } 
            else if(locationString.equals("Verified Only")){
                location = new VerifiedOnly(restaurantObjectID, restaurantName, restaurantGenre, new ObjectId());
            }
            else{
                event.consume();
            }

            if(ownerString.equals("Store Owner")){
                Owner restaurantOwner = new StoreOwner( location, restaurantObjectID, restaurantObjectID, email, password);
            }
            else if (ownerString.equals("Venue Owner")){
                Owner restaurantOwner = new VenueOwner( location, restaurantObjectID, restaurantObjectID, email, password);
            }
            else{
                event.consume();
            }
            // TODO: Add restaurant to Database
//            DatabaseRepository dr = new DatabaseRepository();
//            // Adds the restaurant owner's account to the DB
//            dr.createRestaurantAccount(restaurantOwner);

            // a default pre populated menu
            // TODO: Implement new Menu for the created restaurant - do it in API instead of here
            // Menu sampleMenu = new Menu();

                // Adds the restaurant Object to the DB

            // dr.createRestaurant(restaurant, restaurantObjectID);
            try {
                Parent root = FXMLLoader.load(getClass().getResource("../create_menu_list/create_menu_list.fxml"));
                Stage stage = (Stage) scene.getWindow();
                Scene scene2 = new Scene(root);
                stage.setScene(scene2);
            } catch (IOException io) {
                System.out.println("Error loading Login");
                System.out.println(io.toString());
            }
            event.consume();

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
        LocationType.getItems().addAll(
            "Family Friendly",
            "Over 18's",
            "Verified Only"
        );
        ownertype.getItems().addAll(
            "Store Owner",
            "Venue Owner"
        );

    }

}
