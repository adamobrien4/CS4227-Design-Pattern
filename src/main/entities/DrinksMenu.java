package main.entities;

import java.util.ArrayList;

public class DrinksMenu extends MenuTemplate{
    ArrayList<FoodItem>  listOfDrinkItems;
    private String menuName;

    DrinksMenu(String menuName, ArrayList<FoodItem> listOfDrinkItems) {
        this.menuName = menuName;
        this.listOfDrinkItems = listOfDrinkItems;
    }

    @Override
    ArrayList<FoodItem> getList() {
        return listOfDrinkItems;
    }

    @Override
    String getMenuName() {
        return menuName;
    }
    @Override
    void printInfo() {
        System.out.println(menuName);
    }
}
