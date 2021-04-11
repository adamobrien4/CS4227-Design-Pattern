package main.entities.Businesses.LocationTypes;

import com.fasterxml.jackson.annotation.JsonTypeName;

import org.bson.types.ObjectId;
@JsonTypeName("Family_Friendly")
public class FamilyFriendly extends Location{

    public FamilyFriendly(){}

    @Override
    public boolean customerVerification() {
        return true;
    }

    @Override
    public void review() {
        // TODO Auto-generated method stub
        
    }
    public FamilyFriendly(ObjectId id, String name, String genre,ObjectId menu){
        super(id, name, genre, menu);
    }
    
}
