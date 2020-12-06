package main;

import main.entities.Restaurant;
import main.entities.User;

public class Globals {
    private static User loggedInUser;
    private static Restaurant restaurant;

    private Globals(){
        
    }

    public static User getLoggedInUser() {
        return loggedInUser;
    }
    public static void setLoggedInUser(User user) {
        Globals.loggedInUser = user;
    }

    public static Restaurant getRestaurant() {
        return restaurant;
    }
    public static void setRestaurant(Restaurant r) {
        Globals.restaurant = r;
    }
}
