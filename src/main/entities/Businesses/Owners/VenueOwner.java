package main.entities.Businesses.Owners;

import org.bson.Document;
import org.bson.types.ObjectId;

import main.entities.Businesses.LocationTypes.Location;

public class VenueOwner extends Owner{
    private static final String TYPE = "VenueOwner";
    public VenueOwner(Location restaurant,ObjectId id, ObjectId restaurantId, String email, String password) {
        super(restaurant, id, restaurantId, email, password);
        
    }

    @Override
    public Owner fromDocument(Document document) {
        return new VenueOwner(restaurant,document.getObjectId("_id"), document.getObjectId("restaurant_id"),document.getString("email"), document.getString("password"));
    }

    @Override
    public String getTYPE() {
        return TYPE;
    }
    
}
