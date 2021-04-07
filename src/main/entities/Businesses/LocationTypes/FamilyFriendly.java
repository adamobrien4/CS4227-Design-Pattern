package main.entities.Businesses.LocationTypes;

import org.bson.types.ObjectId;

public class FamilyFriendly extends Location{

    @Override
    public boolean customerVerification() {
        return true;
    }

    @Override
    public void review() {
        // TODO Auto-generated method stub
        
    }
    public FamilyFriendly(ObjectId id, String name, String genre,ObjectId menu){

        id=this.id;
        name=this.name;
        genre=this.genre;
        menu=this.menuid;

    }
    
}
