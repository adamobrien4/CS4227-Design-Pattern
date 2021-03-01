package main.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import main.data_layer.DatabaseRepository;
import main.entities.Customer;
import org.bson.types.ObjectId;

import static com.mongodb.client.model.Filters.eq;

public class CustomerDaoImpl implements Dao<Customer> {

    MongoCollection<Customer> customers = DatabaseRepository.getCollection("users", Customer.class);

    @Override
    public Customer get(String id) {
        // TODO: Retrieve requested customer from Database
        // return new Customer("customer@gmail.com", "1234", "Apt. 8, Limerick Rd, Limerick");
        Customer result = customers.find(eq("_id", new ObjectId(id))).first();

        if(result == null) {
            System.out.println("Could not find customer document with ID: " + id);
            return null;
        } else {
            System.out.println("Found Customer:" +  result.toString());
            return result;
        }
    }

    @Override
    public Customer[] getAll() {
        // TODO: Retrieve requested customers from Database
        return new Customer[] {
                new Customer("customer1@gmail.com", "1234", "Apt. 8, Limerick Rd, Limerick"),
                new Customer("customer2@gmail.com", "1234", "Apt. 8, Limerick Rd, Limerick"),
                new Customer("customer3@gmail.com", "1234", "Apt. 8, Limerick Rd, Limerick")
        };
    }


    @Override
    public boolean insert(Customer customer) {
        return true;
    }

    @Override
    public boolean update(Customer customer) {
        return true;
    }

    @Override
    public boolean delete(Customer customer) {
        return true;
    }

}
