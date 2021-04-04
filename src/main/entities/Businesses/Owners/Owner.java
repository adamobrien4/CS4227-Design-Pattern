package main.entities.Businesses.Owners;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.bson.Document;
import org.bson.types.ObjectId;

import main.entities.Menu;
import main.entities.Businesses.LocationTypes.Location;


public abstract class Owner{
    public Location restaurant;
    

    @JsonProperty("_id")
    private ObjectId id;

    @JsonProperty("restaurant")
    private ObjectId restaurantId;

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

    public Owner(Location restaurant,ObjectId id, ObjectId restaurantId, String email, String password)
    {
        restaurant=this.restaurant;
        this.id = id;
        this.restaurantId = restaurantId;
        this.email = email;
        this.password = password;
    }
    public boolean customerVerification(){

        return restaurant.customerVerification();

    }
    public void review(){
        restaurant.review();
    }

    public Menu getMenu() {
        return restaurant.getMenu();
    }
    public void setMenu(ObjectId menu) {
        restaurant.setMenu(menu);
    }
    public String getName() {
        return restaurant.getName();
    }
    public String getGenre() {
        return restaurant.getGenre();
    }
    public ObjectId getRestId() {
        return restaurant.getId();
    }
    public String toString(){
        return "Name: "+getName()+"\nGenre: "+getGenre();
    }


    public ObjectId getId() {
        return this.id;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return password;
    }
    public Location getLocation(){
        return restaurant;
    }

    public abstract Owner fromDocument(Document document);
    public abstract String getTYPE();
    
}

