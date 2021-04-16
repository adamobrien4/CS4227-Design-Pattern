package main.presentation_layer.presentation;

import java.io.IOException;

public class PresCreateMenu implements PresCommand{

    PresentationLoader presentationLoader;

    public PresCreateMenu(PresentationLoader newPresentationLoader){
        presentationLoader = newPresentationLoader;
    }
    @Override
    public void execute() throws IOException {
        presentationLoader.create_menu();
        
    }

    
}
