package main.presentation_layer.presentation;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.framework.Framework;
import main.framework.contexts.ScreenSwitchContext;
import main.presentation_layer.browse_restaurants.BrowseRestaurantController;
import main.presentation_layer.checkout_order.CheckoutOrderController;
import main.presentation_layer.create_menu_list.CreateMenuListController;
import main.presentation_layer.create_order.CreateOrderController;
import main.presentation_layer.Driver.DriverScreenController;
import main.presentation_layer.login.LoginController;
import main.presentation_layer.signup.SignupController;

public class PresentationLoader implements Presentation {

    private static Stage stage;
    private static PresentationLoader instance = null;
    private static final double HEIGHT = 600.0;
    private static final double WIDTH = 800.0;

    PresentationLoader() {}

    public static PresentationLoader getInstance() {
        if (instance == null) {
            instance = new PresentationLoader();
        }
        return instance;
    }

    public static void setStage(Stage stage) {
        // Framework.getInstance().onScreenSwitch(new ScreenSwitchContext(String.format("Switching from screen '%s' to screen '%s'")));
        PresentationLoader.stage = stage;
    }

    public static Stage getStage() {
        return stage;
    }


    @Override
    public void login() throws IOException {
        Stage stg = PresentationLoader.stage;
        Parent root = FXMLLoader.load(LoginController.class.getResource("LoginFX.fxml"));
        stg.setTitle("Delivery Eats");
        stg.setScene(new Scene(root, 640.0, 400.0));
        stg.show();

    }

    @Override
    public void signup() throws IOException {
        Stage stg = PresentationLoader.stage;
        Parent root = FXMLLoader.load(SignupController.class.getResource("SignupFX.fxml"));
        stg.setTitle("Delivery Eats");
        stg.setScene(new Scene(root, 640.0, 436.0));
        stg.show();

    }

    @Override
    public void checkout_order() throws IOException {
        Stage stg = PresentationLoader.stage;
        Parent root = FXMLLoader.load(CheckoutOrderController.class.getResource("CheckoutOrder.fxml"));
        stg.setTitle("Delivery Eats");
        stg.setScene(new Scene(root, WIDTH, HEIGHT));
        stg.show();

    }

    @Override
    public void driver() throws IOException {
        Stage stg = PresentationLoader.stage;
        Parent root = FXMLLoader.load(DriverScreenController.class.getResource("DriverScreen.fxml"));
        stg.setTitle("Delivery Eats");
        stg.setScene(new Scene(root, 900.0, 600.0));
        stg.show();
    }

    @Override
    public void browse_restaurants() throws IOException {
        Stage stg = PresentationLoader.stage;
        Parent root = FXMLLoader.load(BrowseRestaurantController.class.getResource("BrowseRestaurant.fxml"));
        stg.setTitle("Delivery Eats");
        stg.setScene(new Scene(root, WIDTH, HEIGHT));
        stg.show();
    }

    @Override
    public void create_order() throws IOException {
        Stage stg = PresentationLoader.stage;
        Parent root = FXMLLoader.load(CreateOrderController.class.getResource("CreateOrder.fxml"));
        stg.setTitle("Delivery Eats");
        stg.setScene(new Scene(root, 1000.0, 800.0));
        stg.show();
    }
    
    @Override
    public void create_menu_list() throws IOException{
        Stage stg = PresentationLoader.stage;
        Parent root =FXMLLoader.load(CreateMenuListController.class.getResource("create_menu_list.fxml"));
        stg.setTitle("Deliver Eats");
        stg.setScene(new Scene(root,1000.0,800.0));
        stg.show();
    }
}
