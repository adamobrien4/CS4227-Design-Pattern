import main.dao.CustomerDaoImpl;
import main.dao.UserDaoImpl;
import main.entities.users.Customer;
import main.entities.users.User;

public class DaoTest {
    public static void main(String[] args) {
        UserDaoImpl userDaoImpl = new UserDaoImpl();

        User user = userDaoImpl.getByEmailAndPassword("Adam@gmail.com", "a");

        System.out.println(user.toString());
    }
}
