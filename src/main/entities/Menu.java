package main.entities;

import java.util.ArrayList;
// Menu has a Composition('Has a') relationship with Restaurant
public class Menu {
    public String menuName;
    private ArrayList<FoodItem> listOfMainCoursesItems;
    private ArrayList<FoodItem> listOfDessertItems;
    private ArrayList<FoodItem> listOfSideItems;
    private ArrayList<FoodItem> listOfDrinkItems;

    public Menu(String menuName, ArrayList<FoodItem> listOfMainCoursesItems, ArrayList<FoodItem> listOfDesertItems,
                ArrayList<FoodItem> listOfSideItems, ArrayList<FoodItem> listOfDrinkItems){
        this.menuName = menuName;
        this.listOfMainCoursesItems = listOfMainCoursesItems;
        this.listOfDessertItems = listOfDesertItems;
        this.listOfSideItems = listOfSideItems;
        this.listOfDrinkItems = listOfDrinkItems;
    }

    public String getMenuName() {
        return menuName;
    }

    public ArrayList<FoodItem> getListOfMainCoursesItems() {
        return listOfMainCoursesItems;
    }

    public ArrayList<FoodItem> getListOfDessertItems() {
        return listOfDessertItems;
    }

    public ArrayList<FoodItem> getListOfSideItems() {
        return listOfSideItems;
    }

    public ArrayList<FoodItem> getListOfDrinksItems() {
        return listOfDrinkItems;
    }


}
