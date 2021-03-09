import javafx.application.Application;
import javafx.stage.Stage;
import main.Globals;
import main.data_layer.DatabaseRepository;
import main.entities.User;
import main.presentation_layer.Presentation.*;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {

        PresentationLoader.setStage(primaryStage);
        UseRemote.login();
        
    }

    public static void main(String[] args) {

        DatabaseRepository.setup();

        launch();
    }

}
