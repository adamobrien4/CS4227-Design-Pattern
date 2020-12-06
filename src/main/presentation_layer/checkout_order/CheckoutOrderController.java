package main.presentation_layer.checkout_order;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

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

        Alert a = new Alert(Alert.AlertType.INFORMATION, "Your Order has been placed");
        a.showAndWait();
        evt.consume();
    }

    @FXML
    public void initialize() {
        System.out.println("Initialising Checkout Screen");
    }

}
