package main.presentation_layer.browse_restaurants;

import java.io.IOException;
import java.util.ArrayList;

import org.bson.types.ObjectId;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.Globals;
import main.entities.Menu;
import main.entities.Restaurant;

public class BrowseRestaurantController {
    @FXML
    private AnchorPane restaurant_list_anchor_pane;

    ArrayList<Restaurant> restaurants;

    @FXML
    EventHandler<ActionEvent> handleBrowseRestaurant = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent evt) {
            Button btn = (Button)evt.getSource();
            int btnId = Integer.parseInt(btn.getId());
            Scene scene = btn.getScene();

            Globals.setRestaurant(restaurants.get(btnId));

            try {
                Parent root = FXMLLoader.load(getClass().getResource("../create_order/CreateOrder.fxml"));
                Stage stage = (Stage) scene.getWindow();
                Scene scene2 = new Scene(root);
                stage.setScene(scene2);
            } catch (IOException io) {
                System.out.println("Error loading Checkout");
                System.out.println(io.toString());
            }

            evt.consume();
        }
    };
    
    @FXML
    public void initialize() {
        System.out.println("Initialising Checkout Screen");

        // Read restaurants from Database

        restaurants = new ArrayList<Restaurant>();

        Restaurant r1 = new Restaurant(new ObjectId(), "R1", new Menu(), "BBq");
        Restaurant r2 = new Restaurant(new ObjectId(), "R2", new Menu(), "Asian");
        Restaurant r3 = new Restaurant(new ObjectId(), "R3", new Menu(), "American");

        restaurants.add(r1);
        restaurants.add(r2);
        restaurants.add(r3);
        restaurants.add(r1);
        restaurants.add(r2);
        restaurants.add(r3);
        restaurants.add(r1);
        restaurants.add(r2);
        restaurants.add(r3);
        restaurants.add(r1);
        restaurants.add(r2);
        restaurants.add(r3);

        restaurant_list_anchor_pane.setPrefHeight((restaurants.size()+1) * 50);

        int index = 0;

        for (Restaurant r : restaurants) {
            /*
                <Button layoutX="318.0" layoutY="14.0" mnemonicParsing="false" prefHeight="25.0" prefWidth="118.0" text="Browse Menu" />
                <Text layoutX="14.0" layoutY="31.0" strokeType="OUTSIDE" strokeWidth="0.0" text="Restaurant Name" wrappingWidth="125.13671875" />
                <Text layoutX="209.0" layoutY="30.0" strokeType="OUTSIDE" strokeWidth="0.0" text="Genre" />
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
            btn.setLayoutY(y-16);
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
