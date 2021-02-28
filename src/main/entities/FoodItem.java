package main.entities;

import java.util.ArrayList;
import java.util.Arrays;
import org.bson.Document;

public class FoodItem {
    private String name;
    private boolean hasAllergens = false;
    private String[] allergens;
    private double price;

    public static class Builder<T extends Builder<T>> {
        private String name;
        private boolean hasAllergens = false;
        private String[] allergens;
        private double price;

        public Builder(){}

        public T name(String val) {
            name = val;
            return (T) this;
        }
        public T allergens(String[] val) {
            hasAllergens=true;
            allergens = val;
            return (T) this;
        }
        public T price(double val) {
            price = val;
            return (T) this;
        }
        public FoodItem build(){
            return new FoodItem(this);
        }



    }
    public FoodItem(Builder<?> builder) {
        name=builder.name;
        hasAllergens=builder.hasAllergens;
        allergens=builder.allergens;
        price=builder.price;
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
            return new FoodItem.Builder<>().name(document.get("name", String.class)).allergens(alg.toArray(new String[0])).price(document.get("price", Double.class)).build();
        } else {
            return new FoodItem.Builder<>().name(document.get("name", String.class)).price(document.get("price", Double.class)).build();
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
