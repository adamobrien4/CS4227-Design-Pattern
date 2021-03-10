package main.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import main.Globals;
import main.entities.users.Customer;
import main.entities.users.Driver;
import main.entities.users.RestaurantOwner;
import main.entities.users.User;
import main.services.HttpService;
import main.services.POJOMapper;
import main.utils.PasswordUtils;

import java.net.ConnectException;
import java.util.HashMap;

public class UserDaoImpl {
    public User getByEmailAndPassword(String email, String password) {
        HashMap<String, String> request = new HashMap<>();
        request.put("email", email);
        request.put("password", PasswordUtils.encryptPassword(password));

        String response = "";

        try {
            response = HttpService.post(Globals.APPLICATION_API_URL + "/login", POJOMapper.getMapper().writeValueAsString(request));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }

        User user = null;

        try {
            user = POJOMapper.getMapper().readValue(response, new TypeReference<User>(){});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }

        if(user == null) {
            return null;
        }

        System.out.println(user.toString());

        TypeReference tr = null;

        switch(user.getType()) {
            case User.CUSTOMER:
                tr = new TypeReference<Customer>() {};
                break;
            case User.DRIVER:
                tr = new TypeReference<Driver>() {};
                break;
            case User.RESTAURANT_OWNER:
                tr = new TypeReference<RestaurantOwner>() {};
                break;
            default:
                System.out.println("Unknown user login type");
                return null;
        }

        try {
            return (User) POJOMapper.getMapper().readValue(response, tr);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
