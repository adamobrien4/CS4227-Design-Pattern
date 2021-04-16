package main.presentation_layer.create_order;

import main.dao.DiscountDaoImpl;
import main.dao.MenuDaoImpl;
import main.dao.OrderDaoImpl;
import main.entities.businesses.locationTypes.Location;
import main.entities.users.User;
import main.exceptions.APIException;
import main.framework.Framework;
import main.framework.contexts.Context;
import main.memento.OrderCaretaker;
import main.memento.OrderMemento;
import main.observer.OrderEditor;
import main.observer.listeners.BasketItemAddedListener;
import main.presentation_layer.presentation.*;
import main.visitor.TaxVisitor;
import main.entities.*;
import main.Globals;
import main.entities.users.Customer;

import java.io.IOException;
import java.security.Key;
import java.util.*;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.visitor.*;

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
    private Button undo_btn;

    @FXML
    private Button redo_btn;

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
    private ResourceBundle resources;

    ArrayList<FoodItem> mainCourses;
    ArrayList<FoodItem> desserts;
    ArrayList<FoodItem> sides;
    ArrayList<FoodItem> drinks;

    Map<String, BasketItem> basket = new HashMap<String, BasketItem>();

    OrderEditor orderEditor = new OrderEditor();

    double basketTotal = 0.00;
    double deliveryCost = 4.00;
    double basketSubTotalPrice = 0.00;
    Discount discount = null;
    double discountValue = 0;
    Customer loggedInCustomer;
    TaxVisitor tax = new TaxVisitor();
    Alert alert = new Alert(AlertType.INFORMATION);


    int saveFiles = 0;
    int currentArticle = 0;

    OrderCaretaker orderCaretaker = new OrderCaretaker();

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

            try {

                if (basket.containsKey(btnId)) {
                    System.out.println("Increasing Quantity");
                    basket.get(btnId).incrementQuantity();
                } else {
                    System.out.println("Adding new item");
                    basket.put(btnId, BasketItem.fromFoodItem(course.get(index)));
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }

            orderEditor.addOrderItem(basket.get(btnId));

            orderCaretaker.addMemento(new OrderMemento(hardCopyOfBasket(basket)));

            undo_btn.setDisable(false);
            redo_btn.setDisable(false);
            saveFiles++;
            currentArticle++;

            System.out.println("currentArticle = " + currentArticle);
            System.out.println("-----------------\nORDER CARETAKER AFTER ADDING NEW ITEM/+ Quantity\n" +
                    orderCaretaker.getMemento(currentArticle -1).getSavedArticle().toString());


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

            orderCaretaker.addMemento(new OrderMemento(hardCopyOfBasket(basket)));
            undo_btn.setDisable(false);
            redo_btn.setDisable(false);
            saveFiles++;
            currentArticle++;

            System.out.println("-----------------\nORDER CARETAKER AFTER REDUCING QUANTITY OF AN ITEM\n");

            System.out.println(orderCaretaker.getMemento(currentArticle).getSavedArticle().toString());


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
    private void handleUndoBtn(ActionEvent evt) {
        System.out.println("Undo button has been pressed");

        if(currentArticle > 0) {
            currentArticle--;

            // basket is set to the memento one position back
            System.out.println("-----------------\nORDER CARETAKER AFTER UNDO\n" + orderCaretaker);

            basket = orderCaretaker.getMemento(currentArticle).getSavedArticle();
            System.out.println(orderCaretaker.getMemento(currentArticle).getSavedArticle());

            redo_btn.setDisable(false);
            updateBasket();
        }
        else {
            System.out.println("Undo out of index");
            undo_btn.setDisable(true);
        }
        evt.consume();
    };

    @FXML
    private void handleRedoBtn(ActionEvent evt) {
        System.out.println("Redo button has been pressed");

        if(saveFiles -1 > currentArticle) {
            currentArticle++;

            System.out.println("-----------------\nORDER CARETAKER AFTER REDO\n" + orderCaretaker);
            basket = orderCaretaker.getMemento(currentArticle).getSavedArticle();

            updateBasket();

            undo_btn.setDisable(false);
        }
        else {
            System.out.println("Redo out of index");
            redo_btn.setDisable(true);
        }
        evt.consume();
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
            UseRemote.browseRestaurants();
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
        System.out.println("Initialise CreateOrderController");

        // Setup Listeners
        orderEditor.events.subscribe("added", new BasketItemAddedListener());

        Location location = Globals.getRestaurant();
        if(!Globals.getLoggedInUser().getType().equals(User.CUSTOMER)) {
            User u = Globals.getLoggedInUser();
            Globals.setLoggedInUser(new Customer(u.getEmail(), u.getPassword(), "Unset Address"));
        }

        loggedInCustomer = (Customer)Globals.getLoggedInUser();

        Menu restaurantMenu = MenuDaoImpl.getInstance().get(location.getMenuId().toHexString());

        System.out.println("Location:" + location);
        System.out.println("Menu:" + restaurantMenu);

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

        // When screen loads first basket will be empty
        undo_btn.setDisable(true);
        redo_btn.setDisable(true);

        updateBasket();

        // We need to treat the initial state (an empty basket) as a saved state also as we may want to return to this position via undo btn
        orderCaretaker.addMemento(new OrderMemento(hardCopyOfBasket(basket)));
        saveFiles++;
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
            System.out.println("Update Price");
            double itemSubTotal = item.acceptPrice(tax) * item.getQuantity();
            
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

    public Map<String,BasketItem> hardCopyOfBasket(Map<String,BasketItem> originalBasket) {
        Map<String, BasketItem> basketCopy = new HashMap<>();

        System.out.println("-----------------COPYING BASKET----------------");
        for(String key : originalBasket.keySet()) {
            basketCopy.put(key,originalBasket.get(key));
            System.out.println(key + " : " + originalBasket.get(key));
        }

        return basketCopy;
    }
}
