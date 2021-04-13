package main.dao;

import com.fasterxml.jackson.core.JsonProcessingException;

import main.Globals;
import main.entities.businesses.Owners.Owner;
import main.exceptions.APIException;
import main.services.HttpService;
import main.services.POJOMapper;

public class OwnerDaoImpl implements Dao<Owner>{

    @Override
    public Owner get(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean insert(Owner owner) throws APIException {
        String response = null;
        try {
            response = HttpService.post(Globals.APPLICATION_API_URL + "/owner/add", POJOMapper.getMapper().writeValueAsString(owner));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        if(response.equals("\"Owner Added\"")) {
            return true;
        } else {
            throw new APIException(response);
        }
    }

    @Override
    public Owner update(Owner t) throws APIException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean delete(Owner t) throws APIException {
        // TODO Auto-generated method stub
        return false;
    }
    
}
