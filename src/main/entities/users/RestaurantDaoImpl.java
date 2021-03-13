package main.entities.users;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import main.Globals;
import main.dao.Dao;
import main.entities.Restaurant;
import main.exceptions.APIException;
import main.services.HttpService;
import main.services.POJOMapper;

import java.util.ArrayList;

public class RestaurantDaoImpl implements Dao<Restaurant> {
    @Override
    public Restaurant get(String id) {
        String response = HttpService.get(Globals.APPLICATION_API_URL + "/restaurant/" + id);

        System.out.println("Response is:");
        System.out.println(response);

        try {
            return POJOMapper.getMapper().readValue(response, new TypeReference<Restaurant>() {});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Restaurant> getAll() {
        String response = HttpService.get(Globals.APPLICATION_API_URL + "/restaurant/");

        System.out.println("Response is:");
        System.out.println(response);

        try {
            return POJOMapper.getMapper().readValue(response, new TypeReference<ArrayList<Restaurant>>(){});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    @Override
    public boolean insert(Restaurant restaurant) throws APIException {
        return false;
    }

    @Override
    public Restaurant update(Restaurant restaurant) throws APIException {
        return null;
    }

    @Override
    public boolean delete(Restaurant restaurant) throws APIException {
        return false;
    }
}
