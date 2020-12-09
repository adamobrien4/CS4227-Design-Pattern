package main.entities;

import java.util.ArrayList;
// Menu has a Composition('Has a') relationship with Restaurant
public class Menu {
    public String menuName;
    private ArrayList<FoodItem> listOfMainCoursesItems;
    private ArrayList<FoodItem> listOfDessertItems;
    private ArrayList<FoodItem> listOfSideItems;
    private ArrayList<FoodItem> listOfDrinkItems;

    // Default menu
    public Menu() {
        String[] allergens1 = {"gluten","nuts"};
        String[] allergens2 = {"soya","milk"};

        FoodItem item1 = new FoodItem("cheese",allergens1,4.50);
        FoodItem item2 = new FoodItem("burger",allergens2,7.50);
        FoodItem item3 = new FoodItem("ham",allergens2,5.50);
        FoodItem item4 = new FoodItem("salad",allergens1,4.50);
        FoodItem item5 = new FoodItem("ice cream",allergens1,4.50);
        FoodItem item6 = new FoodItem("cherry",allergens2,1.50);
        FoodItem item7 = new FoodItem("milk",allergens1,1.50);
        FoodItem item8 = new FoodItem("coffee",allergens2,1.20);


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
