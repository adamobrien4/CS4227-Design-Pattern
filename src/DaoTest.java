import main.dao.CustomerDaoImpl;
import main.data_layer.DatabaseRepository;
import main.entities.Customer;

public class DaoTest {
    public static void main(String[] args) {
        DatabaseRepository.setup();

        CustomerDaoImpl customerDaoImpl = new CustomerDaoImpl();

        Customer c = customerDaoImpl.get("603d4a73c495bf5da092f88a");

        customerDaoImpl.delete(c);
    }
}
