package main.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import main.Globals;
import main.entities.users.Customer;
import main.services.HttpService;
import main.services.POJOMapper;

import static com.mongodb.client.model.Filters.eq;

public class CustomerDaoImpl implements Dao<Customer> {

    @Override
    public Customer get(String id) {
        String response = HttpService.get(Globals.APPLICATION_API_URL + id);

        try {
            return POJOMapper.getMapper().readValue(response, new TypeReference<Customer>() {});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean insert(Customer customer) {
        String response = null;
        try {
            response = HttpService.post(Globals.APPLICATION_API_URL + "/add", POJOMapper.getMapper().writeValueAsString(customer));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        assert response != null;
        return response.equals("Customer Added");
    }

    @Override
    public boolean update(Customer customer) {
        // TODO: Implement functionality for editing Customer
        return false;
    }

    @Override
    public boolean delete(Customer customer) {
        String response = HttpService.get(Globals.APPLICATION_API_URL + "/" + customer.getId());

        return response.equals("Customer Deleted");
    }
}
