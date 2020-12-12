package main.entities;
import java.util.ArrayList;

public class MainMenu extends MenuTemplate {
    ArrayList<FoodItem>  listOfMainMItems;
    private String menuName;

    MainMenu(String menuName, ArrayList<FoodItem> listOfMainMItems) {
        this.menuName = menuName;
        this.listOfMainMItems = listOfMainMItems;
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
        return listOfMainMItems;
    }

}
