package main.presentation_layer.Presentation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UseRemote {
    static PresentationLoader newDevice = (PresentationLoader) Remote.getPres();

    public static void checkout() throws IOException {
        PrescheckoutOrder onCommand = new PrescheckoutOrder(newDevice);
        Screen onSwap = new Screen(onCommand);
        onSwap.change();
    }

    public static void signup() throws IOException {
        PresSignup onCommand = new PresSignup(newDevice);
        Screen onSwap = new Screen(onCommand);
        onSwap.change();
    }

    public static void login() throws IOException {
        PresLogin onCommand = new PresLogin(newDevice);
        Screen onSwap = new Screen(onCommand);
        onSwap.change();
    }

    public static void driver() throws IOException {
        PresDriver onCommand = new PresDriver(newDevice);
        Screen onSwap = new Screen(onCommand);
        onSwap.change();
    }

    public static void browserestaurants() throws IOException {
        PresBrowseRestaurants onCommand = new PresBrowseRestaurants(newDevice);
        Screen onSwap = new Screen(onCommand);
        onSwap.change();
    }

    public static void createorder() throws IOException {
        PresCreateOrder onCommand = new PresCreateOrder(newDevice);
        Screen onSwap = new Screen(onCommand);
        onSwap.change();
    }

}
