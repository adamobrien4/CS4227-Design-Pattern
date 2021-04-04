package main.entities.Businesses.LocationTypes;

import org.bson.types.ObjectId;

public class NightClub extends Location{

    public NightClub(ObjectId id, String name, String genre,ObjectId menu){

        id=this.id;
        name=this.name;
        genre=this.genre;
        menu=this.menuid;
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
