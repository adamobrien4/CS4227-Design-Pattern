package main.presentation_layer.presentation;

import java.io.IOException;

public class Screen {

    PresCommand theCommand;

    public Screen(PresCommand newCommand) {
        theCommand = newCommand;
    }

    public void change() throws IOException {

        theCommand.execute();

    }
}
