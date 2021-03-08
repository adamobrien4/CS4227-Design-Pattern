package main.entities.users;

import java.util.List;

import main.entities.Order;
import org.bson.Document;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

public class Driver implements User {
    private ObjectId id;
    private String email;
    private String password;
    private List driverOrders;
    private static final String TYPE = "driver";
    
    public Driver(ObjectId id, String email, String password){
        this.id=id;
        this.email=email;
        this.password=password;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public String getType() {
        return Driver.TYPE;
    }
    
    public void AcceptOrder(Order order){
        order.setDriver(id);
        driverOrders.add(order);
    }
    
    public Order CompleteOrder(Order order){
        order.setIsCompleted();
        return order;
    }
    
    public List ViewOrders(){
        return driverOrders;
    }
    public List viewAvailibleOrders(){
        return Order.PendingOrders;
    }
    
}
