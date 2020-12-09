package main.presentation_layer.Driver;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;

public class DriverScreenController extends Application implements EventHandler<ActionEvent> {
    Button btn;
    Label lab;
    CheckBox cbx;
    //StackPane DetailsStack;
    HBox root;
    VBox OrderTab;
    VBox AcceptTab;
    VBox DetailsTab;
    //Testing
    int[] Id = { 1, 2, 3, 4, 5, 6, 7 };
    int[] unId = { 1, 2, 3, 4, 5, 6, 7 };
    int[] Price = { 10, 20, 40, 20, 50, 20, 15 };
    String[] Rest = {"mcdonalds","KFC","roB jAY CAFE","Adam O brien Fine wine","james'","Andys","Boomer"};
    String [] addr = {"add1","add2","add3","add4","add5","add6","add7"};
    String[] Cust = {"Andrew","Rob","James","Adam","Ted","Olie","Vanco"};
    String temp = "";

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Driver Screen");
        HBox root = new HBox();
        VBox OrderTab = new VBox();
        VBox AcceptTab = new VBox();
        VBox DetailsTab = new VBox();
        for (int i = 0; i < Id.length; i++) {
            temp = Integer.toString(Id[i]);
            btn = new Button("Complete Order: #" + temp);
            btn.setLayoutY(i * 100);
            btn.setLayoutX(1);
            btn.setId("Order"+temp);
            btn.setOnAction(this);
            OrderTab.getChildren().add(btn);

        }
        for (int i = 0; i < unId.length; i++) {
            temp = Integer.toString(Id[i]);
            btn = new Button("Accept: #" + temp);
            btn.setLayoutY(i * 100);
            btn.setLayoutX(1);
            btn.setId("NewOrder"+temp);
            btn.setOnAction(this);
            AcceptTab.getChildren().add(btn);

        }
        
        AcceptTab.setPadding(new Insets(10, 20, 10, 5));
        DetailsTab.setPadding(new Insets(10, 20, 10, 5));
        OrderTab.setPadding(new Insets(10, 20, 10, 5));
        root.setPadding(new Insets(10, 10, 10, 5));
        TabPane detailsTabPane= new TabPane();


        for(int i = 0; i<Cust.length; i++){
        VBox Tabv= new VBox();
        Tabv.getChildren().addAll( new Label("Name: "+Cust[i]),new Label("Address: "+addr[i]),new Label("Name: "+Rest[i]),new Label("Price: "+Price[i]));
        Tab tab= new Tab("Order: #"+i,Tabv);
        detailsTabPane.getTabs().add(tab);
    }
        
        
        

        HBox check1 = new HBox();
        cbx = new CheckBox();
        cbx.setId("cbxAtRest");
        //cbx.setOnAction(checkHandler);
        lab = new Label();
        lab.setText("Parked At Restaurant");
        check1.getChildren().addAll(cbx, lab);

        HBox check2 = new HBox();
        cbx = new CheckBox();
        cbx.setId("cbxEnRoute");
        //cbx.setOnAction(checkHandler);
        lab = new Label();
        lab.setText("Enroute");
        check2.getChildren().addAll(cbx, lab);

        btn = new Button("Complete");
        btn.setOnAction(this);

        DetailsTab.getChildren().addAll(check1, check2, btn);

        root.getChildren().addAll(AcceptTab, detailsTabPane, OrderTab);

        Scene orderScene = new Scene(root, 1920, 1000);
        primaryStage.setScene(orderScene);

        primaryStage.show();
    }

    //button handler
    @Override
    public void handle(ActionEvent arg0){
        System.out.println(arg0);
    Button sourceButton = (Button) arg0.getSource();
    String id = sourceButton.getId();
    
    if(id.substring(0,5)=="Order"){
    // To Do: Complete Orders
    }
    if(id.substring(0,5)=="NewOr"){
        //To Do: Accept Orders
    }
    }

    
} 