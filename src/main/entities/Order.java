package main.entities;

import org.bson.Document;
import org.bson.types.ObjectId;

import main.Globals;

import java.util.Arrays;
import java.util.List;

public class Order {
    
    private ObjectId id;
    private ObjectId driver;
    private ObjectId restaurant;
    private String status;
    private String address;

    private double totalCost;
    private double foodCost;
    private double deliveryCost;

    private String discountCode;
    private double discountAmount;
    private String[] Food;
    private String[] orderItems;

    private boolean IsCompleted;
    public static List PendingOrders;

    public static class Builder <T extends Builder<T>>{
    private ObjectId id;
    private ObjectId driver;
    private ObjectId restaurant;
    private String status;
    private String address;

    private double totalCost;
    private double foodCost;
    private double deliveryCost;

    private String discountCode;
    private double discountAmount;
    private String[] Food;
    private String[] orderItems;

    private boolean IsCompleted;
    public static List PendingOrders;

    public Builder(){}

    public T id(ObjectId val){
        id=val;
        return (T)this;
    }
    public T driver(ObjectId val){
        driver=val;
        return (T)this;
    }
    public T restaurant(ObjectId val){
        restaurant=val;
        return (T)this;
    }
    public T status (String val){
        status=val;
        return (T)this;
    }
    public T address(String val){
        status=val;
        return (T)this;
    }
    public T totalCost(double val){
        totalCost=val;
        return (T)this;
    }
    public T foodCost(double val){
        foodCost=val;
        return (T)this;
    }
    public T deliveryCost(double val){
        deliveryCost=val;
        return (T)this;
    }
    public T discountCode(String val){
        discountCode=val;
        return (T)this;
    }
    public T discountAmount(double val){
        discountAmount=val;
        return (T)this;
    }
    public T Food(String[] val){
        Food=val;
        return (T)this;
    }
    public T orderItems(String[] val){
        orderItems=val;
        return (T)this;
    }
    public Order build(){
        return new Order(this);
    }
    }
    public Order(Builder<?> builder){
        id=builder.id;
        Food=builder.Food;
        orderItems=builder.orderItems;
        discountAmount=builder.discountAmount;
        discountCode=builder.discountCode;
        deliveryCost=builder.deliveryCost;
        foodCost=builder.foodCost;
        totalCost=builder.totalCost;
        address=builder.address;
        status=builder.status;
        restaurant=builder.restaurant;
        driver=builder.driver;
    }
    public ObjectId getId() {
        return id;
    }



    public void setIsCompleted() {
        this.IsCompleted = true;
    }


    public Document toDocument() {
        Customer loggedInUser = (Customer)Globals.getLoggedInUser();
        Document doc = new Document("customer_id", loggedInUser.getId())
            .append("restaurant_id", Globals.getRestaurant().getId())
            .append("total_cost", totalCost)
            .append("delivery_cost", deliveryCost)
            .append("order_items", Arrays.asList(orderItems))
            .append("status", "pending_payment")
            .append("address", address);

        if (discountAmount > 0) {
            doc.append("discount_code", discountCode)
                .append("discount_amount", discountAmount);
        }
    
        return doc;
    }
	public void setDriver(ObjectId id) {
        driver=id;
	}

}

