package main.presentation_layer.create_restaurant;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.Globals;
import main.dao.RestaurantDaoImpl;
import main.dao.RestaurantOwnerDaoImpl;
import main.entities.businessesLocationTypes.*;
import main.entities.businessesLocationTypes.Location;
import main.entities.users.RestaurantOwner;
import main.exceptions.APIException;
import main.presentation_layer.presentation.UseRemote;
import main.utils.PasswordUtils;
import org.bson.types.ObjectId;

import java.io.IOException;

public class CreateRestaurantController {

    @FXML
    private TextField usernameRField;
    @FXML
    private Button backBtn;
    @FXML
    private Button createRestaurantBtn;
    @FXML
    private TextField passwordRField;
    @FXML
    private TextField confirmPasswordRField;
    @FXML
    private TextField restaurantNameTf;
    @FXML
    private TextField restaurantGenreTf;
    @FXML
    private Text messageTxt;
    @FXML
    private ComboBox<String> locationTypeComboBox;
    @FXML
    private Label create_restaurant_error;

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
        String confirmPassword = confirmPasswordRField.getText();
        String restaurantName = restaurantNameTf.getText();
        String restaurantGenre = restaurantGenreTf.getText();
        String locationType = locationTypeComboBox.getValue();

        Location location;

        if(email.isEmpty()) {
            create_restaurant_error.setText("Email cannot be empty");
            return;
        }
        if(password.isEmpty()) {
            create_restaurant_error.setText("Password cannot be empty");
            return;
        }
        if(!password.equals(confirmPassword)){
            create_restaurant_error.setText("Passwords must match");
            return;
        }
        if(restaurantName.isEmpty()) {
            create_restaurant_error.setText("Restaurant name is required");
            return;
        }
        if(restaurantGenre.isEmpty()) {
            create_restaurant_error.setText("Restaurant Genre is required");
            return;
        }
        if(locationType.isEmpty()) {
            create_restaurant_error.setText("Must select a restaurant type");
            return;
        }


        ObjectId restaurantObjectID = new ObjectId();

        switch(locationType) {
            case "Family_Friendly":
                System.out.println("FF");
                location = new FamilyFriendly(restaurantObjectID, restaurantName, restaurantGenre, new ObjectId());
                break;
            case "Over_18":
                System.out.println("O18");
                location = new Over18s(restaurantObjectID, restaurantName, restaurantGenre, new ObjectId());
                break;
            case "Verified_Only":
                System.out.println("VO");
                location = new VerifiedOnly(restaurantObjectID, restaurantName, restaurantGenre, new ObjectId());
                break;
            default:
                System.out.println("No matched location type");
                return;
        }

        RestaurantOwner owner = new RestaurantOwner(new ObjectId(), location.getId(), email, PasswordUtils.encryptPassword(confirmPassword));
        Globals.setLoggedInUser(owner);

        // Add Restaurant owner to database
        try {
            RestaurantOwnerDaoImpl.getInstance().insert(owner);
        } catch (APIException e) {
            e.printStackTrace();
            return;
        }

        // Add restaurant to database
        try {
            RestaurantDaoImpl.getInstance().insert(location);
        } catch (APIException e) {
            e.printStackTrace();
            return;
        }

        // Move to Menu creator screen
        UseRemote.createMenu();
    }

    @FXML
    public void initialize() {

        create_restaurant_error.setText("");

        locationTypeComboBox.getItems().addAll(
            "Family_Friendly",
            "Over_18",
            "Verified_Only"
        );

    }

}
