package main.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import main.Globals;
import main.entities.Discount;
import main.exceptions.APIException;
import main.services.HttpService;
import main.services.POJOMapper;

public class DiscountDaoImpl implements Dao<Discount> {

    private static DiscountDaoImpl instance = null;

    public static DiscountDaoImpl  getInstance() {
        if(instance == null) {
            instance = new DiscountDaoImpl();
        }
        return instance;
    }

    @Override
    public Discount get(String code) {
        String response = HttpService.get(Globals.APPLICATION_API_URL + "/discount/" + code);

        System.out.println(response);

        try {
            return POJOMapper.getMapper().readValue(response, Discount.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean insert(Discount discount) throws APIException {
        return false;
    }

    @Override
    public Discount update(Discount discount) throws APIException {
        return null;
    }

    @Override
    public boolean delete(Discount discount) throws APIException {
        return false;
    }
}
