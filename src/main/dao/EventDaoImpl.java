package main.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;

import main.Globals;
import main.entities.Event;
import main.exceptions.APIException;
import main.services.HttpService;
import main.services.POJOMapper;

import java.util.HashMap;

public class EventDaoImpl implements Dao<Event> {

    @Override
    public Event get(String id) {
        String response = HttpService.get(Globals.APPLICATION_API_URL + "/event/" + id);

        try {
            return POJOMapper.getMapper().readValue(response, new TypeReference<Event>() {});
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean insert(Event t) throws APIException {
        String response = null;
        try {
            response = HttpService.post(Globals.APPLICATION_API_URL + "/event/add", POJOMapper.getMapper().writeValueAsString(t));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        if(response.equals("\"Event Added\"")) {
            return true;
        } else {
            throw new APIException(response);
        }
    }

    @Override
    public Event update(Event t) throws APIException {
        return null;
    }

    public boolean payForOrder()
    {
        String response = null;

        HashMap<String, String> details = new HashMap<>();

        details.put("customer", Globals.getLoggedInUser().getId().toString());
        details.put("location",Globals.getRestaurant().getId().toString());

        try {
            response = HttpService.post(Globals.APPLICATION_API_URL + "/event/pay", POJOMapper.getMapper().writeValueAsString(details));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        if(response.equals("\"Event Paid\"")) {
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Event t) throws APIException {
        return false;
    }
    
}
