package main.entities;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import main.entities.FoodItem.*;
import org.bson.types.ObjectId;

// Menu has a Composition('Has a') relationship with Restaurant
@JsonPropertyOrder({"_id", "name", "main_course", "dessert", "sides", "drinks", "restaurant"})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Menu {
    @JsonProperty("_id")
    public ObjectId id;
    @JsonProperty("name")
    public String menuName;
    @JsonProperty("main_course")
    private ArrayList<FoodItem> listOfMainCoursesItems;
    @JsonProperty("dessert")
    private ArrayList<FoodItem> listOfDessertItems;
    @JsonProperty("sides")
    private ArrayList<FoodItem> listOfSideItems;
    @JsonProperty("drinks")
    private ArrayList<FoodItem> listOfDrinkItems;
    @JsonProperty("restaurant")
    private ObjectId restaurant;

    // Default menu
    public Menu() {
        ArrayList<String> allergens1 = new ArrayList<>();
        ArrayList<String> allergens2 = new ArrayList<>();

        allergens1.add("gluten");
        allergens1.add("nuts");

        allergens2.add("soya");
        allergens2.add("milk");

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

    public String toString() {
        return this.getMenuName();
    }
}
