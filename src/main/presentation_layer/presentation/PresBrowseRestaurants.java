package main.presentation_layer.presentation;

import java.io.IOException;

public class PresBrowseRestaurants implements PresCommand {

    PresentationLoader thePresentationLoader;

    public PresBrowseRestaurants(PresentationLoader newPresentationLoader) {
        thePresentationLoader = newPresentationLoader;
    }

    @Override
    public void execute() throws IOException {
        thePresentationLoader.browse_restaurants();

    }
    
}
