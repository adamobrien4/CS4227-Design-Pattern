package main.presentation_layer.Presentation;

import java.io.IOException;

public class PresCreateOrder implements PresCommand {

    PresentationLoader thePresentationLoader;

    public PresCreateOrder(PresentationLoader newPresentationLoader) {
        thePresentationLoader = newPresentationLoader;
    }

    @Override
    public void execute() throws IOException {
        thePresentationLoader.create_order();

    }
    
}
