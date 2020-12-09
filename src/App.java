import javafx.application.Application;
import javafx.stage.Stage;
import main.Globals;
import main.data_layer.DatabaseRepository;
import main.presentation_layer.PresentationLoader;

import java.io.IOException;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {

        if (Globals.getLoggedInUser() == null) {
            PresentationLoader.setStage(primaryStage);
            PresentationLoader.display(PresentationLoader.LOGIN);
        } else {
            PresentationLoader.setStage(primaryStage);
            PresentationLoader.display(PresentationLoader.BROWSE_RESTAURANT);
        }
        
    }

    public static void main(String[] args) {

        DatabaseRepository.setup();

        launch();
    }

}
