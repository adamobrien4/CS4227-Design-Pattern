package main.entities;

import main.entities.users.Customer;
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

    public Order(ObjectId id,String[] food, String address){
        this.id=id;
        this.driver=null;
        this.IsCompleted=false;
        PendingOrders.add(id);
        this.Food=food;
    }
    public ObjectId getId() {
        return id;
    }

    public Order(double totalCost, double deliveryCost, String[] orderItems, String address) {
        this.totalCost = totalCost;
        this.deliveryCost = deliveryCost;
        this.orderItems = orderItems;
        this.address = address;
    }

    public Order(double totalCost, String discountCode, double discountAmount, double deliveryCost, String[] orderItems, String address) {
        this.totalCost = totalCost;
        this.discountCode = discountCode;
        this.discountAmount = discountAmount;
        this.deliveryCost = deliveryCost;
        this.orderItems = orderItems;
        this.address = address;
    }

    public void setIsCompleted() {
        this.IsCompleted = true;
    }

    public void setDriver(ObjectId driver){
        for (int i=0; i<PendingOrders.size();i++){
            if(PendingOrders.get(i)==id){
                PendingOrders.remove(i);
                this.driver=driver;
                break;
            }
        }
        System.out.println("Failed to assign Driver");
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

}

