package main.entities;

import java.util.ArrayList;
// Course has a Composition('Has a') relationship with Menu
public class Course {
    private String name;
    private ArrayList<FoodItem> listOfItems;


    public Course(String name, ArrayList<FoodItem> listOfItems) {
        this.name = name;
        this.listOfItems = listOfItems;
    }

    public String getName() {
        return name;
    }
    public ArrayList<FoodItem> getListOfItems() {
        return listOfItems;
    }

}
