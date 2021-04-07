package main.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import main.Globals;
import main.entities.Menu;
import main.entities.users.Customer;
import main.exceptions.APIException;
import main.services.HttpService;
import main.services.POJOMapper;

public class MenuDaoImpl implements Dao<Menu> {
    @Override
    public Menu get(String id) {
        String response = HttpService.get(Globals.APPLICATION_API_URL + "/menu/" + id);

        try {
            return POJOMapper.getMapper().readValue(response, new TypeReference<Menu>() {});
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean insert(Menu menu) throws APIException {
        String response = null;
        try {
            response = HttpService.post(Globals.APPLICATION_API_URL + "/menu/add", POJOMapper.getMapper().writeValueAsString(menu));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        if(response.equals("\"Menu Added\"")) {
            return true;
        } else {
            throw new APIException(response);
        }
    }

    @Override
    public Menu update(Menu menu) throws APIException {
        return null;
    }

    @Override
    public boolean delete(Menu menu) throws APIException {
        return false;
    }
}
