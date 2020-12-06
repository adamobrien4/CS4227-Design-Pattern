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

    private double totalCost;
    private double foodCost;
    private double deliveryCost;

    private String discountCode;
    private double discountAmount;

    private String[] orderItems;

    private boolean IsCompleted;
    public static List PendingOrders;

    public Order(ObjectId id){
        this.id=id;
        this.driver=null;
        this.IsCompleted=false;
        PendingOrders.add(id);
    }

    public Order(double totalCost, double deliveryCost, String[] orderItems) {
        this.totalCost = totalCost;
        this.deliveryCost = deliveryCost;
        this.orderItems = orderItems;
    }

    public Order(double totalCost, String discountCode, double discountAmount, double deliveryCost, String[] orderItems) {
        this.totalCost = totalCost;
        this.discountCode = discountCode;
        this.discountAmount = discountAmount;
        this.deliveryCost = deliveryCost;
        this.orderItems = orderItems;
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
            .append("restaurant_id", Globals.getRestaurant())
            .append("total_cost", totalCost)
            .append("delivery_cost", deliveryCost)
            .append("order_items", Arrays.asList(orderItems))
            .append("status", "pending_payment");

        if (discountAmount > 0) {
            doc.append("discount_code", discountCode)
                .append("discount_amount", discountAmount);
        }
    
        return doc;
    }

}

