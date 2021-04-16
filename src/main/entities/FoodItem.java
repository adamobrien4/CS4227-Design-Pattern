package main.entities;

import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;

public class FoodItem {
    @JsonProperty("_id")
    private ObjectId id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("allergens")
    private ArrayList<String> allergens = new ArrayList<>();
    @JsonProperty("price")
    private double price;

    public FoodItem(){}

    public static class Builder<T extends Builder<T>> {
        private String name;
        private ArrayList<String> allergens;
        private double price;

        public Builder(){}

        public T name(String val) {
            name = val;
            return (T) this;
        }
        public T allergens(ArrayList<String> val) {
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
        allergens=builder.allergens;
        price=builder.price;
	}

	public String getName() {
        return this.name;
    }

    public ArrayList<String> getAllergens() {
        return this.allergens;
    }

    public double getPrice() {
        return this.price;
    }

    public boolean hasAllergens() {
        return allergens != null;
    }

    @Override
    public String toString() {
        if(allergens != null) {

            return "FoodItem{" +
                    "name='" + name + '\'' +
                    ", hasAllergens=" + this.hasAllergens() +
                    ", allergens=" + allergens.toString() +
                    ", price=" + price +
                    '}';
        }
        else {
            return "FoodItem{" +
                    "name='" + name + '\'' +
                    ", hasAllergens=" + this.hasAllergens() +
                    ", allergens=" + "none" +
                    ", price=" + price +
                    '}';
        }
    }
}
