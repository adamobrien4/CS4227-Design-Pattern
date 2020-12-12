package main.entities;

import java.util.ArrayList;
import java.util.Arrays;

import org.bson.Document;

public class FoodItem {
    private String name;
    private boolean hasAllergens = false;
    private String[] allergens;
    private double price;

    public FoodItem (String n, double p) {
        this.name = n;
        this.price = p;
    }

    public FoodItem (String n, String[] a, double p) {
        this.name = n;
        this.hasAllergens = true;
        this.allergens = a;
        this.price = p;
    }

    public String getName() {
        return this.name;
    }

    public String[] getAllergens() {
        return this.allergens;
    }

    public double getPrice() {
        return this.price;
    }

    public boolean hasAllergens() {
        return hasAllergens;
    }

    public static FoodItem fromDocument(Document document) {
        if (document.containsKey("allergens")) {
            ArrayList<String> alg = document.get("allergens", ArrayList.class);
            return new FoodItem(document.get("name", String.class), alg.toArray(new String[0]), document.get("price", Double.class));
        } else {
            return new FoodItem(document.get("name", String.class), document.get("price", Double.class));
        }
        
    }

    @Override
    public String toString() {
        return "FoodItem{" +
                "name='" + name + '\'' +
                ", hasAllergens=" + hasAllergens +
                ", allergens=" + Arrays.toString(allergens) +
                ", price=" + price +
                '}';
    }
}
