package main.presentation_layer;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.presentation_layer.browse_restaurants.BrowseRestaurantController;
import main.presentation_layer.checkout_order.CheckoutOrderController;
import main.presentation_layer.create_order.CreateOrderController;
import main.presentation_layer.login.LoginController;
import main.presentation_layer.signup.SignupController;

public class PresentationLoader {

    private static Parent root;

    public static final int LOGIN = 0;
    public static final int SIGNUP = 1;
    public static final int BROWSE_RESTAURANT = 2;
    public static final int CREATE_ORDER = 3;
    public static final int CHECKOUT_ORDER = 4;

    private static Stage stage;

    private PresentationLoader() {
        System.out.println("new PresentationLoader");
    }

    public static void setStage(Stage stage) {
        PresentationLoader.stage = stage;
    }

    public static void display(int screen) {

        if (PresentationLoader.stage == null) {
            System.out.println("Please setup stage before continuing");
            System.exit(0);
        }

        double prefWidth = 800.0;
        double prefHeight = 600.0;

        try {
            switch (screen) {
                case LOGIN:
                    prefWidth = 640.0;
                    prefHeight = 400.0;
                    root = FXMLLoader.load(LoginController.class.getResource("LoginFX.fxml"));
                    break;
                case SIGNUP:
                    prefWidth = 640.0;
                    prefHeight = 400.0;
                    root = FXMLLoader.load(SignupController.class.getResource("SignupFX.fxml"));
                    break;
                case BROWSE_RESTAURANT:
                    root = FXMLLoader.load(BrowseRestaurantController.class.getResource("BrowseRestaurant.fxml"));
                    break;
                case CREATE_ORDER:
                    root = FXMLLoader.load(CreateOrderController.class.getResource("CreateOrder.fxml"));
                    prefWidth = 1000.0;
                    prefHeight = 800.0;
                    break;
                case CHECKOUT_ORDER:
                    root = FXMLLoader.load(CheckoutOrderController.class.getResource("CheckoutOrder.fxml"));
                    break;
                default:
                    break;
            }
        } catch (IOException ex) {
            System.out.println("Could not load screen");
            System.exit(0);
        }

        Stage stg = PresentationLoader.stage;

        stg.setTitle("Delivery Eats");
        stg.setScene(new Scene(root, prefWidth, prefHeight));
        stg.show();
    }
}
