package main.entities;

import java.util.ArrayList;
import main.entities.FoodItem.*;

// Menu has a Composition('Has a') relationship with Restaurant
public class Menu {
    public String menuName;
    private ArrayList<FoodItem> listOfMainCoursesItems;
    private ArrayList<FoodItem> listOfDessertItems;
    private ArrayList<FoodItem> listOfSideItems;
    private ArrayList<FoodItem> listOfDrinkItems;

    // Default menu
    public Menu() {
        String[] allergens1 = { "gluten", "nuts" };
        String[] allergens2 = { "soya", "milk" };

        FoodItem item1 = new FoodItem.Builder<>().name("cheese").allergens(allergens1).price(4.50).build();
        FoodItem item2 = new FoodItem.Builder<>().name("Burger").allergens(allergens1).price(7.50).build();
        FoodItem item3 = new FoodItem.Builder<>().name("ham").allergens(allergens2).price(5.50).build();
        FoodItem item4 = new FoodItem.Builder<>().name("salad").allergens(allergens1).price(4.50).build();
        FoodItem item5 = new FoodItem.Builder<>().name("ice cream").allergens(allergens1).price(4.50).build();
        FoodItem item6 = new FoodItem.Builder<>().name("cherry").allergens(allergens2).price(1.50).build();
        FoodItem item7 = new FoodItem.Builder<>().name("milk").allergens(allergens2).price(1.50).build();
        FoodItem item8 = new FoodItem.Builder<>().name("coffee").allergens(allergens2).price(1.20).build();


        ArrayList<FoodItem> listOfMainCoursesItems = new ArrayList<>();
        listOfMainCoursesItems.add(item1);
        listOfMainCoursesItems.add(item2);

        ArrayList<FoodItem> listOfDesertItems = new ArrayList<>();
        listOfDesertItems.add(item3);
        listOfDesertItems.add(item4);

        ArrayList<FoodItem>  listOfSideItems = new ArrayList<>();
        listOfSideItems.add(item5);
        listOfSideItems.add(item6);

        ArrayList<FoodItem>  listOfDrinkItems = new ArrayList<>();
        listOfDrinkItems.add(item7);
        listOfDrinkItems.add(item8);

        this.menuName = "Sample Menu";
        this.listOfMainCoursesItems = listOfMainCoursesItems;
        this.listOfDessertItems = listOfDesertItems;
        this.listOfSideItems = listOfSideItems;
        this.listOfDrinkItems = listOfDrinkItems;

    }


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
