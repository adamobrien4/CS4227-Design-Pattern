package main.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoCollection;
import main.data_layer.DatabaseRepository;
import main.entities.Customer;
import main.services.HttpService;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class CustomerDaoImpl implements Dao<Customer> {

    // MongoCollection<Customer> customers = DatabaseRepository.getCollection("users", Customer.class);

    @Override
    public Customer get(String id) {
        String response = HttpService.get("http://localhost:5000/customers/" + id);

        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        try {
            Customer customer = mapper.readValue(response, new TypeReference<Customer>() {});
            return customer;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Get all Customers
    public Customer[] getAll() {
//        FindIterable<Customer> result = customers.find(new Document().append("type", "customer"))
//
//        if(result == null) {
//            System.out.println("Could not find customer document with ID: " + id);
//            return null;
//        } else {
//            System.out.println("Found Customer:" +  result.toString());
//            return result.
//        }
        return null;
    }


    @Override
    public boolean insert(Customer customer) {
//        try {
//            customers.insertOne(customer);
//        } catch(Exception e) {
//            System.out.println("Could not insert customer document");
//            return false;
//        }
//        return true;

        String customerAsJson = "";
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            customerAsJson = objectMapper.writeValueAsString(customer);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return false;
        }

        System.out.println(customerAsJson);

        String response = HttpService.post("http://localhost:5000/customers/add", customerAsJson);

        if(response == "Customer Added!") {
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Customer customer) {
//        Document setData = new Document();
//        setData.append("email", customer.getEmail()).append("password", customer.getPassword()).append("address", customer.getAddress());
//
//        try{
//            customers.updateOne(eq("_id", customer.getId()), new Document().append("$set", setData));
//        } catch (Exception e) {
//            System.out.println("Could not update customer");
//            return false;
//        }
//        return true;
        return false;
    }

    @Override
    public boolean delete(Customer customer) {
//        try {
//            customers.deleteOne(new Document().append("_id", customer.getId()));
//        } catch (Exception e) {
//            System.out.println("Could not delete customer ID:" + customer.getId());
//            return false;
//        }
//        return true;
        return false;
    }

}
