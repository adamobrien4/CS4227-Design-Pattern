package main.presentation_layer.create_menu;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import main.dao.MenuDaoImpl;
import main.entities.BasketItem;
import main.entities.FoodItem;
import main.entities.Menu;
import main.exceptions.APIException;
import main.presentation_layer.presentation.UseRemote;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class CreateMenuController {

    @FXML
    private TextField menu_name_input;

    @FXML
    private TextField item_name_add_input;
    @FXML
    private TextField item_price_add_input;
    @FXML
    private TextField item_allergens_add_input;
    @FXML
    private Button add_item_button;
    @FXML
    private TabPane add_item_tab_pane;

    @FXML
    private AnchorPane main_course_pane;
    @FXML
    private AnchorPane sides_pane;
    @FXML
    private AnchorPane desserts_pane;
    @FXML
    private AnchorPane drinks_pane;

    @FXML
    private Label add_item_error;

    @FXML
    private Button create_menu_button;
    @FXML
    private Label create_menu_error;

    private int selectedTabIndex = 0;
    private Menu menu;

    private ArrayList<FoodItem> mainCourse = new ArrayList<>();
    private ArrayList<FoodItem> sidesCourse = new ArrayList<>();
    private ArrayList<FoodItem> dessertsCourse = new ArrayList<>();
    private ArrayList<FoodItem> drinksCourse = new ArrayList<>();

    @FXML
    protected void handleAddMenuItem(ActionEvent evt) {
        ObservableList<Node> children = null;
        ArrayList<FoodItem> selectedCourse = null;
        FoodItem newMenuItem;

        // Get data from user fields
        String itemName = item_name_add_input.getText();
        String itemPriceStr = item_price_add_input.getText();
        String itemAllergens = item_allergens_add_input.getText();
        boolean itemHasAllergens = false;

        if(itemName.length() == 0) {
            add_item_error.setText("Please specify an item name");
            return;
        } else if(itemPriceStr.length() == 0) {
            add_item_error.setText("Please specify an item price");
            return;
        }

        item_name_add_input.clear();
        item_price_add_input.clear();
        item_allergens_add_input.clear();

        double itemPrice = Double.parseDouble(itemPriceStr);

        if(itemAllergens.length() > 0) {
            itemHasAllergens = true;
        }

        switch(selectedTabIndex) {
            case 0:
                // Main Course
                children = main_course_pane.getChildren();
                selectedCourse = mainCourse;

                if(itemHasAllergens) {
                    newMenuItem = new FoodItem.Builder<>().name(itemName).price(itemPrice).allergens(new ArrayList(Arrays.asList(itemAllergens.split(",")))).build();
                } else {
                    newMenuItem = new FoodItem.Builder<>().name(itemName).price(itemPrice).build();
                }

                break;
            case 1:
                // Sides
                children = sides_pane.getChildren();
                selectedCourse = sidesCourse;

                if(itemHasAllergens) {
                    newMenuItem = new FoodItem.Builder<>().name(itemName).price(itemPrice).allergens(new ArrayList(Arrays.asList(itemAllergens.split(",")))).build();
                } else {
                    newMenuItem = new FoodItem.Builder<>().name(itemName).price(itemPrice).build();
                }

                break;
            case 2:
                // Desserts
                children = desserts_pane.getChildren();
                selectedCourse = dessertsCourse;

                if(itemHasAllergens) {
                    newMenuItem = new FoodItem.Builder<>().name(itemName).price(itemPrice).allergens(new ArrayList(Arrays.asList(itemAllergens.split(",")))).build();
                } else {
                    newMenuItem = new FoodItem.Builder<>().name(itemName).price(itemPrice).build();
                }

                break;
            case 3:
                // Drinks
                children = drinks_pane.getChildren();
                selectedCourse = drinksCourse;

                newMenuItem = new FoodItem.Builder<>().name(itemName).price(itemPrice).build();

                break;
            default:
                return;
        }

        selectedCourse.add(newMenuItem);

        children.clear();

        for(int i = 0; i < selectedCourse.size(); i++) {
            Text txt = new Text(selectedCourse.get(i).getName());
            txt.setLayoutX(12.0);
            txt.setLayoutY(20.0 + (20 * i));

            Text price = new Text(String.format("%.2f", selectedCourse.get(i).getPrice()));
            price.setLayoutX(200.0);
            price.setLayoutY(20.0 + (20 * i));

            String allergensStr = "";
            if(selectedTabIndex != 3) {
                if(selectedCourse.get(i).getAllergens() == null) {
                    allergensStr = "No Allergens Specified";
                } else {
                    allergensStr = String.valueOf(selectedCourse.get(i).getAllergens());
                }
            }

            Text allergens = new Text(allergensStr);
            allergens.setLayoutX(280.0);
            allergens.setLayoutY(20.0 + (20 * i));
            children.addAll(txt, price, allergens);
        }
        evt.consume();
    }

    @FXML
    protected void handleCreateMenu(ActionEvent evt) {
        if(mainCourse.isEmpty() && sidesCourse.isEmpty() && dessertsCourse.isEmpty() && drinksCourse.isEmpty()) {
            create_menu_error.setText("Please add at least one item to your menu");
            return;
        }

        String menuName = menu_name_input.getText();

        if(menuName.isEmpty()) {
            create_menu_error.setText("Please specify a menu name");
            return;
        }

        menu = new Menu(menuName, mainCourse, dessertsCourse, sidesCourse, drinksCourse);

        try {
            MenuDaoImpl.getInstance().insert(menu);
        } catch (APIException e) {
            e.printStackTrace();
        }

        try {
            UseRemote.browseRestaurants();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void initialize(){

        create_menu_error.setText("");
        add_item_error.setText("");

        menu = new Menu();
        add_item_tab_pane.getSelectionModel().selectedIndexProperty().addListener((observableValue, number, t1) -> {
            selectedTabIndex = (int) observableValue.getValue();
        });
    }
}
