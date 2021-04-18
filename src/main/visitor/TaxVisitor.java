package main.visitor;

import java.util.ArrayList;

import main.Globals;
import main.entities.*;

public class TaxVisitor implements Visitor {

    public TaxVisitor() {
    }

    // Add an updated price too food item
    public double visitPrice(FoodItem foodItem) {
        if (Globals.TAXED_DRINKS.contains(foodItem.getName())) {
            System.out.println("Food Item original price = " + foodItem.getPrice() + "\n new price is"
                    + (foodItem.getPrice() * Globals.SUGAR_TAX) + foodItem.getPrice() + "Name is "
                    + foodItem.getName());
            return (foodItem.getPrice() * Globals.SUGAR_TAX) + foodItem.getPrice();
        } else {
            return foodItem.getPrice();
        }
    }

    // Add an updated price to Event
    @Override
    public double visitPrice(Event event) {
        System.out.println("Event TaxVisitor");
        return (event.getPrice() * Globals.EVENT_TAX) + event.getPrice();
    }

}