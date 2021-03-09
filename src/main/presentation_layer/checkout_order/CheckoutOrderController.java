package main.presentation_layer.checkout_order;

import java.io.IOException;

import com.mongodb.BasicDBObject;

import org.bson.Document;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import main.Globals;
import main.data_layer.DatabaseRepository;
import main.entities.Customer;
import main.presentation_layer.Presentation.*;

public class CheckoutOrderController {
    
    @FXML
    private TextField checkout_card_number;
    @FXML
    private TextField card_expiry;
    @FXML
    private TextField checkout_card_owner;
    @FXML
    private TextField checkout_cvv;
    @FXML
    private void handlePayNow(ActionEvent evt) {
        System.out.println("Checking out");

        if (checkout_card_number.getText().length() == 0) {
            new Alert(Alert.AlertType.WARNING, "Please enter a CARD NUMBER").showAndWait();
            return;
        }

        if (card_expiry.getText().length() == 0) {
            new Alert(Alert.AlertType.WARNING, "Please enter a CARD EXPIRY").showAndWait();
            return;
        }

        if (checkout_card_owner.getText().length() == 0) {
            new Alert(Alert.AlertType.WARNING, "Please enter a CARD OWNER").showAndWait();
            return;
        }

        if (checkout_cvv.getText().length() == 0) {
            new Alert(Alert.AlertType.WARNING, "Please enter a CHECKOUT CVV").showAndWait();
            return;
        }

        new Alert(Alert.AlertType.INFORMATION, "Your Order has been placed").showAndWait();

        Customer c = (Customer)Globals.getLoggedInUser();
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("customer_id", c.getId());
        whereQuery.put("status", "pending_payment");

        Document query = new Document();
        query.append("customer_id", c.getId());
        query.append("status", "pending_payment");

        Document setData = new Document();
        setData.append("status", "pending");

        Document update = new Document();
        update.append("$set", setData);
        
        //To update single Document  
        DatabaseRepository.getDB().getCollection("orders").updateOne(query, update);

        try {
            UseRemote.browserestaurants();
        } catch (IOException e) {
            e.printStackTrace();
        }

        evt.consume();
    }

    @FXML
    public void initialize() {
        System.out.println("Initialising Checkout Screen");
    }

}
