package main.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import main.Globals;
import main.entities.users.Customer;
import main.entities.users.Driver;
import main.entities.users.RestaurantOwner;
import main.entities.users.User;
import main.factories.UserFactory;
import main.services.HttpService;
import main.services.POJOMapper;
import main.utils.PasswordUtils;

import java.util.HashMap;
import java.util.Map;

public class UserDaoImpl {

    private UserFactory userFactory;

    public UserDaoImpl() {
        this.userFactory = new UserFactory();
    }

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

        System.out.println(response);

        try {
            // Get type of user
            Map<String, String> userMap = POJOMapper.getMapper().readValue(response, Map.class);

            user = userFactory.createUser(userMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }

        if(user == null) {
            return null;
        }

        return user;
    }
}
