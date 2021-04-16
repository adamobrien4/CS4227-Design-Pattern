package main.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import main.Globals;
import main.entities.Menu;
import main.exceptions.APIException;
import main.services.HttpService;
import main.services.POJOMapper;

public class MenuDaoImpl implements Dao<Menu> {

    private static MenuDaoImpl instance = null;

    public static MenuDaoImpl  getInstance() {
        if(instance == null) {
            instance = new MenuDaoImpl();
        }
        return instance;
    }

    @Override
    public Menu get(String id) {
        String response = HttpService.get(Globals.APPLICATION_API_URL + "/menu/" + id);

        System.out.println(response);

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
            response = HttpService.post(Globals.APPLICATION_API_URL + "/menu/add/" + Globals.getLoggedInUser().getEmail(), POJOMapper.getMapper().writeValueAsString(menu));
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
