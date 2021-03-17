package main.presentation_layer.presentation;

public class Remote {

    public static Presentation getPres(){
        return new PresentationLoader();
        
    }
    
}
