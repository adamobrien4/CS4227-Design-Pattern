package main.entities;

import java.util.ArrayList;
// Menu has a Composition('Has a') relationship with Restaurant
public class Menu {
    public String menuName;
    public ArrayList<FoodItem> listOfMainCoursesItems;
    public ArrayList<FoodItem> listOfDesertItems;
    public ArrayList<FoodItem> listOfSideItems;
    public ArrayList<FoodItem> listOfDrinkItems;


    // TODO : Delete, this is for testing
    public Menu() {
        
    }


    public Menu(String menuName, ArrayList<FoodItem> listOfMainCoursesItems, ArrayList<FoodItem> listOfDesertItems,
                ArrayList<FoodItem> listOfSideItems, ArrayList<FoodItem> listOfDrinkItems){
        this.menuName = menuName;
        this.listOfMainCoursesItems = listOfMainCoursesItems;
        this.listOfDesertItems = listOfDesertItems;
        this.listOfSideItems = listOfSideItems;
        this.listOfDrinkItems = listOfDrinkItems;
    }

    public String getMenuName() {
        return menuName;
    }

    public ArrayList<FoodItem> getListOfMainCoursesItems() {
        return listOfMainCoursesItems;
    }

    public ArrayList<FoodItem> getListOfDesertItems() {
        return listOfDesertItems;
    }

    public ArrayList<FoodItem> getListOfSideItems() {
        return listOfSideItems;
    }


}
