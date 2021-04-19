package main.entities.discount_visitor;

import main.entities.*;

//Vistitor Interface will be implemented into taxVistor and other similarly named classes
public interface Visitor {

    // Update Price of Event
    public String visitDiscount(Discount discount, FoodItem foodItem);
}