package main.entities;
import java.util.ArrayList;

public class DessertMenu extends MenuTemplate {
    ArrayList<FoodItem>  listOfDessertItems;
    private String menuName;

    DessertMenu(String menuName, ArrayList<FoodItem> listOfMainMItems) {
        this.menuName = menuName;
        this.listOfDessertItems = listOfMainMItems;
    }

    @Override
    String getMenuName() {
        return menuName;
    }
    @Override
    void printInfo() {
        System.out.println(menuName);
    }
    @Override
    ArrayList<FoodItem> getList() {
        return listOfDessertItems;
    }

}
