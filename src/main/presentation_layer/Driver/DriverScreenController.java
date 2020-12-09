package main.presentation_layer.Driver;

import main.entities.FoodItem;
import main.entities.Order;
import main.data_layer.DatabaseRepository;
import main.entities.BasketItem;
import org.bson.types.ObjectId;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.ResourceBundle;

import org.bson.Document;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class DriverScreenController{
    @FXML
    private Text ViewOrderId;
    @FXML
    private Text RestaurntLocation;
    @FXML
    private Text  CustomerName;
    @FXML
    private Text CustomerAddress;
    @FXML 
    private Text Cost;
    @FXML
    private AnchorPane ViewOrderScreen;
    @FXML
    private AnchorPane ViewAcceptedScreen;
    @FXML
    private Button CompleteButton;
    @FXML
    private CheckBox ParkedAtRestaurantCheck;
    @FXML
    private CheckBox EnrouteCheck;
    @FXML 
    private Button AcceptButton;

    DatabaseRepository db;
    Order[] Ord; 
    double OrderTotal;
    String CustName ="";
    String AddressName ="";
    String RestaurantAddress ="";
    ObjectId OrderId;
    Order[] UnOrd;
    EventHandler<ActionEvent> acceptOrdeHandler = new EventHandler<ActionEvent>(){

        @Override
        public void handle(ActionEvent arg0) {
            //To Do AssignDriverId to Order

        }
        
    };
    EventHandler<ActionEvent> completeOrderHandler = new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent arg0){
            //TO DO set status to complete
        }
    };
    EventHandler<ActionEvent>viewOrderHandler = new EventHandler<ActionEvent>(){

        @Override
        public void handle(ActionEvent arg0) {
            //Display Order Details

        }

    };
    EventHandler<ActionEvent>checkEnrouteBoxHandler = new EventHandler<ActionEvent>(){

        @Override
        public void handle(ActionEvent arg0) {
            // TODO Set status to enroute

        }
        
    };
    EventHandler<ActionEvent>checkParkedBoxHandler = new EventHandler<ActionEvent>(){

        @Override
        public void handle(ActionEvent arg0) {
            // TODO Set status to at restuarant


        }
        
    };
    @FXML
    public void initialize(){
        db = new DatabaseRepository();
        //TO do Get Orders from DB
        String [] unfoodarray={"Big Burger","Cheesy Fies","A brick on toast"};
        String [] foodarray={"Chicken curry","chips","pepsi max"};
        Ord = new Order[]{
            new Order(OrderId, foodarray)
        
        };
        UnOrd=new Order[]{
            new Order(OrderId,unfoodarray)
        };

        for (int i=0; i<Ord.length; i++){
            double y = 27.0+(33.0*i);
            ViewAcceptedScreen.getChildren().addAll(makeOrderListing(Ord[i],y,i));
            
        }
        for (int i=0; i<UnOrd.length; i++){
            double y =27.0+(33.0*i);
            ViewOrderScreen.getChildren().addAll(acceptOrderListing(UnOrd[i], y, i));
        }
        if (Ord.length>0){
        ViewOrderScreen.getChildren().addAll(displayOrderDetails(Ord[0]));
        }else{
            ViewOrderScreen.getChildren().add(displayNoOrder());
        }

    }
    private Node displayNoOrder(){
        String NoOrders = "You Have No Active Orders";
        Text NorderTxtBox = new Text();
        NorderTxtBox.setLayoutX(1000.0);
        NorderTxtBox.setLayoutY(14.0);
        NorderTxtBox.setText(NoOrders);

        return NorderTxtBox;
    }
    private Node[] makeOrderListing(Order order, double y,int btnIndex){
        String ObjID="INSERT ID HERE";
        Text name = new Text();
        name.setLayoutX(14.0);
        name.setLayoutY(y);
        name.setText(ObjID);

        Button btn = new Button("View");
        btn.setLayoutX(596.0);
        btn.setLayoutY(y-17.0);
        btn.setId("ViewOrderButton"+btnIndex);
        btn.setOnAction(viewOrderHandler);
    
    
        return new Node[] {name,btn};
    }
    private Node[] displayOrderDetails(Order order){
        String ObjID="INSERT ID HERE";
        Text ViewingOrderId = new Text();
        ViewingOrderId.setText(ObjID);
        ViewingOrderId.setLayoutX(1000.0);
        ViewingOrderId.setLayoutY(14.0);

        AddressName="Insert Adress Here";
        CustName="Insert Name here";
        RestaurantAddress="Insert Restaurant Address";

        Text ViewCustName=new Text();
        Text ViewRestAddress=new Text();
        Text ViewCustAddress=new Text();
        
        ViewCustAddress.setLayoutX(1000.0);
        ViewCustAddress.setLayoutY(28.0);

        ViewRestAddress.setLayoutX(1000.0);
        ViewRestAddress.setLayoutY(35.0);

        ViewCustName.setLayoutX(1000.0);
        ViewCustName.setLayoutY(21.0);

        CheckBox EnrouteBox= new CheckBox();
        CheckBox ParkedAtRestaurantCheck= new CheckBox();
        
        EnrouteBox.setOnAction(checkEnrouteBoxHandler);
        ParkedAtRestaurantCheck.setOnAction(checkParkedBoxHandler);
        return new Node[]{ViewCustName, ViewRestAddress, ViewCustAddress,EnrouteBox,ParkedAtRestaurantCheck};

    }
    private Node[] acceptOrderListing(Order order, double y,int btnIndex){
        String ObjID="INSERT ID HERE";
        Text name = new Text();
        name.setLayoutX(1500);
        name.setLayoutY(y);
        name.setText(ObjID);

        Button btn = new Button("Accept");
        btn.setLayoutX(2000);
        btn.setLayoutY(y-17.0);
        btn.setId("ViewOrderButton"+btnIndex);
        btn.setOnAction(acceptOrdeHandler);
    
    
        return new Node[] {name,btn};
    }
}