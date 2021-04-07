package main.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;

import main.Globals;
import main.entities.FoodItem;
import main.exceptions.APIException;
import main.services.HttpService;
import main.services.POJOMapper;

public class FoodItemDaoImpl implements Dao<FoodItem>{

    @Override
    public FoodItem get(String id) throws APIException {
        String response = HttpService.get(Globals.APPLICATION_API_URL + "/FoodItem/" + id);

        try {
            return POJOMapper.getMapper().readValue(response, new TypeReference<FoodItem>() {});
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean insert(FoodItem t) throws APIException {
        String response = null;
        try {
            response = HttpService.post(Globals.APPLICATION_API_URL + "/FoodItem/add", POJOMapper.getMapper().writeValueAsString(t));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        if(response.equals("\"FoodItem Added\"")) {
            return true;
        } else {
            throw new APIException(response);
        }
    }

    @Override
    public FoodItem update(FoodItem t) throws APIException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean delete(FoodItem t) throws APIException {
        // TODO Auto-generated method stub
        return false;
    }
    
}
