package main.entities;

import java.util.ArrayList;

public abstract class MenuTemplate {
    abstract String getMenuName();
    abstract void printInfo();
    abstract ArrayList<FoodItem> getList();


    // template method
    public final ArrayList<FoodItem> getListOfItems() {
        printInfo();
        ArrayList<FoodItem> foodItems = getList();

        return foodItems;
    }

}

