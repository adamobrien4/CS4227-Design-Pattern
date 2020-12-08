package main.presentation_layer.login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.data_layer.DatabaseRepository;
import main.presentation_layer.PresentationLoader;

public class LoginMain extends Application {

    public void start(Stage primaryStage) throws Exception{
        System.out.println(getClass().getResource("LoginFX.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("LoginFX.fxml"));

        DatabaseRepository.setup();
        PresentationLoader.setStage(primaryStage);

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}