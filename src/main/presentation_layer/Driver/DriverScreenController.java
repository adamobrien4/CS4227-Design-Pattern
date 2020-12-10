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
    private static int UnAcptIdSize;
    private static ArrayList <Object> Price;
    private static ArrayList<Object> Cust;
    private static ArrayList<Object> Rest;
    private static ArrayList<String> unacptid;
    private static ArrayList<Object> acptid;

    public static void main(String[] args) {
        ArrayList<Object> price = new ArrayList<Object>();
        ArrayList<Object> cust = new ArrayList<Object>();
        ArrayList<Object> rest = new ArrayList<Object>();
        ArrayList<String> UnAcptId = new ArrayList<String>();
        ArrayList<Object> Actid=new ArrayList<Object>();
        ObjectId tempId;
        DatabaseRepository.setup();
        DatabaseRepository.getDB();

        FindIterable<org.bson.Document> ordPending = DatabaseRepository.getOrders("pending");
        FindIterable<org.bson.Document> ordUnAccepted=DatabaseRepository.getOrders("UnAccepted");
        for (org.bson.Document doc : ordPending) {
            price.add(doc.get("total_cost"));
            tempId = (ObjectId) doc.get("customer_id");
            cust.add(DatabaseRepository.getCust(tempId).get("email"));
            tempId = (ObjectId) doc.get("restaurant_id");
            rest.add(DatabaseRepository.getRest(tempId).get("name"));
            tempId=(ObjectId) doc.get("_id");
            Actid.add(tempId);
        }
        
        for (org.bson.Document doc : ordUnAccepted){;
            UnAcptId.add(doc.get("_id").toString());
        }
        
        custSize = cust.size();
        UnAcptIdSize= UnAcptId.size();
        Price=price;
        Cust=cust;
        Rest=rest;
        acptid=Actid;
        unacptid=UnAcptId;
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Driver Screen");
        HBox root = new HBox();
        VBox OrderTab = new VBox();
        VBox AcceptTab = new VBox();
        VBox DetailsTab = new VBox();
        for (int i = 0; i < custSize; i++) {
            btn = new Button("Complete: #"+(i+1));
            btn.setLayoutY(i * 100);
            btn.setLayoutX(1);
            btn.setId("Order"+acptid.get(i));
            btn.setOnAction(this);
            OrderTab.getChildren().add(btn);

        }
        for (int i = 0; i < UnAcptIdSize; i++) {
            btn = new Button("Accept");
            btn.setLayoutY(i * 100);
            btn.setLayoutX(1);
            btn.setId("NewOr"+unacptid.get(i));
            btn.setOnAction(this);
            AcceptTab.getChildren().add(btn);

        }
        
        AcceptTab.setPadding(new Insets(10, 20, 10, 5));
        DetailsTab.setPadding(new Insets(10, 20, 10, 5));
        OrderTab.setPadding(new Insets(10, 20, 10, 5));
        root.setPadding(new Insets(10, 10, 10, 5));
        TabPane detailsTabPane= new TabPane();


        for(int i = 0; i<Cust.size(); i++){
        VBox Tabv= new VBox();
        Tabv.getChildren().addAll( new Label("Name: "+Cust.get(i)),new Label("Restaurant: "+Rest.get(i)),new Label("Price: "+Price.get(i)));
        Tab tab= new Tab("Order: #"+(i+1),Tabv);
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

        root.getChildren().addAll(OrderTab, detailsTabPane, AcceptTab);

        Scene orderScene = new Scene(root, 1920, 1000);
        primaryStage.setScene(orderScene);

        primaryStage.show();
    }

    //button handler
    @Override
    public void handle(ActionEvent arg0){
    Button sourceButton = (Button) arg0.getSource();
    String id = sourceButton.getId();

    ObjectId x = new ObjectId(id.substring(5));
    if(id.substring(0,5).equals("Order")){
        System.out.println("Order Completed: "+id.substring(5));
        DatabaseRepository.completeOrder(x);
    }

    
    
    if(id.substring(0,5).equals("NewOr")){
        System.out.println("Order Accepted: "+id.substring(8));
    
        DatabaseRepository.acceptOrder(x);
    }
    
    }

    
} 