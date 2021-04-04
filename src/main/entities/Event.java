package main.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;

public class Event {

    @JsonProperty("_id")
    private ObjectId id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("price")
    private double price;
    
    public Event (){}

    public Event(Builder<?> builder) {
        name=builder.name;
        price=builder.price;
	}
    public String getName(){
        return name;
    }
    public double getPrice(){
        return price;
    }
    public static class Builder<T extends Builder<T>> {
        private String name;
        private double price;

        public Builder(){}

        public T name(String val) {
            name = val;
            return (T) this;
        }

        public T price(double val) {
            price = val;
            return (T) this;
        }
        public Event build(){
            return new Event(this);
        }
    }


}
