package main.presentation_layer.Presentation;

public class Remote {

    public static Presentation getPres(){
        return new PresentationLoader();
        
    }
    
}
