package main.entities;

import java.util.List;

import org.bson.types.ObjectId;

public class Driver  implements User{
    private ObjectId id;
    private String email;
    private String password;
    private List DriverOrders;
    private ObjectId RestaurantId;
    
    public Driver(ObjectId id, String email, String password, ObjectId RestaurantId){
        this.id=id;
        this.email=email;
        this.password=password;
        DriverOrders=null;
        this.RestaurantId= RestaurantId;
    }

    @Override
    public String toJson() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void fromJson() {
        // TODO Auto-generated method stub

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
