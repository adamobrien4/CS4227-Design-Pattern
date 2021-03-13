package main.presentation_layer.browse_restaurants;

import java.util.ArrayList;

import main.entities.users.RestaurantDaoImpl;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import main.Globals;
import main.entities.Restaurant;
import main.presentation_layer.PresentationLoader;
import org.bson.types.ObjectId;


public class BrowseRestaurantController {
    @FXML
    private AnchorPane restaurant_list_anchor_pane;

    private RestaurantDaoImpl restaurantDao;
    ArrayList<Restaurant> restaurants;

    @FXML
    EventHandler<ActionEvent> handleBrowseRestaurant = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent evt) {
            Button btn = (Button) evt.getSource();
            int btnId = Integer.parseInt(btn.getId());

            Globals.setRestaurant(restaurants.get(btnId));

            PresentationLoader.getInstance().display(PresentationLoader.CREATE_ORDER);

            evt.consume();
        }
    };

    @FXML
    public void initialize() {
        System.out.println("Initialising Restaurant Listings");

        System.out.println("Clearing restaurants array");
        restaurants = new ArrayList<Restaurant>();
        System.out.println("Cleared");

        System.out.println("Clearing pane");
        restaurant_list_anchor_pane.getChildren().clear();
        System.out.println("Cleared");

        // Get all restaurants from DB
        System.out.println("getting All");


        restaurants = restaurantDao.getAll();



        restaurants.add(new Restaurant(new ObjectId(), "Name", new ObjectId(), "Genre"));

        System.out.println("getAll REsp:");
        System.out.println(restaurants);

        restaurant_list_anchor_pane.setPrefHeight((restaurants.size() + 1) * 50);

        int index = 0;

        for (Restaurant r : restaurants) {
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
