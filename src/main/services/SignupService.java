package main.services;

import main.dao.CustomerDaoImpl;
import main.entities.users.Customer;
import main.exceptions.APIException;

public class SignupService {

    CustomerDaoImpl customerDao;

    public SignupService() {
        System.out.println("Created new Signup Service");
        customerDao = new CustomerDaoImpl();
    }

    public boolean signupUser(Customer newUser)throws APIException {
        return customerDao.insert(newUser);
    }
}
