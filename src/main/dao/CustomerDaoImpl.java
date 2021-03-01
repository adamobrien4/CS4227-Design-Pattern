package main.dao;

import com.mongodb.client.MongoCollection;
import main.data_layer.DatabaseRepository;
import main.entities.Customer;
import org.bson.Document;
import org.bson.types.ObjectId;

import static com.mongodb.client.model.Filters.eq;

public class CustomerDaoImpl implements Dao<Customer> {

    MongoCollection<Customer> customers = DatabaseRepository.getCollection("users", Customer.class);

    @Override
    public Customer get(String id) {
        Customer result = customers.find(eq("_id", new ObjectId(id))).first();

        if(result == null) {
            System.out.println("Could not find customer document with ID: " + id);
            return null;
        } else {
            System.out.println("Found Customer:" +  result.toString());
            return result;
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
        try {
            customers.insertOne(customer);
        } catch(Exception e) {
            System.out.println("Could not insert customer document");
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Customer customer) {
        Document setData = new Document();
        setData.append("email", customer.getEmail()).append("password", customer.getPassword()).append("address", customer.getAddress());

        try{
            customers.updateOne(eq("_id", customer.getId()), new Document().append("$set", setData));
        } catch (Exception e) {
            System.out.println("Could not update customer");
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Customer customer) {
        try {
            customers.deleteOne(new Document().append("_id", customer.getId()));
        } catch (Exception e) {
            System.out.println("Could not delete customer ID:" + customer.getId());
            return false;
        }
        return true;
    }

}
