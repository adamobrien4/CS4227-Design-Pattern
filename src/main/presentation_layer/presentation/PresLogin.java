package main.presentation_layer.presentation;

import java.io.IOException;

public class PresLogin implements PresCommand {

    PresentationLoader thePresentationLoader;

    public PresLogin(PresentationLoader newPresentationLoader) {
        thePresentationLoader = newPresentationLoader;
    }

    @Override
    public void execute() throws IOException {
        thePresentationLoader.login();

    }
    
}
