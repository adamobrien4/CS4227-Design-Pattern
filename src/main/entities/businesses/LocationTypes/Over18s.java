package main.entities.businesses.LocationTypes;

import com.fasterxml.jackson.annotation.JsonTypeName;

import org.bson.types.ObjectId;
@JsonTypeName("Over_18")
public class Over18s extends Location{
    
    public Over18s(){}

    public Over18s(ObjectId id, String name, String genre,ObjectId menu){
        super(id, name, genre, menu);
    }

   @Override
    public boolean customerVerification() {
        System.out.println("Checking Age...");
        return true;
    }

    @Override
    public void review() {
        System.out.println("Leaving Reivew");
        
    }
    
}
