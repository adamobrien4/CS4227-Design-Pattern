package main.presentation_layer.create_order;

import main.entities.FoodItem;
import main.entities.Order;
import main.Globals;
import main.data_layer.DatabaseRepository;
import main.entities.BasketItem;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.ResourceBundle;

import org.bson.Document;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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
    private AnchorPane basket_pane;

    @FXML
    private URL location;
    @FXML
    private ResourceBundle resources;

    DatabaseRepository db;

    FoodItem[] mainCourses;
    FoodItem[] desserts;
    FoodItem[] sides;
    FoodItem[] drinks;

    Map<String, BasketItem> basket = new HashMap<String, BasketItem>();

    double basketTotal;
    double deliveryCost = 4.00;
    String discountCode = "";
    double discountTotal = 0.00;

    EventHandler<ActionEvent> addToBasketHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent evt) {
            Button btn = (Button) evt.getSource();
            String btnId = btn.getId();
            int index = Integer.parseInt(btnId.substring(2));

            if (basket.containsKey(btnId)) {
                System.out.println("Increasing Quantity");
                basket.get(btnId).incrementQuantity();
            } else {
                System.out.println("Adding new item");
                basket.put(btnId, BasketItem.fromFoodItem(mainCourses[index]));
            }

            updateBasket();
            evt.consume();
        }
    };

    EventHandler<ActionEvent> removeFromBasketHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent evt) {
            Button btn = (Button) evt.getSource();
            String btnId = btn.getId();

            if (basket.containsKey(btnId)) {
                System.out.println("Decreasing Quantity");
                if (basket.get(btnId).decrementQuantity()) {
                    basket.remove(btnId);
                }
            }

            updateBasket();
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
        discountCode = discount_code_entry_field.getText();
        System.out.println("Apply Discount Code : " + discountCode);
        discountTotal = 23.00;

        evt.consume();
        updateBasket();
    };

    @FXML
    private void handleGoBackButton(ActionEvent evt) {
        Button btn = (Button) evt.getSource();
        Scene scene = btn.getScene();

        try {
            Parent root = FXMLLoader.load(getClass().getResource("../browse_restaurants/BrowseRestaurant.fxml"));
            Stage stage = (Stage) scene.getWindow();
            Scene scene2 = new Scene(root);
            stage.setScene(scene2);
        } catch (IOException io) {
            System.out.println("Error loading Checkout");
            System.out.println(io.toString());
        }

        evt.consume();
    }

    @FXML
    private void handleCheckout(ActionEvent evt) {
        System.out.println("Handling Checkout");

        Order order;

        ArrayList<String> orderItems = new ArrayList<String>();

        for (BasketItem item : basket.values()) {
            String s = item.getName();
            if (item.getQuantity() > 0) {
                s += " * " + item.getQuantity();
            }
            orderItems.add(s);
        }

        if (discountTotal > 0) {
            order = new Order(basketTotal, discountCode, discountTotal, deliveryCost,
                    orderItems.toArray(new String[orderItems.size()]));
        } else {
            order = new Order(basketTotal, deliveryCost, orderItems.toArray(new String[orderItems.size()]));
        }

        db.insertOrder(order);

        Button btn = (Button) evt.getSource();
        Scene scene = btn.getScene();

        try {
            Parent root = FXMLLoader.load(getClass().getResource("../checkout_order/CheckoutOrder.fxml"));
            Stage stage = (Stage) scene.getWindow();
            Scene scene2 = new Scene(root);
            stage.setScene(scene2);
        } catch (IOException io) {
            System.out.println("Error loading Checkout");
            System.out.println(io.toString());
        }

        evt.consume();
    }

    @FXML
    public void initialize() {
        // Initialise the controller
        System.out.println("Initialise");

        // TODO : Query DB for restaurant menu
        db = new DatabaseRepository();

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
        if (item.hasAllergens()) {
            alg.setLayoutX(163.0);
            alg.setLayoutY(y);
            alg.setText("Allergens: " + String.join(", ", item.getAllergens()));
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

    private void updateBasket() {
        ObservableList<Node> children = basket_pane.getChildren();

        children.clear();

        int index = 0;
        double basketSubTotalPrice = 0.0;

        BasketItem[] items = basket.values().toArray(new BasketItem[basket.size()]);
        String[] keys = basket.keySet().toArray(new String[basket.size()]);

        for (int i = items.length - 1; i >= 0; i--) {
            BasketItem item = items[i];
            String key = keys[i];
            String title = item.getName();
            if (item.getQuantity() > 1) {
                title += " * " + item.getQuantity();
            }
            double itemSubTotal = item.getPrice() * item.getQuantity();
            title += " (€" + String.format("%.2f", itemSubTotal) + ")";

            basketSubTotalPrice += itemSubTotal;

            Text txt = new Text(title);
            txt.setLayoutX(14.0);
            txt.setLayoutY(27.0 + (33.0 * index));

            Button btn = new Button("-");
            btn.setLayoutX(231.0);
            btn.setLayoutY(10.0 + (33.0 * index));
            btn.setId(key);
            btn.setPrefWidth(40.0);
            btn.setOnAction(removeFromBasketHandler);

            Button btn2 = new Button("+");
            btn2.setLayoutX(191.0);
            btn2.setLayoutY(10.0 + (33.0 * index));
            btn2.setId(key);
            btn2.setPrefWidth(40.0);
            btn2.setOnAction(addToBasketHandler);

            children.addAll(txt, btn, btn2);
            index++;
        }

        // Update Cost details
        String NUM_FORMAT = "€%.2f";
        basket_sub_total.setText(String.format(NUM_FORMAT, basketSubTotalPrice));
        delivery_total.setText(String.format(NUM_FORMAT, deliveryCost));
        discount_amount.setText(String.format(NUM_FORMAT, discountTotal));

        basketTotal = basketSubTotalPrice + deliveryCost - discountTotal;
        if (basketTotal < 0) {
            discountTotal = 0.00;
        }
        basket_total.setText(String.format(NUM_FORMAT, basketTotal));
    }
}
