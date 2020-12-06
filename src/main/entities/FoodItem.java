package main.entities;

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
}
