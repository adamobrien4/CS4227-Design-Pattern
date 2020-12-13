package main.presentation_layer.Driver;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.data_layer.DatabaseRepository;

public class DriverScreenMain extends Application {

    public void start(Stage primaryStage) throws Exception{

        DatabaseRepository.setup();

        System.out.println(getClass().getResource("DriverScreen.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("DriverScreen.fxml"));

        primaryStage.setTitle("DriverScreen");
        primaryStage.setScene(new Scene(root, 900, 500));
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);

    }
}