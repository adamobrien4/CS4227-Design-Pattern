package main.presentation_layer.Driver;

import javafx.fxml.FXML;
import main.data_layer.DatabaseRepository;
import main.presentation_layer.Presentation.*;

import java.util.ArrayList;

import com.mongodb.client.FindIterable;

import org.bson.types.ObjectId;

import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;

public class DriverScreenController {

    @FXML
    private VBox orders_pane;
    @FXML
    private VBox accept_pane;
    @FXML
    private VBox DetailsPane;
    @FXML
    private HBox root;
    Button btn;
    Label lab;
    Label dashlab;
    CheckBox cbx;

    VBox OrderTab;
    VBox AcceptTab;
    VBox DetailsTab;
    // Testing
    private int custSize;
    private int UnAcptIdSize;
    private ArrayList<Object> Price;
    private ArrayList<Object> Cust;
    private ArrayList<Object> Rest;
    private ArrayList<String> unacptid;
    private ArrayList<Object> acptid;
    DatabaseRepository db = new DatabaseRepository();
    

    EventHandler<ActionEvent> handleClick = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent evt) {
            Button sourceButton = (Button) evt.getSource();
            String id = sourceButton.getId();
            ObjectId x = new ObjectId(id.substring(5));
            if (id.substring(0, 5).equals("Order")) {
                System.out.println("Order Completed: " + id.substring(5));
                         db.completeOrder(x);
                     }

                     if (id.substring(0, 5).equals("NewOr")) {
                           System.out.println("Order Accepted: " + id.substring(8));
               
                           db.acceptOrder(x);
                       }
            evt.consume();
        }
    };

    @FXML
    public void initialize() {

        Stage primaryStage = PresentationLoader.getStage();

        ArrayList<Object> price = new ArrayList<Object>();
        ArrayList<Object> cust = new ArrayList<Object>();
        ArrayList<Object> rest = new ArrayList<Object>();
        ArrayList<String> UnAcptId = new ArrayList<String>();
        ArrayList<Object> UnDelCost = new ArrayList<Object>();
        ArrayList<String> Unaddr= new ArrayList<String>();
        ArrayList<Object> Actid=new ArrayList<Object>();
        ObjectId tempId;
        DatabaseRepository.setup();
        DatabaseRepository.getDB();

        FindIterable<org.bson.Document> ordPending = db.getOrders("pending");
        FindIterable<org.bson.Document> ordUnAccepted=db.getOrders("UnAccepted");
        for (org.bson.Document doc : ordPending) {
            price.add(doc.get("total_cost"));
            tempId = (ObjectId) doc.get("customer_id");
            cust.add(DatabaseRepository.getCust(tempId).get("email"));
            tempId = (ObjectId) doc.get("restaurant_id");
            rest.add(db.getRest(tempId).get("name"));
            tempId=(ObjectId) doc.get("_id");
            Actid.add(tempId);
        }
        
        for (org.bson.Document doc : ordUnAccepted){
            UnAcptId.add(doc.get("_id").toString());
            tempId = (ObjectId) doc.get("customer_id");
            Unaddr.add(DatabaseRepository.getCust(tempId).get("address").toString());
            UnDelCost.add(doc.get("delivery_cost"));
        }
        
        custSize = cust.size();
        UnAcptIdSize = UnAcptId.size();
        Price = price;
        Cust = cust;
        Rest = rest;
        acptid = Actid;
        unacptid = UnAcptId;

        for (int i = 0; i < custSize; i++) {
            Button btn = new Button("Complete: #" + (i + 1));
            btn.setLayoutY(i * 100);
            btn.setLayoutX(1);
            btn.setId("Order" + acptid.get(i));
            btn.setOnAction(handleClick);
            orders_pane.getChildren().add(btn);
        }
        
        for (int i=0; i<Unaddr.size();i++){
          Unaddr.set(i,Unaddr.get(i).replaceAll(",", " \n")); 
        }
        for (int i = 0; i < UnAcptIdSize; i++) {
            btn = new Button("Accept");
            lab= new Label("Payment: €"+UnDelCost.get(i)+"\nAddress: "+Unaddr.get(i));
            dashlab=new Label("--------------------");
            btn.setLayoutY(i * 100);
            btn.setLayoutX(1);
            btn.setId("NewOr" + unacptid.get(i));
            btn.setOnAction(handleClick);
            accept_pane.getChildren().addAll(lab,btn,dashlab);

        }

       // primaryStage.setTitle("Driver Screen");
        VBox OrderTab = new VBox();
        VBox AcceptTab = new VBox();
        VBox DetailsTab = new VBox();
        for (int i = 0; i < custSize; i++) {
            btn = new Button("Complete: #"+(i+1));
            btn.setLayoutY(i * 100);
            btn.setLayoutX(1);
            btn.setId("Order" + acptid.get(i));
            btn.setOnAction(handleClick);
            OrderTab.getChildren().add(btn);

        }
        for (int i = 0; i < UnAcptIdSize; i++) {
            btn = new Button("Accept");
            btn.setLayoutY(i * 100);
            btn.setLayoutX(1);
            btn.setId("NewOr" + unacptid.get(i));
            btn.setOnAction(handleClick);
            AcceptTab.getChildren().add(btn);
        }
        
        accept_pane.setPadding(new Insets(10, 20, 10, 5));
        DetailsTab.setPadding(new Insets(10, 20, 10, 5));
        orders_pane.setPadding(new Insets(10, 20, 10, 5));
        root.setPadding(new Insets(10, 10, 10, 5));
        
    TabPane DetailsTabPane=new TabPane();

        for(int i = 0; i<Cust.size(); i++){
            VBox Tabv= new VBox();
            Tabv.getChildren().addAll( new Label("Email: "+Cust.get(i)),new Label("Restaurant: "+Rest.get(i)),new Label("Price: €"+Price.get(i)));
            Tab tab= new Tab("Order: #"+(i+1),Tabv);
            DetailsTabPane.getTabs().add(tab);
    }
        
        DetailsPane.getChildren().add(DetailsTabPane);
        

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
        btn.setOnAction(handleClick);

        DetailsTab.getChildren().addAll(check1, check2, btn);

        //root.getChildren().addAll(orders_pane, DetailsTab, accept_pane);

       // Scene orderScene = new Scene(root, 1920, 1000);
       // primaryStage.setScene(orderScene);

        //primaryStage.show();
    
    }
} 
