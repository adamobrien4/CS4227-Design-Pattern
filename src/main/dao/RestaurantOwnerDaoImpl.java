package main.dao;

import com.fasterxml.jackson.core.JsonProcessingException;

import main.Globals;
import main.entities.users.RestaurantOwner;
import main.exceptions.APIException;
import main.services.HttpService;
import main.services.POJOMapper;

public class RestaurantOwnerDaoImpl implements Dao<RestaurantOwner>{

    protected static RestaurantOwnerDaoImpl instance;

    public static RestaurantOwnerDaoImpl getInstance() {
        if(instance == null) {
            instance = new RestaurantOwnerDaoImpl();
        }
        return instance;
    }

    @Override
    public RestaurantOwner get(String id) {
        return null;
    }

    @Override
    public boolean insert(RestaurantOwner owner) throws APIException {
        String response = null;
        try {
            response = HttpService.post(Globals.APPLICATION_API_URL + "/owner/add", POJOMapper.getMapper().writeValueAsString(owner));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        if(response.equals("\"Restaurant Owner Added\"")) {
            return true;
        } else {
            throw new APIException(response);
        }
    }

    @Override
    public RestaurantOwner update(RestaurantOwner t) throws APIException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean delete(RestaurantOwner t) throws APIException {
        // TODO Auto-generated method stub
        return false;
    }
    
}
