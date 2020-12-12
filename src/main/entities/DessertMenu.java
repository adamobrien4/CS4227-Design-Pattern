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
        System.out.println("This is the Dessert Menu");
        for (FoodItem item : listOfDessertItems)
            System.out.println(item.toString());
    }
    @Override
    ArrayList<FoodItem> getList() {
        return listOfDessertItems;
    }

}
