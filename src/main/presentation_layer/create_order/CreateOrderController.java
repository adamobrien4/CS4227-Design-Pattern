package main.presentation_layer.create_order;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.entities.FoodItem;

public class CreateOrderController {
    @FXML
    private Text basket_sub_total;
    @FXML
    private Text discount_amount;
    @FXML
    private Text delivery_total;
    @FXML
    private Text basket_total;

    @FXML
    private TextField discount_code_entry_field;

    @FXML
    private Button discount_code_apply_btn;

    @FXML
    private AnchorPane main_course_tab_pane;
    @FXML
    private AnchorPane desserts_tab_pane;
    @FXML
    private AnchorPane sides_tab_pane;
    @FXML
    private AnchorPane drinks_tab_pane;

    @FXML
    private URL location;
    @FXML
    private ResourceBundle resources;

    FoodItem[] mainCourses;
    FoodItem[] desserts;
    FoodItem[] sides;
    FoodItem[] drinks;

    EventHandler<ActionEvent> addToBasketHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent evt) {
            Button btn = (Button) evt.getSource();
            System.out.println(btn.getId());

            evt.consume();
        }
    };

    EventHandler<MouseEvent> nameClickHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent evt) {
            Image image = new Image(
                    "https://upload.wikimedia.org/wikipedia/commons/thumb/4/42/Emojione_1F62D.svg/64px-Emojione_1F62D.svg.png");
            ImageView imageView = new ImageView(image);

            // Our image will sit in the middle of our popup.
            BorderPane pane = new BorderPane();
            pane.setCenter(imageView);
            Scene scene = new Scene(pane);

            // Create the actual window and display it.
            Stage stage = new Stage();
            stage.setScene(scene);
            // Without this, the audio won't stop!
            stage.setOnCloseRequest(e -> {
                e.consume();
                stage.close();
            });
            stage.showAndWait();
            evt.consume();
        }
    };

    @FXML
    private void handleApplyDiscount(ActionEvent evt) {
        System.out.println("Apply Discount Code");

        evt.consume();
    };

    @FXML
    public void initialize() {
        // Initialise the controller
        System.out.println("Initialise");

        // Query DB for restaurant menu

        // Testing
        mainCourses = new FoodItem[] { new FoodItem("Burger", 45.99),
                new FoodItem("Kebab", new String[] { "Wheat", "Soya" }, 24.99), };
        desserts = new FoodItem[] {};
        sides = new FoodItem[] {};
        drinks = new FoodItem[] {};

        // Main Course
        if (mainCourses.length == 0) {
            main_course_tab_pane.getChildren().add(noFoodItemsMessage());
        }
        for (int i = 0; i < mainCourses.length; i++) {
            // Add food item to drinks tab
            double y = 27.0 + (33.0 * i);
            Node[] foodListing = makeFoodListing(mainCourses[i], y, "mc", i);
            main_course_tab_pane.getChildren().addAll(foodListing[0], foodListing[1], foodListing[2], foodListing[3]);
        }

        // Desserts
        if (desserts.length == 0) {
            desserts_tab_pane.getChildren().add(noFoodItemsMessage());
        }
        for (int i = 0; i < desserts.length; i++) {

            // Add food item to drinks tab
            double y = 27.0 + (33.0 * i);
            Node[] foodListing = makeFoodListing(desserts[i], y, "ds", i);
            desserts_tab_pane.getChildren().addAll(foodListing[0], foodListing[1], foodListing[2], foodListing[3]);
        }

        // Sides
        if (sides.length == 0) {
            sides_tab_pane.getChildren().add(noFoodItemsMessage());
        }
        for (int i = 0; i < sides.length; i++) {

            // Add food item to drinks tab
            double y = 27.0 + (33.0 * i);
            Node[] foodListing = makeFoodListing(sides[i], y, "sd", i);
            sides_tab_pane.getChildren().addAll(foodListing[0], foodListing[1], foodListing[2], foodListing[3]);
        }

        // Drinks
        if (drinks.length == 0) {
            drinks_tab_pane.getChildren().add(noFoodItemsMessage());
        }
        for (int i = 0; i < drinks.length; i++) {

            // Add food item to drinks tab
            double y = 27.0 + (33.0 * i);
            Node[] foodListing = makeFoodListing(drinks[i], y, "dk", i);
            drinks_tab_pane.getChildren().addAll(foodListing[0], foodListing[2], foodListing[3]);
        }
    }

    private Node[] makeFoodListing(FoodItem item, double y, String abb, int btnIndex) {
        Text name = new Text();
        name.setLayoutX(14.0);
        name.setLayoutY(y);
        name.setText(item.getName());
        name.setOnMouseClicked(nameClickHandler);

        Text alg = new Text();
        String[] allergens = item.getAllergens();
        if (allergens != null && allergens.length > 0) {
            alg.setLayoutX(163.0);
            alg.setLayoutY(y);
            alg.setText("Allergens: " + String.join(", ", allergens));
        }

        Text prc = new Text();
        prc.setLayoutX(552.0);
        prc.setLayoutY(y);
        prc.setText("€" + item.getPrice());

        Button btn = new Button("Add to Basket");
        btn.setLayoutX(596.0);
        btn.setLayoutY(y - 17.0);
        btn.setId(abb + btnIndex);
        btn.setOnAction(addToBasketHandler);

        return new Node[] { name, alg, prc, btn };
    }

    private Node noFoodItemsMessage() {
        Text noFoodListingsMessage = new Text("No Items to display");
        noFoodListingsMessage.setLayoutX(299.0);
        noFoodListingsMessage.setLayoutY(365.0);
        return noFoodListingsMessage;
    }
}
