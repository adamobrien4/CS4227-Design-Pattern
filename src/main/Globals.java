package main;

import main.entities.businesses.LocationTypes.Location;
import main.entities.users.User;

public class Globals {
    private static User loggedInUser;
    private static Location restaurant;

    private Globals(){}

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
}
