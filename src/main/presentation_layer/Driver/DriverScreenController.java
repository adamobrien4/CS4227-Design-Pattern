package main.presentation_layer.Driver;

import main.data_layer.DatabaseRepository;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.Document;

import com.mongodb.client.FindIterable;

import org.bson.types.ObjectId;

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
    private static int custSize;
    private static ArrayList <Object> Price;
    private static ArrayList<Object> Cust;
    private static ArrayList<Object> Rest;

    public static void main(String[] args) {
        ArrayList<Object> price = new ArrayList<Object>();
        ArrayList<Object> cust = new ArrayList<Object>();
        ArrayList<Object> rest = new ArrayList<Object>();
        Object temp1;
        ObjectId tempId;
        DatabaseRepository.setup();
        DatabaseRepository.getDB();

        FindIterable<org.bson.Document> ord = DatabaseRepository.getOrders();

        for (org.bson.Document doc : ord) {
            price.add(doc.get("total_cost"));
            tempId = (ObjectId) doc.get("customer_id");
            cust.add(DatabaseRepository.getCust(tempId).get("email"));
            tempId = (ObjectId) doc.get("restaurant_id");
            rest.add(DatabaseRepository.getRest(tempId).get("name"));
        }
        custSize = cust.size();
        Price=price;
        Cust=cust;
        Rest=rest;
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Driver Screen");
        HBox root = new HBox();
        VBox OrderTab = new VBox();
        VBox AcceptTab = new VBox();
        VBox DetailsTab = new VBox();
        for (int i = 0; i < custSize; i++) {
            //temp = Integer.toString(Id[i]);
            btn = new Button("Complete");
            btn.setLayoutY(i * 100);
            btn.setLayoutX(1);
            //btn.setId("Order"+temp);
            //btn.setOnAction(this);
            OrderTab.getChildren().add(btn);

        }
        for (int i = 0; i < custSize; i++) {
           // temp = Integer.toString(Id[i]);
            btn = new Button("Accept");
            btn.setLayoutY(i * 100);
            btn.setLayoutX(1);
           // btn.setId("NewOrder"+temp);
            //btn.setOnAction(this);
            AcceptTab.getChildren().add(btn);

        }
        
        AcceptTab.setPadding(new Insets(10, 20, 10, 5));
        DetailsTab.setPadding(new Insets(10, 20, 10, 5));
        OrderTab.setPadding(new Insets(10, 20, 10, 5));
        root.setPadding(new Insets(10, 10, 10, 5));
        TabPane detailsTabPane= new TabPane();


        for(int i = 0; i<Cust.size(); i++){
        VBox Tabv= new VBox();
        Tabv.getChildren().addAll( new Label("Name: "+Cust.get(i)),new Label("Name: "+Rest.get(i)),new Label("Price: "+Price.get(i)));
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