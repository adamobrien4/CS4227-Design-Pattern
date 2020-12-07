package main.entities;

import java.util.ArrayList;

import org.bson.Document;
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

    public static Restaurant fromDocument(Document document) {

        ArrayList<FoodItem> mainCourse = new ArrayList<FoodItem>();
        ArrayList<FoodItem> dessert = new ArrayList<FoodItem>();
        ArrayList<FoodItem> sides = new ArrayList<FoodItem>();
        ArrayList<FoodItem> drinks = new ArrayList<FoodItem>();

        Document menu = document.get("menu", Document.class);
        if (menu.containsKey("main_course")) {
            ArrayList<Document> mc = menu.get("main_course", ArrayList.class);
            System.out.println(mc);

            for(Document d : mc) {
                mainCourse.add(FoodItem.fromDocument(d));
            }
        }
        if (menu.containsKey("desserts")) {
            ArrayList<Document> dc = menu.get("desserts", ArrayList.class);
            System.out.println(dc);

            for(Document d : dc) {
                dessert.add(FoodItem.fromDocument(d));
            }
        }
        if (menu.containsKey("sides")) {
            ArrayList<Document> sc = menu.get("sides", ArrayList.class);
            System.out.println(sc);

            for(Document d : sc) {
                sides.add(FoodItem.fromDocument(d));
            }
        }
        if (menu.containsKey("drinks")) {
            ArrayList<Document> dks = menu.get("drinks", ArrayList.class);
            System.out.println(dks);

            for(Document d : dks) {
                drinks.add(FoodItem.fromDocument(d));
            }
        }

        return new Restaurant(document.get("_id", ObjectId.class), document.get("name", String.class), new Menu("Menu 1", mainCourse, dessert, sides, drinks), document.get("genre", String.class));
    }
}

