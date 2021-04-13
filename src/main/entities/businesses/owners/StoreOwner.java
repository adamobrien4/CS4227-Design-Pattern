package main.entities.businesses.owners;

import org.bson.Document;
import org.bson.types.ObjectId;

import main.entities.EventItems;
import main.entities.Menu;
import main.entities.businesses.locationTypes.Location;

public class StoreOwner extends Owner{

    private static final String TYPE = "StoreOwner";

    public StoreOwner(Location restaurant, ObjectId id, ObjectId restaurantId, String email, String password) {
        super(restaurant, id, restaurantId, email, password);
    }

    @Override
    public Owner fromDocument(Document document) {
        return new StoreOwner(restaurant,document.getObjectId("_id"), document.getObjectId("restaurant_id"),document.getString("email"), document.getString("password"));
    }

    @Override
    public String getTYPE() {
        return TYPE;
    }

    @Override
    public EventItems getEventList() {
        return restaurant.getEventList();
    }

    @Override
    public Menu getMenu() {
        return restaurant.getMenu();
    }

    
}
