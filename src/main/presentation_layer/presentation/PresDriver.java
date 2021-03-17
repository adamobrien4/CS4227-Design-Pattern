package main.presentation_layer.presentation;

import java.io.IOException;

public class PresDriver implements PresCommand {

    PresentationLoader thePresentationLoader;

    public PresDriver(PresentationLoader newPresentationLoader) {
        thePresentationLoader = newPresentationLoader;
    }

    @Override
    public void execute() throws IOException {
        thePresentationLoader.driver();

    }
    
}