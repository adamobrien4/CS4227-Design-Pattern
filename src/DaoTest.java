import javafx.application.Application;
import javafx.stage.Stage;
import main.presentation_layer.presentation.PresentationLoader;
import main.presentation_layer.presentation.UseRemote;

public class DaoTest extends Application {
    public void start(Stage primaryStage) throws Exception{
        PresentationLoader.setStage(primaryStage);
        UseRemote.createMenu();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
