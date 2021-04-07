package main.presentation_layer.presentation;

import java.io.IOException;

public class PresCreateMenuList implements PresCommand{

    PresentationLoader thePresentationLoader;

    public PresCreateMenuList(PresentationLoader newPresentationLoader){
        thePresentationLoader = newPresentationLoader;
    }
    @Override
    public void execute() throws IOException {
        thePresentationLoader.create_menu_list();
        
    }

    
}
