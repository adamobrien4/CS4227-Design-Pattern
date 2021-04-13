package main.visitor;

import main.entities.*;

//Vistitor Interface will be implemented into taxVistor and other similarly named classes
public interface Visitor {
    //Update Price of FoodItem
    public double visitPrice(FoodItem foodItem);    
    //Update Price of Event
    public double visitPrice(Event event);
}