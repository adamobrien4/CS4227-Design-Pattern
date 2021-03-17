package main.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import main.entities.users.Customer;
import org.bson.Document;
import org.bson.types.ObjectId;

import main.Globals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Order {
    @JsonProperty("_id")
    private ObjectId id = new ObjectId();
    @JsonProperty("driver")
    private ObjectId driver = new ObjectId();
    @JsonProperty("restaurant")
    private ObjectId restaurant = new ObjectId();
    @JsonProperty("status")
    private String status;
    @JsonProperty("address")
    private String address;
    @JsonProperty("foodCost")
    private double foodCost;
    @JsonProperty("deliveryCost")
    private double deliveryCost;
    @JsonProperty("totalCost")
    private double totalCost;
    @JsonProperty("discountCode")
    private String discountCode;
    @JsonProperty("discountAmount")
    private double discountAmount;
    @JsonProperty("orderItems")
    private ArrayList<String> orderItems;

    // Additional Fields TODO: Are these fields necessary

    private String[] Food;
    private boolean IsCompleted;
    public static List PendingOrders;

    private Order() {}

    public Order(double totalCost, double foodCost, double deliveryCost, ArrayList<String> orderItems, String address) {
        this.totalCost = totalCost;
        this.deliveryCost = deliveryCost;
        this.foodCost = foodCost;
        this.orderItems = orderItems;
        this.address = address;
    }

    public Order(double totalCost, double foodCost, double deliveryCost, String discountCode, double discountAmount, ArrayList<String> orderItems, String address) {
        this.totalCost = totalCost;
        this.discountCode = discountCode;
        this.discountAmount = discountAmount;
        this.foodCost = foodCost;
        this.deliveryCost = deliveryCost;
        this.orderItems = orderItems;
        this.address = address;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getDriver() {
        return driver;
    }

    public void setDriver(ObjectId driver) {
        this.driver = driver;
    }

    public ObjectId getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(ObjectId restaurant) {
        this.restaurant = restaurant;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getFoodCost() {
        return foodCost;
    }

    public void setFoodCost(double foodCost) {
        this.foodCost = foodCost;
    }

    public double getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(double deliveryCost) {
        this.deliveryCost = deliveryCost;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public ArrayList<String> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(ArrayList<String> orderItems) {
        this.orderItems = orderItems;
    }

    // TODO: Remove
    public void setIsCompeted() {
        this.IsCompleted = true;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", driver=" + driver +
                ", restaurant=" + restaurant +
                ", status='" + status + '\'' +
                ", address='" + address + '\'' +
                ", foodCost=" + foodCost +
                ", deliveryCost=" + deliveryCost +
                ", totalCost=" + totalCost +
                ", discountCode='" + discountCode + '\'' +
                ", discountAmount=" + discountAmount +
                ", orderItems=" + orderItems +
                '}';
    }
}

