package main.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.bson.types.ObjectId;
import java.util.ArrayList;



public class EventItems {

    @JsonPropertyOrder({"_id", "name","booking", "location"})
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonProperty("_id")
    public ObjectId id;
    @JsonProperty("name")
    public String menuName;
    @JsonProperty("main_course")
    private ArrayList<Event> listOfEvents;
    @JsonProperty("location")
    private ObjectId restaurant;
    
    public EventItems(String menuName, ArrayList<Event> listOfEvents){
            this.menuName = menuName;
            this.listOfEvents = listOfEvents;
    }
    public String getMenuName() {
        return menuName;
    }
    public ArrayList<Event> getListOfMainCoursesItems() {
        return listOfEvents;
    }
    public String toString() {
        return this.getMenuName();
    }
}
