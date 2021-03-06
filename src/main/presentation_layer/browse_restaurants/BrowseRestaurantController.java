package main.presentation_layer.browse_restaurants;

import java.io.IOException;
import java.util.ArrayList;

import main.dao.RestaurantDaoImpl;
import main.entities.businesses.locationTypes.Location;
import main.entities.users.Customer;
import main.entities.users.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import main.Globals;
import main.framework.Framework;
import main.framework.contexts.Context;
import main.presentation_layer.presentation.*;

public class BrowseRestaurantController {
    @FXML
    private AnchorPane restaurant_list_anchor_pane;

    private RestaurantDaoImpl restaurantDao;
    ArrayList<Location> locations;

    @FXML
    EventHandler<ActionEvent> handleBrowseRestaurant = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent evt) {
            Button btn = (Button) evt.getSource();
            int btnId = Integer.parseInt(btn.getId());
            User loggedInUser = Globals.getLoggedInUser();
            Location r = locations.get(btnId);
            if(!r.customerVerification((Customer) loggedInUser)){
                new Alert(Alert.AlertType.INFORMATION, "You are not autherized to order from this here.\n Please choose another loction.").showAndWait();
            }
            else{
            Globals.setRestaurant(locations.get(btnId));
            Framework.getInstance()
                    .onLogEvent(new Context(String.format("'%s' Has been visited", locations.get(btnId).toString())));
            // create order
            try {
                UseRemote.createOrder();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
            evt.consume();
        }
    };

    @FXML
    public void initialize() {
        System.out.println("Initialising Restaurant Listings");
        restaurantDao = new RestaurantDaoImpl();

        locations = new ArrayList<Location>();

        restaurant_list_anchor_pane.getChildren().clear();

        // Get all restaurants from DB
        locations = restaurantDao.getAll();
        System.out.println("-------------------------------");
        System.out.println(locations);

        restaurant_list_anchor_pane.setPrefHeight((locations.size() + 1) * 50);

        int index = 0;

        for (Location r : locations) {
            /*
             * <Button layoutX="318.0" layoutY="14.0" mnemonicParsing="false"
             * prefHeight="25.0" prefWidth="118.0" text="Browse Menu" /> <Text
             * layoutX="14.0" layoutY="31.0" strokeType="OUTSIDE" strokeWidth="0.0"
             * text="Restaurant Name" wrappingWidth="125.13671875" /> <Text layoutX="209.0"
             * layoutY="30.0" strokeType="OUTSIDE" strokeWidth="0.0" text="Genre" />
             */

            double y = 30.0 + (index * 50);

            Text t1 = new Text(r.getName());
            t1.setLayoutX(14.0);
            t1.setLayoutY(y);
            t1.setWrappingWidth(130.0);

            Text t2 = new Text(r.getGenre());
            t2.setLayoutX(209.0);
            t2.setLayoutY(y);
            t2.setWrappingWidth(130.0);

            Button btn = new Button();
            btn.setLayoutX(318.0);
            btn.setLayoutY(y - 16);
            btn.prefHeight(25.0);
            btn.setPrefWidth(118.0);
            btn.setText("Browse Menu");
            btn.setOnAction(handleBrowseRestaurant);
            btn.setId(Integer.toString(index));

            restaurant_list_anchor_pane.getChildren().addAll(t1, t2, btn);

            index++;
        }
    }

}
