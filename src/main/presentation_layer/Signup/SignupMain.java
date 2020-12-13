package main.presentation_layer.Signup;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.data_layer.DatabaseRepository;

public class SignupMain extends Application {

    public void start(Stage primaryStage) throws Exception{
        System.out.println(getClass().getResource("SignupFX.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("SignupFX.fxml"));

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }
    


    public static void main(String[] args) {
        DatabaseRepository.setup();

        launch(args);

    }
}
