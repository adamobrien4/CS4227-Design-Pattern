package main.presentation_layer.owner_menu;

import main.entities.EventItems;
import main.entities.FoodItem;
import main.entities.Menu;
import main.entities.Event;
import main.entities.businesses.LocationTypes.Location;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;

import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import main.Globals;
import main.entities.businesses.Owners.Owner;
import main.dao.EventItemsDaoImpl;
import main.dao.MenuDaoImpl;

import java.util.ArrayList;

public class OwnerMenuController {

    @FXML
    private Text SplitText;
    @FXML
    private VBox itemsBox;
    @FXML 
    private TextField eventName;
    @FXML
    private TextField eventPrice;
    @FXML
    private TextField foodName;
    @FXML
    private TextField foodAlergens;
    @FXML
    private TextField foodPrice;

    private Owner loggedInOwner;
    private ArrayList<Event> items;
    private ArrayList<FoodItem> food;

    private MenuDaoImpl menuDao;
    private EventItemsDaoImpl eventItemsDao;
    @FXML
    private void handleCreateFood(ActionEvent evt){
        Menu menu = loggedInOwner.getMenu();
        if(foodAlergens.getText().equals("")){
            menu.addFoodItem(new FoodItem.Builder<>().name((foodName.getText())).price(Double.parseDouble(foodPrice.getText())).build());
        }
        else{
            
        }

        foodName.clear();
        foodAlergens.clear();
        foodPrice.clear();
        evt.consume();
        updateList();
        
    }

    @FXML
    private void handleCreateEvent(ActionEvent evt){
        EventItems eventItems = loggedInOwner.getEventList();
        eventItems.addEventItem(new Event.Builder<>().name(eventName.getText()).price(Double.parseDouble(eventPrice.getText())).build());
        eventName.clear();
        eventPrice.clear();
        evt.consume();

        updateList();
        
    }
    @FXML
    public void initialize()
    {
        eventItemsDao = new EventItemsDaoImpl();
        menuDao = new MenuDaoImpl();
        loggedInOwner = (Owner)Globals.getLoggedInUser();
        Location l = loggedInOwner.getLocation();

        EventItems eventItems = eventItemsDao.get(l.getMenuId().toString());
        Menu menuitems = menuDao.get(l.getMenuId().toString());
        food= menuitems.getListOfMainCoursesItems();
        items = eventItems.getListOfMainCoursesItems();
        SplitText.setText("Hello "+loggedInOwner.getName()+"!");
        itemsBox.getChildren().add(SplitText);

        SplitText.setText("-------Event Listings-------");
        itemsBox.getChildren().add(SplitText);

        for (int i = 0; i < items.size(); i++) {
            // Add food item to drinks tab
            double y = 27.0 + (33.0 * i);
            Node[] foodListing = makeEventListing(items.get(i), y);
            itemsBox.getChildren().addAll(foodListing[0], foodListing[1]);
        }
        SplitText.setText("-------Food Listings-------");
        itemsBox.getChildren().add(SplitText);

        for (int i = 0; i < food.size(); i++) {
            // Add food item to drinks tab
            double y = (27.0+(33.0*items.size()))+50+(27.0 + (33.0 * i));
            Node[] foodListing = makeFoodListing(items.get(i), y);
            itemsBox.getChildren().addAll(foodListing[0], foodListing[1]);
        }


    }

    private Node[] makeFoodListing(Event item, double y) {
        Text name = new Text();
        name.setLayoutX(14.0);
        name.setLayoutY(y);
        name.setText(item.getName());

        Text prc = new Text();
        prc.setLayoutX(552.0);
        prc.setLayoutY(y);
        prc.setText("€" + item.getPrice());

        return new Node[] { name, prc,};
    }
    private Node[] makeEventListing(Event item, double y) {
        Text name = new Text();
        name.setLayoutX(14.0);
        name.setLayoutY(y);
        name.setText(item.getName());

        Text prc = new Text();
        prc.setLayoutX(552.0);
        prc.setLayoutY(y);
        prc.setText("€" + item.getPrice());

        return new Node[] { name, prc,};
    }
    private void updateList(){

    }
    
}
