package main.entities;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.bson.types.ObjectId;

@JsonPropertyOrder({"_id", "name", "menu", "genre"})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Restaurant {
    @JsonProperty("_id")
    private ObjectId id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("menu")
    private ObjectId menu;
    @JsonProperty("genre")
    private String genre;

    private Restaurant(){}

    public Restaurant(ObjectId id, String name, ObjectId menu, String genre){
        this.id = id;
        this.name = name;
        this.menu = menu;
        this.genre = genre;
    }

    public ObjectId getMenu() {
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

//    public static Restaurant fromDocument(Document document) {
//
//        ArrayList<FoodItem> mainCourse = new ArrayList<FoodItem>();
//        ArrayList<FoodItem> dessert = new ArrayList<FoodItem>();
//        ArrayList<FoodItem> sides = new ArrayList<FoodItem>();
//        ArrayList<FoodItem> drinks = new ArrayList<FoodItem>();
//
//        Document menu = document.get("menu", Document.class);
//        if (menu.containsKey("main_course")) {
//            ArrayList<Document> mc = menu.get("main_course", ArrayList.class);
//            System.out.println(mc);
//
//            for(Document d : mc) {
//                mainCourse.add(FoodItem.fromDocument(d));
//            }
//        }
//        if (menu.containsKey("dessert")) {
//            ArrayList<Document> dc = menu.get("dessert", ArrayList.class);
//            System.out.println(dc);
//
//            for(Document d : dc) {
//                dessert.add(FoodItem.fromDocument(d));
//            }
//        }
//        if (menu.containsKey("sides")) {
//            ArrayList<Document> sc = menu.get("sides", ArrayList.class);
//            System.out.println(sc);
//
//            for(Document d : sc) {
//                sides.add(FoodItem.fromDocument(d));
//            }
//        }
//        if (menu.containsKey("drinks")) {
//            ArrayList<Document> dks = menu.get("drinks", ArrayList.class);
//            System.out.println(dks);
//
//            for(Document d : dks) {
//                drinks.add(FoodItem.fromDocument(d));
//            }
//        }
//
//        return new Restaurant(document.get("_id", ObjectId.class), document.get("name", String.class), new Menu("Menu 1", mainCourse, dessert, sides, drinks), document.get("genre", String.class));
//    }
}

