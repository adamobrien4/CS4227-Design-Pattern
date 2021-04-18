package main.presentation_layer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DriverScreen extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        // Create FXMLLoader
        /*
         * FXMLLoader loader= new FXMLLoader(); //path String filePath =
         * "C:/Users/Andrew/Desktop/Cs4125/OOAD-Project/src/main/presentation_layer/Driver/";
         * FileInputStream fxmlStream = new FileInputStream(filePath);
         */
        Parent root = FXMLLoader.load(getClass().getResource("DriverScreen.fxml"));

        stage.setTitle("Hello World");
        stage.setScene(new Scene(root, 1000, 800));
        stage.show();

        /*
         * VBox root = (VBox) loader.load(fxmlStream); Scene scene = new Scene(root);
         * stage.setScene(scene); stage.setTitle("Driver Screen"); stage.show();
         */

    }

}
