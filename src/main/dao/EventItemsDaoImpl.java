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
        String response = HttpService.get(Globals.APPLICATION_API_URL + "/menu/" + id);

        try {
            return POJOMapper.getMapper().readValue(response, new TypeReference<EventItems>() {});
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean insert(EventItems t) throws APIException {
        // TODO Auto-generated method stub
        return false;
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
