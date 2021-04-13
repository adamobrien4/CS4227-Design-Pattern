package main.dao;

import main.entities.EventItems;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import main.Globals;
import main.entities.Menu;
import main.entities.users.Customer;
import main.exceptions.APIException;
import main.services.HttpService;
import main.services.POJOMapper;

public class EventItemsDaoImpl implements Dao<EventItems>{

    public EventItems get(String id) {
        String response = HttpService.get(Globals.APPLICATION_API_URL + "/EventItems/" + id);

        try {
            return POJOMapper.getMapper().readValue(response, new TypeReference<EventItems>() {});
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    public boolean insert(EventItems eventItems) throws APIException {
        String response = null;
        try {
            response = HttpService.post(Globals.APPLICATION_API_URL + "/EventItems/add", POJOMapper.getMapper().writeValueAsString(eventItems));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        if(response.equals("\"EventItems Added\"")) {
            return true;
        } else {
            throw new APIException(response);
        }
    }

    @Override
    public EventItems update(EventItems t) throws APIException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean delete(EventItems t) throws APIException {
        // TODO Auto-generated method stub
        return false;
    }
    
}
