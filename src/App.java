import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.data_layer.DatabaseRepository;
import main.entities.FoodItem;
import main.entities.Menu;
import main.entities.Restaurant;
import main.presentation_layer.PresentationLoader;

import org.bson.types.ObjectId;

import java.io.IOException;
import java.util.ArrayList;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        PresentationLoader.setStage(primaryStage);
        PresentationLoader.display(PresentationLoader.BROWSE_RESTAURANT);
    }

    public static void main(String[] args) {

        DatabaseRepository.setup();

        String[] allergens1 = {"gluten","nuts"};
        String[] allergens2 = {"soya","milk"};

        FoodItem item1 = new FoodItem("cheese",allergens1,4.50);
        FoodItem item2 = new FoodItem("burger",allergens2,7.50);

        FoodItem item3 = new FoodItem("ham",allergens2,5.50);
        FoodItem item4 = new FoodItem("salad",allergens1,4.50);

        FoodItem item5 = new FoodItem("ice cream",allergens1,4.50);
        FoodItem item6 = new FoodItem("cherry",allergens2,1.50);





        ArrayList<FoodItem> listOfMainCoursesItems = new ArrayList<>();
        listOfMainCoursesItems.add(item1);
        listOfMainCoursesItems.add(item2);

        ArrayList<FoodItem> listOfDesertItems = new ArrayList<>();
        listOfDesertItems.add(item3);
        listOfDesertItems.add(item4);

        ArrayList<FoodItem>  listOfSideItems = new ArrayList<>();
        listOfSideItems.add(item5);
        listOfSideItems.add(item6);

        ArrayList<FoodItem>  listOfDrinkItems = new ArrayList<>();
        listOfSideItems.add(item5);
        listOfSideItems.add(item6);


        System.out.println("creating restaurant");



        Menu menu = new Menu("the menu",listOfMainCoursesItems,listOfDesertItems,listOfSideItems, listOfDrinkItems);
        Restaurant restaurant = new Restaurant( new ObjectId(),"The best restaurant",menu,"best");


        DatabaseRepository databaseRepository = new DatabaseRepository();

        databaseRepository.createRestaurant(restaurant, new ObjectId() );


        launch();
    }

}
