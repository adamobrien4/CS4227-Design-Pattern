package main.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import main.Globals;
import main.entities.businesses.locationTypes.FamilyFriendly;
import main.entities.businesses.locationTypes.Location;
import main.exceptions.APIException;
import main.services.HttpService;
import main.services.POJOMapper;

import java.util.ArrayList;

public class RestaurantDaoImpl implements Dao<Location> {

    protected static RestaurantDaoImpl instance;

    public static RestaurantDaoImpl getInstance() {
        if(instance == null) {
            instance = new RestaurantDaoImpl();
        }
        return instance;
    }

    @Override
    public Location get(String id) {
        String response = HttpService.get(Globals.APPLICATION_API_URL + "/restaurant/" + id);

        System.out.println("Response is:");
        System.out.println(response);

        try {
            return POJOMapper.getMapper().readValue(response, new TypeReference<FamilyFriendly>() {});
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
        String response = null;
        try {
            System.out.println(HttpService.post(Globals.APPLICATION_API_URL + "/restaurant/add/" + Globals.getLoggedInUser().getEmail(), POJOMapper.getMapper().writeValueAsString(restaurant)));
            response = HttpService.post(Globals.APPLICATION_API_URL + "/restaurant/add/" + Globals.getLoggedInUser().getEmail(), POJOMapper.getMapper().writeValueAsString(restaurant));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        if(response.equals("\"Restaurant Added\"")) {
            return true;
        } else {
            throw new APIException(response);
        }
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
