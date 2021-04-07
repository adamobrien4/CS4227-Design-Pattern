package main.presentation_layer.create_menu_list;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.dao.EventDaoImpl;
import main.dao.EventItemsDaoImpl;
import main.dao.MenuDaoImpl;
import main.entities.Event;
import main.entities.EventItems;
import main.entities.FoodItem;
import main.entities.Menu;


public class CreateMenuListController {
    @FXML
    TextField foodnamefield;
    @FXML
    TextField foodpricefield;
    @FXML
    TextField foodallergenfeild;
    @FXML
    TextField EventNamefield;
    @FXML
    TextField Eventpricefeild;
    @FXML
    Button foodcreatebutton;
    @FXML
    Button EventcreateButton;
    @FXML
    Button completebutton;

    Menu menu;
    EventItems eventlist;
    EventDaoImpl eventdb;
    EventItemsDaoImpl eventlistdb;
    MenuDaoImpl menudb;
    
    public void handleFoodCreate(ActionEvent event){
        String name =foodnamefield.getText();
        ArrayList <String> allergens =new ArrayList<String>();
        allergens.add(foodallergenfeild.getText());
        double price=0;
        try{
        price=Double.parseDouble(foodpricefield.getText());
        }
        catch(Exception e){

            event.consume();
        }
        FoodItem food= new FoodItem.Builder<>().name(name).allergens(allergens).price(price).build();
        menu.addFoodItem(food);
    }
    public void handleEventCreate(ActionEvent event){
        String name =EventNamefield.getText();
        double price=0;
        try{
        price=Double.parseDouble(foodpricefield.getText());
        }
        catch(Exception e){

            event.consume();
        }
        Event eve= new Event.Builder<>().name(name).price(price).build();

        eventlist.addEventItem(eve);
    }
    public void handleComplete(ActionEvent event){
        Button btn = (Button) event.getSource();
        Scene scene = btn.getScene();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../login/LoginFX.fxml"));
            Stage stage = (Stage) scene.getWindow();
            Scene scene2 = new Scene(root);
            stage.setScene(scene2);
        } catch (IOException io) {
            System.out.println("Error loading Checkout");
            System.out.println(io.toString());
        }
        event.consume();
    }
    public void initialize(){}
}
