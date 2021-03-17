package main.presentation_layer.presentation;

import java.io.IOException;

public interface Presentation{
    
    public void login() throws IOException;

    public void signup() throws IOException;

    public void checkout_order() throws IOException;

    public void driver() throws IOException;

    public void browse_restaurants() throws IOException;

    public void create_order() throws IOException;
}