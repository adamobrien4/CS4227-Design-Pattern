package main.entities.Businesses.LocationTypes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.bson.types.ObjectId;

import main.dao.EventItemsDaoImpl;
import main.dao.MenuDaoImpl;
import main.entities.EventItems;
import main.entities.Menu;
@JsonPropertyOrder({"_id", "name", "menu", "genre"})
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({@Type (value = FamilyFriendly.class, name="Family_Friendly"), @Type(value = Over18s.class, name="Over_18"),@Type(value = VerifiedOnly.class, name = "Verfied_Only")})


public abstract  class Location {


    @JsonProperty("_id")
    public ObjectId id;
    @JsonProperty("name")
    public String name;
    @JsonProperty("menu")
    public ObjectId menu;
    @JsonProperty("genre")
    public String genre;
    public EventItems eventitemid;
    MenuDaoImpl Menudao;
    EventItemsDaoImpl Eventdao; 

    @JsonCreator
    public Location(@JsonProperty("_id") ObjectId id,@JsonProperty("name") String name,@JsonProperty("genre")String genre,@JsonProperty("menu")ObjectId menu){

        id=this.id;
        name=this.name;
        genre=this.genre;
        menu=this.menu;

    }
    public Location(){}

    public Menu getMenu() {
        return Menudao.get(menu.toString());
    }
    
    public EventItems getEventList(){
        return Eventdao.get(eventitemid.toString());
    }
    
    public ObjectId getMenuId(){
        return menu;
    }
    public void setMenu(ObjectId menu) {
        this.menu = menu;
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
