package main.entities;

import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

public class Driver implements User {
    private ObjectId id;
    private String email;
    private String password;
    private List DriverOrders;
    private static final String TYPE = "driver";
    
    public Driver(ObjectId id, String email, String password){
        this.id=id;
        this.email=email;
        this.password=password;
    }

    @Override
    public String toJson() {
        // TODO Auto-generated method stub
        return null;
    }

    public String getEmail() {
        return this.email;
    }

    public String getType() {
        return Driver.TYPE;
    }

    public static Driver fromDocument(Document document) {
        return new Driver(document.getObjectId("_id"), document.getString("email"), document.getString("password"));
    }
    
    public void AcceptOrder(Order order){
        order.setDriver(id);
        DriverOrders.add(order);
    }
    
    public Order CompleteOrder(Order order){
        order.setIsCompleted();
        return order;
    }
    
    public List ViewOrders(){
        return DriverOrders;
    }
    public List viewAvailibleOrders(){
        return Order.PendingOrders;
    }
    
}
