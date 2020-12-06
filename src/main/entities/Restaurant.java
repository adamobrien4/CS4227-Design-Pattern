package main.entities;

public class Restaurant {
    // Ids might not be used, name instead
    private String name;
    // A Restaurant has only one menu at a time. therefore no ArrayList of menus
    private Menu menu;

    public Restaurant(String name, Menu menu){
        this.name = name;
        this.menu = menu;
    }

    public Menu getMenu() {
        return menu;
    }
    public void overwriteMenu(Menu menu) {
        this.menu = menu;
    }
    public String getName() {
        return name;
    }
}

