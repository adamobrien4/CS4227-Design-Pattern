package main.data_layer;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

import java.util.Iterator;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;


public class ConnectToDB {

	public static void main(String args[]) {

		MongoClientURI uri = new MongoClientURI("mongodb+srv://cs4125_user:P3anutButt3r@sandbox.51cvt.mongodb.net/cs4125?retryWrites=true&w=majority");

		MongoClient mongoClient = new MongoClient(uri);
		MongoDatabase database = mongoClient.getDatabase("cs4125");

		System.out.println();

		FindIterable<Document> iterDoc = database.getCollection("users").find();
		int i = 1;
		// Getting the iterator
		Iterator it = iterDoc.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
			i++;
		}
	}
}