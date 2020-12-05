package main.entities;

import org.bson.Document;
import org.bson.types.ObjectId;
import java.util.List;

public class Order {
    
    private ObjectId id;
    private ObjectId driver;
    private boolean IsCompleted;
    static public List PendingOrders;

    public Order(ObjectId id){
        this.id=id;
        this.driver=null;
        this.IsCompleted=false;
        PendingOrders.add(id);
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

}

