package main;

import java.util.ArrayList;
import java.util.Arrays;

import main.entities.businesses.locationTypes.Location;
import main.entities.users.User;

public class Globals {
    private static User loggedInUser;
    private static Location restaurant;

    private Globals() {
    }

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(User user) {
        Globals.loggedInUser = user;
    }

    public static Location getRestaurant() {
        return restaurant;
    }

    public static void setRestaurant(Location r) {
        Globals.restaurant = r;
    }

    public static final String APPLICATION_API_URL = "https://cs4227-design-pattern-api.herokuapp.com";
    // public static final String APPLICATION_API_URL = "http://localhost:5000";

    // Tax Visitor
    public static final double SUGAR_TAX = 0.09;
    public static final double EVENT_TAX = 0.09;
    public static final ArrayList<String> TAXED_DRINKS = new ArrayList<String>(
            Arrays.asList("coke", "coke-cola", "Coke", "Coke-Cola", "Fanta", "fanta"));

    // Checkout Conroller- Bridge design pattern
    public static final String CREDIT_CARD = "Credit Card";
    public static final String DEBIT_CARD="Debit Card";
    public static final String[] CARD_TYPES = new String[] { "Debit Card", "Credit Card" };

}
