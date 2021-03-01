package test.entities;

import java.util.ArrayList;

import org.junit.Test;

import main.entities.DessertMenu;
import main.entities.DrinksMenu;
import main.entities.FoodItem;
import main.entities.MainMenu;
import main.entities.MenuTemplate;
import main.entities.SideMenu;

public class TemplateMenuTest {
    
    @Test
    public void test() {
        String[] allergens1 = {"gluten","nuts"};
        String[] allergens2 = {"soya","milk"};

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


        /*MenuTemplate menu = new DrinksMenu("drinks menu", listOfDrinkItems);
        menu.printInfo();

        menu = new MainMenu("main menu",listOfMainCoursesItems);
        menu.printInfo();

        menu = new SideMenu("side menu",listOfSideItems);
        menu.printInfo();

        menu = new DessertMenu("dessert menu",listOfDesertItems);
        menu.printInfo();*/
    }

}
