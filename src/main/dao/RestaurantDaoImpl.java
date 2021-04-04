package main.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import main.Globals;
import main.dao.Dao;
import main.entities.Businesses.LocationTypes.Location;
import main.exceptions.APIException;
import main.services.HttpService;
import main.services.POJOMapper;

import java.util.ArrayList;

public class RestaurantDaoImpl implements Dao<Location> {
    @Override
    public Location get(String id) {
        String response = HttpService.get(Globals.APPLICATION_API_URL + "/restaurant/" + id);

        System.out.println("Response is:");
        System.out.println(response);

        try {
            return POJOMapper.getMapper().readValue(response, new TypeReference<Location>() {});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Location> getAll() {
        String response = HttpService.get(Globals.APPLICATION_API_URL + "/restaurant/");

        System.out.println("Response is:");
        System.out.println(response);

        try {
            return POJOMapper.getMapper().readValue(response, new TypeReference<ArrayList<Location>>(){});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    @Override
    public boolean insert(Location restaurant) throws APIException {
        return false;
    }

    @Override
    public Location update(Location restaurant) throws APIException {
        return null;
    }

    @Override
    public boolean delete(Location restaurant) throws APIException {
        return false;
    }
}
