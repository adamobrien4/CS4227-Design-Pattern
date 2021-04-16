package main.presentation_layer.checkout_order;

import java.io.IOException;

import main.dao.OrderDaoImpl;
import main.framework.Framework;
import main.framework.contexts.Context;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import main.presentation_layer.presentation.*;

public class CheckoutOrderController {
    
    @FXML
    private TextField checkout_card_number;
    @FXML
    private TextField card_expiry;
    @FXML
    private TextField checkout_card_owner;
    @FXML
    private TextField checkout_cvv;

    private OrderDaoImpl orderDao;

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


        // TODO: Update checked out order
        if( orderDao.payForOrder() ){
            Framework.getInstance().onLogEvent(new Context(String.format("Order has Been paid for with card:\n Card Number: %s\nCard Expiry: %s\nCard Name: %s\nCVV: %s",checkout_card_number.getText(),card_expiry.getText(),checkout_card_owner.getText(),checkout_cvv.getText())));
            new Alert(Alert.AlertType.INFORMATION, "Your Order has been placed").showAndWait();
        }

        try {
            UseRemote.browseRestaurants();
        } catch (IOException e) {
            e.printStackTrace();
        }

        evt.consume();
    }

    @FXML
    public void initialize() {
        System.out.println("Initialising Checkout Screen");
        orderDao = new OrderDaoImpl();
    }

}
