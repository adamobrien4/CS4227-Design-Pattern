import main.dao.CustomerDaoImpl;
import main.data_layer.DatabaseRepository;

public class DaoTest {
    public static void main(String[] args) {
        DatabaseRepository.setup();

        CustomerDaoImpl customerDaoImpl = new CustomerDaoImpl();

        customerDaoImpl.get("5fd3e7bd3ba9fcd30da1b5f1");
    }
}
