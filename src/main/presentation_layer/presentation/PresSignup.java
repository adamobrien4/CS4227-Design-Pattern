package main.presentation_layer.presentation;

import java.io.IOException;

public class PresSignup implements PresCommand {

    PresentationLoader thePresentationLoader;

    public PresSignup(PresentationLoader newPresentationLoader) {
        thePresentationLoader = newPresentationLoader;
    }

    @Override
    public void execute() throws IOException {
        thePresentationLoader.signup();
    }
    
}
