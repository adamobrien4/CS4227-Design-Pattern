package main.presentation_layer;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.presentation_layer.browse_restaurants.BrowseRestaurantController;
import main.presentation_layer.checkout_order.CheckoutOrderController;
import main.presentation_layer.create_order.CreateOrderController;
import main.presentation_layer.driver.DriverScreenController;
import main.presentation_layer.signup.SignupController;
import main.presentation_layer.login.LoginController;

public class PresentationLoader {

    public static final int LOGIN = 0;
    public static final int SIGNUP = 1;
    public static final int BROWSE_RESTAURANT = 2;
    public static final int CREATE_ORDER = 3;
    public static final int CHECKOUT_ORDER = 4;
    public static final int DELIVERY_DRIVER = 5;

    private static Stage stage;

    private static PresentationLoader instance = null;

    private PresentationLoader() {}

    public static PresentationLoader getInstance() {
        if (instance == null) {
            instance = new PresentationLoader();
        }
        return instance;
    }

    public static void setStage(Stage stage) {
        PresentationLoader.stage = stage;
    }

    public static Stage getStage() {
        return stage;
    }

    public void display(int screen) {

        if (PresentationLoader.stage == null) {
            System.out.println("Please setup stage before continuing");
            System.exit(0);
        }

        Parent root = null;

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
                    prefHeight = 436.0;
                    root = FXMLLoader.load(SignupController.class.getResource("SignupFX.fxml"));
                    break;
                case BROWSE_RESTAURANT:
                    prefWidth = 600.0;
                    prefHeight = 600.0;
                    root = FXMLLoader.load(BrowseRestaurantController.class.getResource("BrowseRestaurant.fxml"));
                    break;
                case CREATE_ORDER:
                    prefWidth = 1000.0;
                    prefHeight = 800.0;
                    root = FXMLLoader.load(CreateOrderController.class.getResource("CreateOrder.fxml"));
                    break;
                case CHECKOUT_ORDER:
                    root = FXMLLoader.load(CheckoutOrderController.class.getResource("CheckoutOrder.fxml"));
                    break;
                case DELIVERY_DRIVER:
                    prefWidth = 900.0;
                    prefHeight = 600.0;
                    root = FXMLLoader.load(DriverScreenController.class.getResource("DriverScreen.fxml"));
                    break;
                default:
                    break;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Could not load screen");
            System.exit(0);
        }

        Stage stg = PresentationLoader.stage;

        stg.setTitle("Delivery Eats");
        stg.setScene(new Scene(root, prefWidth, prefHeight));
        stg.show();
    }
}
