package main.entities;

import java.util.ArrayList;

public class TemplateMenuTest {
    public static void main(String[] args) {

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


        MenuTemplate menu = new DrinksMenu("drinks menu", listOfDrinkItems);
        System.out.println(menu.getListOfItems());

        menu = new MainMenu("main menu",listOfMainCoursesItems);
        System.out.println(menu.getListOfItems());

        menu = new SideMenu("side menu",listOfSideItems);
        System.out.println(menu.getListOfItems());

        menu = new DessertMenu("dessert menu",listOfDesertItems);
        System.out.println(menu.getListOfItems());


    }
}
