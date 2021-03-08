import javafx.application.Application;
import javafx.stage.Stage;
import main.data_layer.DatabaseRepository;
import main.presentation_layer.PresentationLoader;

import java.io.IOException;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {

        PresentationLoader pl = PresentationLoader.getInstance();

        PresentationLoader.setStage(primaryStage);
        pl.display(PresentationLoader.LOGIN);
        
    }

    public static void main(String[] args) {

        DatabaseRepository.setup();

        launch();
    }

}
