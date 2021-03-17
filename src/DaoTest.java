import com.fasterxml.jackson.core.JsonProcessingException;
import main.Globals;
import main.dao.DiscountDaoImpl;
import main.dao.MenuDaoImpl;
import main.dao.OrderDaoImpl;
import main.dao.RestaurantDaoImpl;
import main.entities.Discount;
import main.entities.Menu;
import main.entities.Order;
import main.entities.users.Customer;
import main.services.POJOMapper;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.HashMap;

public class DaoTest {
    public static void main(String[] args) {
        DiscountDaoImpl discountDao = new DiscountDaoImpl();

        Discount d = discountDao.get("DEL");

        System.out.println(d.toString());
    }
}
