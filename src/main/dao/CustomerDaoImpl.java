package main.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import main.Globals;
import main.entities.users.Customer;
import main.exceptions.APIException;
import main.services.HttpService;
import main.services.POJOMapper;

public class CustomerDaoImpl implements Dao<Customer> {

    @Override
    public Customer get(String id) {
        String response = HttpService.get(Globals.APPLICATION_API_URL + "/user/" + id);

        try {
            return POJOMapper.getMapper().readValue(response, new TypeReference<Customer>() {});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean insert(Customer customer) throws APIException {
        String response = null;
        try {
            response = HttpService.post(Globals.APPLICATION_API_URL + "/user/add", POJOMapper.getMapper().writeValueAsString(customer));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        if(response == "Customer Added") {
            return true;
        } else {
            throw new APIException(response);
        }
    }

    @Override
    public Customer update(Customer customer) {
        // TODO: Implement functionality for editing Customer
        return null;
    }

    @Override
    public boolean delete(Customer customer) {
        String response = HttpService.get(Globals.APPLICATION_API_URL + "/user/" + customer.getId());

        return response.equals("Customer Deleted");
    }
}
