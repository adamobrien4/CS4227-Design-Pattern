package main.entities.Businesses.LocationTypes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import org.bson.types.ObjectId;

import main.entities.Menu;
@JsonPropertyOrder({"_id", "name", "menu", "genre"})
@JsonIgnoreProperties(ignoreUnknown = true)
public

abstract class Location {

    @JsonProperty("_id")
    public ObjectId id;
    @JsonProperty("name")
    public String name;
    @JsonProperty("menu")
    public ObjectId menuid;
    @JsonProperty("genre")
    public String genre;

    public Menu getMenu() {
        return null;
    }
    public ObjectId getMenuId(){
        return menuid;
    }
    public void setMenu(ObjectId menu) {
        this.menuid = menu;
    }
    public String getName() {
        return name;
    }
    public String getGenre() {
        return this.genre;
    }
    public ObjectId getId() {
        return this.id;
    }
    public String toString(){
        return "Name: "+name+"\nGenre: "+genre;
    }

    public abstract boolean customerVerification();
    public abstract void review();
    
}
