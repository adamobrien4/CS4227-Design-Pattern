package main.presentation_layer.create_restaurant;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CreateRestaurantMain extends Application {

    public void start(Stage primaryStage) throws Exception{
        System.out.println(getClass().getResource("CreateRestaurant.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("CreateRestaurant.fxml"));

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);

    }
}