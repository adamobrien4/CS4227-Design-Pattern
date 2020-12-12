package main.entities;

import java.util.ArrayList;

public class SideMenu extends MenuTemplate{
    ArrayList<FoodItem>  listOfSideItems;
    private String menuName;

    SideMenu(String menuName, ArrayList<FoodItem> listOfDrinkItems) {
        this.menuName = menuName;
        this.listOfSideItems = listOfDrinkItems;
    }

    @Override
    ArrayList<FoodItem> getList() {
        return listOfSideItems;
    }

    @Override
    String getMenuName() {
        return menuName;
    }
    @Override
    void printInfo() {
        System.out.println("Side Menu");
        System.out.println(menuName);
    }
}
