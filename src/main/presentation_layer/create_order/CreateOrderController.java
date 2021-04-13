package main.presentation_layer.create_order;

import main.dao.DiscountDaoImpl;
import main.dao.MenuDaoImpl;
import main.dao.OrderDaoImpl;
import main.exceptions.APIException;
import main.framework.Framework;
import main.framework.contexts.Context;
import main.presentation_layer.presentation.*;
import main.entities.*;
import main.Globals;
import main.entities.users.Customer;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
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

    ArrayList<FoodItem> mainCourses;
    ArrayList<FoodItem> desserts;
    ArrayList<FoodItem> sides;
    ArrayList<FoodItem> drinks;

    Map<String, BasketItem> basket = new HashMap<String, BasketItem>();

    double basketTotal = 0.00;
    double deliveryCost = 4.00;
    double basketSubTotalPrice = 0.00;
    Discount discount = null;
    double discountValue = 0;
    Customer loggedInCustomer;

    EventHandler<ActionEvent> addToBasketHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent evt) {
            Button btn = (Button) evt.getSource();
            String btnId = btn.getId();
            String abb = btnId.substring(0, 2);
            int index = Integer.parseInt(btnId.substring(2));

            ArrayList<FoodItem> course = null;

            switch(abb) {
                case "mc":
                    course = mainCourses;
                break;
                case "ds":
                    course = desserts;
                break;
                case "sd":
                    course = sides;
                break;
                case "dk":
                    course = drinks;
                break;
                default:
                break;
            }


            if (basket.containsKey(btnId)) {
                System.out.println("Increasing Quantity");
                basket.get(btnId).incrementQuantity();
            } else {
                System.out.println("Adding new item");
                basket.put(btnId, BasketItem.fromFoodItem(course.get(index)));
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
        System.out.println("Apply Discount Code : " + discount_code_entry_field.getText());

        // TODO: Get dicsount from database
        discount = DiscountDaoImpl.getInstance().get(discount_code_entry_field.getText());

        if(discount == null) {
            // Discount does not exist
            discount_code_entry_field.clear();
            return;
        }

        evt.consume();
        updateBasket();
    };

    @FXML
    private void handleGoBackButton(ActionEvent evt) {
        //browse restaurat

        try {
            UseRemote.browserestaurants();
        } catch (IOException e) {
            e.printStackTrace();
        }

        evt.consume();
    }

    @FXML
    private void handleCheckout(ActionEvent evt) {
        System.out.println("Handling Checkout");
        evt.consume();

        Order order;

        ArrayList<String> orderItems = new ArrayList<>();

        for (BasketItem item : basket.values()) {
            String s = item.getName();
            if (item.getQuantity() > 0) {
                s += " * " + item.getQuantity();
            }
            orderItems.add(s);
        }

        if (discountValue > 0) {
            order = new Order.Builder<>().totalCost(basketTotal).discountCode(discount.getCode()).discountAmount(discountValue).deliveryCost(deliveryCost).orderItems(orderItems).address(loggedInCustomer.getAddress()).build();
        } else {
            order = new Order.Builder<>().totalCost(basketTotal).deliveryCost(deliveryCost).orderItems(orderItems).address(loggedInCustomer.getAddress()).build();
        }

        order.setRestaurant(Globals.getRestaurant().getId());

        try {
            OrderDaoImpl.getInstance().insert(order);
            Framework.getInstance().onLogEvent(new Context(String.format("'%s' Has Now been Placed",order.toString())));
            UseRemote.checkout();
        } catch (APIException | IOException e) {
            e.printStackTrace();
        }

        evt.consume();
    }

    @FXML
    public void initialize() {
        // Initialise the controller
        System.out.println("Initialise");

        main.entities.businesses.LocationTypes.Location location = Globals.getRestaurant();
        loggedInCustomer = (Customer)Globals.getLoggedInUser();

        Menu restaurantMenu = location.getMenu();

        // Testing
        mainCourses = restaurantMenu.getListOfMainCoursesItems();
        desserts = restaurantMenu.getListOfDessertItems();
        sides = restaurantMenu.getListOfSideItems();
        drinks = restaurantMenu.getListOfDrinksItems();

        // Main Course
        if (mainCourses.isEmpty()) {
            main_course_tab_pane.getChildren().add(noFoodItemsMessage());
        }
        for (int i = 0; i < mainCourses.size(); i++) {
            // Add food item to drinks tab
            double y = 27.0 + (33.0 * i);
            Node[] foodListing = makeFoodListing(mainCourses.get(i), y, "mc", i);
            main_course_tab_pane.getChildren().addAll(foodListing[0], foodListing[1], foodListing[2], foodListing[3]);
        }

        // Desserts
        if (desserts.isEmpty()){
            desserts_tab_pane.getChildren().add(noFoodItemsMessage());
        }
        for (int i = 0; i < desserts.size(); i++) {

            // Add food item to drinks tab
            double y = 27.0 + (33.0 * i);
            Node[] foodListing = makeFoodListing(desserts.get(i), y, "ds", i);
            desserts_tab_pane.getChildren().addAll(foodListing[0], foodListing[1], foodListing[2], foodListing[3]);
        }

        // Sides
        if (sides.isEmpty()) {
            sides_tab_pane.getChildren().add(noFoodItemsMessage());
        }
        for (int i = 0; i < sides.size(); i++) {

            // Add food item to drinks tab
            double y = 27.0 + (33.0 * i);
            Node[] foodListing = makeFoodListing(sides.get(i), y, "sd", i);
            sides_tab_pane.getChildren().addAll(foodListing[0], foodListing[1], foodListing[2], foodListing[3]);
        }

        // Drinks
        if (drinks.isEmpty()) {
            drinks_tab_pane.getChildren().add(noFoodItemsMessage());
        }
        for (int i = 0; i < drinks.size(); i++) {

            // Add food item to drinks tab
            double y = 27.0 + (33.0 * i);
            Node[] foodListing = makeFoodListing(drinks.get(i), y, "dk", i);
            drinks_tab_pane.getChildren().addAll(foodListing[0], foodListing[2], foodListing[3]);
        }

        updateBasket();
    }

    private Node[] makeFoodListing(FoodItem item, double y, String abb, int btnIndex) {
        Text name = new Text();
        name.setLayoutX(14.0);
        name.setLayoutY(y);
        name.setText(item.getName());
        name.setOnMouseClicked(nameClickHandler);

        Text alg = new Text();
        if (item.hasAllergens()) {
            alg.setLayoutX(250.0);
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
        basketSubTotalPrice = 0.0;

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

        basketTotal = basketSubTotalPrice + deliveryCost;
        discountValue = 0.0;

        if (discount != null) {
            switch(discount.getType()) {
                case Discount.FOOD_DISCOUNT:
                if (discount.getAmount() > (basketTotal + deliveryCost - 10)) {
                    discountValue = (basketTotal + deliveryCost - 10);
                } else {
                    discountValue = discount.getAmount();
                }
                discountValue = Math.max(0.0, discountValue);
                break;
                case Discount.DELIVERY_DISCOUNT:
                    deliveryCost = deliveryCost - discount.getAmount();
                    deliveryCost = Math.max(0, deliveryCost);
                    discountValue = discount.getAmount();
                break;
                default:
                break;
            }
        } else {
            discountValue = 0.0;
        }
        

        // Update Cost details
        String NUM_FORMAT = "€%.2f";
        basket_sub_total.setText(String.format(NUM_FORMAT, basketSubTotalPrice));
        delivery_total.setText(String.format(NUM_FORMAT, deliveryCost));
        discount_amount.setText(String.format(NUM_FORMAT, discountValue));

        basketTotal = basketSubTotalPrice + deliveryCost - discountValue;
        
        basket_total.setText(String.format(NUM_FORMAT, basketTotal));
    }
}
