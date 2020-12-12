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
        System.out.println("This is the Side Menu");
        for (FoodItem item : listOfSideItems)
            System.out.println(item.toString());
    }
}
