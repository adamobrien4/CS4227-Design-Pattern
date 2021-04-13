import javafx.application.Application;
import javafx.stage.Stage;
import main.Globals;
import main.adapters.AdapterType;
import main.framework.Framework;
import main.framework.interceptors.LoggingInterceptor;
import main.framework.interceptors.ScreenSwitchInterceptor;
import main.presentation_layer.presentation.*;
import main.services.HttpService;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {

        // Framework
        Framework framework = Framework.getInstance();

        // Interceptors
        LoggingInterceptor loggingInterceptor = LoggingInterceptor.getInstance(AdapterType.CONSOLE);
        ScreenSwitchInterceptor screenSwitchInterceptor = ScreenSwitchInterceptor.getInstance();

        // Register Interceptors
        framework.registerLoggingInterceptor(loggingInterceptor);
        framework.registerScreenSwitchInterceptor(screenSwitchInterceptor);

        PresentationLoader.setStage(primaryStage);
        UseRemote.login();
        
    }

    public static void main(String[] args) {

        // Check that the API is available
        String resp = HttpService.get(Globals.APPLICATION_API_URL + "/ping");

        if(resp != null && resp.equals("\"API Available\"")) {
            launch();
        } else {
            System.out.println(resp);
            System.out.println("Cannot connect to API, is the API available?");
            System.exit(1);
        }
    }

}
