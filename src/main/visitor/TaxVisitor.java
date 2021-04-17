package main.visitor;
import java.util.ArrayList;

import main.entities.*;

public class TaxVisitor implements Visitor{
    private double eventTax =0.09;
    private double foodItemTax =0.09;
    ArrayList<String> taxedDrinks = new ArrayList<String>();



    public TaxVisitor(){
    }

    //Add an updated price too food item
    public double visitPrice(FoodItem foodItem){
        taxedDrinks.add("coke");
        taxedDrinks.add("Fanta");
        if(taxedDrinks.contains(foodItem.getName())){ 
        System.out.println("Food Item original price = " + foodItem.getPrice() + "\n new price is" + (foodItem.getPrice()*foodItemTax)+foodItem.getPrice() + "Name is " + foodItem.getName());
        return (foodItem.getPrice()*foodItemTax)+foodItem.getPrice();
        }
        else{
            return foodItem.getPrice();
        }
    }
    //Add an updated price to Event
    @Override
    public double visitPrice(Event event){
        System.out.println("Event TaxVisitor");
        return (event.getPrice()*eventTax)+event.getPrice();
    }

}