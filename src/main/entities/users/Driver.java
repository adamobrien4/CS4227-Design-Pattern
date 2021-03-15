package main.entities.users;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import main.entities.Order;
import org.bson.Document;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

@JsonPropertyOrder({"_id", "email", "password"})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Driver implements User {
    @JsonProperty("_id")
    private ObjectId id;

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
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
    public ObjectId getId() {
        return id;
    }

    @Override
    public String getType() {
        return Driver.TYPE;
    }
    
    public void AcceptOrder(Order order){
        order.setDriver(id);
        driverOrders.add(order);
    }

    // TODO: Fix this / remove the way the orders are complted
    
    public Order CompleteOrder(Order order){
        //order.setIsCompleted();
        return order;
    }
    
    public List ViewOrders(){
        return driverOrders;
    }
    public List viewAvailibleOrders(){
        return Order.PendingOrders;
    }
    
}
