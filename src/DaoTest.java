import main.dao.CustomerDaoImpl;
import main.data_layer.DatabaseRepository;
import main.entities.Customer;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;

public class DaoTest {
    public static void main(String[] args) {
        CustomerDaoImpl customerDaoImpl = new CustomerDaoImpl();

        Customer c1 = new Customer("Adam@gmail.com", "Psad", "Oak Road, Limerick");

        Customer c = customerDaoImpl.get("60461c849bc2cc36400db595");

        //Boolean r = customerDaoImpl.insert(c1);

        System.out.println(c);
    }
}
