package main.presentation_layer.Presentation;

import java.io.IOException;

public class PrescheckoutOrder implements PresCommand {

    PresentationLoader thePresentationLoader;

    public PrescheckoutOrder(PresentationLoader newPresentationLoader) {
        thePresentationLoader = newPresentationLoader;
    }

    @Override
    public void execute() throws IOException {
        thePresentationLoader.checkout_order();

    }
    
}
