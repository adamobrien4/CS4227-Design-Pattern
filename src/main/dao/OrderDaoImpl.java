package main.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import main.Globals;
import main.entities.Order;
import main.entities.users.Customer;
import main.exceptions.APIException;
import main.services.HttpService;
import main.services.POJOMapper;
import org.bson.types.ObjectId;

import java.util.HashMap;

public class OrderDaoImpl implements Dao<Order> {
    @Override
    public Order get(String id) {
        return null;
    }

    @Override
    public boolean insert(Order order) throws APIException {
        String response = null;
        try {
            response = HttpService.post(Globals.APPLICATION_API_URL + "/order/add", POJOMapper.getMapper().writeValueAsString(order));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        if(response.equals("\"Order Added\"")) {
            return true;
        } else {
            throw new APIException(response);
        }
    }

    @Override
    public Order update(Order order) throws APIException {
        return null;
    }

    public boolean payForOrder() {
        String response = null;

        HashMap<String, String> details = new HashMap<>();

        details.put("customer", Globals.getLoggedInUser().getId().toString());
        details.put("restaurant",Globals.getRestaurant().getId().toString());

        try {
            response = HttpService.post(Globals.APPLICATION_API_URL + "/order/pay", POJOMapper.getMapper().writeValueAsString(details));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        if(response.equals("\"Order Paid\"")) {
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Order order) throws APIException {
        return false;
    }
}
