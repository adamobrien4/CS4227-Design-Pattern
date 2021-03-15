import com.fasterxml.jackson.core.JsonProcessingException;
import main.Globals;
import main.dao.MenuDaoImpl;
import main.dao.OrderDaoImpl;
import main.dao.RestaurantDaoImpl;
import main.entities.Menu;
import main.entities.Order;
import main.entities.users.Customer;
import main.services.POJOMapper;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.HashMap;

public class DaoTest {
    public static void main(String[] args) {
        Customer c = new Customer(new ObjectId(), "email@gmail.com", "Password", "Oak St.");

        Globals.setLoggedInUser(c);

        HashMap<String, String> details = new HashMap<>();

        details.put("email", Globals.getLoggedInUser().getEmail());
        details.put("restaurant", new ObjectId().toString());

        try {
            System.out.println(POJOMapper.getMapper().writeValueAsString(details));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
