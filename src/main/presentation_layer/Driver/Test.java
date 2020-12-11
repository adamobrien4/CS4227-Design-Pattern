package main.presentation_layer.driver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.client.FindIterable;

import org.bson.BSONObject;
import org.bson.Document;
import org.bson.types.ObjectId;

import main.data_layer.DatabaseRepository;

public class Test {

    public static void main(String[] args) {
        ArrayList<Object> price = new ArrayList<Object>();
        ArrayList<Object> cust = new ArrayList<Object>();
        ArrayList<Object> rest = new ArrayList<Object>();
        Object temp1;
        ObjectId tempId;
        DatabaseRepository.setup();
        DatabaseRepository.getDB();
/*
        FindIterable<org.bson.Document> ord = DatabaseRepository.getOrders();

        for (org.bson.Document doc : ord) {
            price.add(doc.get("total_cost"));
            tempId = (ObjectId) doc.get("customer_id");
            cust.add(DatabaseRepository.getCust(tempId).get("email"));
            tempId=(ObjectId) doc.get("restaurant_id");
            rest.add(DatabaseRepository.getRest(tempId).get("name"));
        }
        System.out.println(cust);
       System.out.println(rest);
       System.out.println(price);

 */
    }
}

