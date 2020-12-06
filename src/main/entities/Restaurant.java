package main.entities;

import org.bson.types.ObjectId;

public class Restaurant {
    private ObjectId id;
    // Ids might not be used, name instead
    private String name;
    // A Restaurant has only one menu at a time. therefore no ArrayList of menus
    private Menu menu;
    // The Type of restaurant
    private String genre;

    public Restaurant(ObjectId id, String name, Menu menu, String genre){
        this.id = id;
        this.name = name;
        this.menu = menu;
        this.genre = genre;
    }

    public Menu getMenu() {
        return menu;
    }
    public void setMenu(Menu menu) {
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
}

