package main.entities.Businesses.LocationTypes;

import com.fasterxml.jackson.annotation.JsonTypeName;

import org.bson.types.ObjectId;
@JsonTypeName("Verified_Only")
public class VerifiedOnly extends Location{

    @Override
    public boolean customerVerification() {
        return false;
    }

    @Override
    public void review() {
        // TODO Auto-generated method stub
        
    }
    VerifiedOnly(){}
    public VerifiedOnly(ObjectId id, String name, String genre,ObjectId menu){
        super(id, name, genre, menu);
    }
    
}
