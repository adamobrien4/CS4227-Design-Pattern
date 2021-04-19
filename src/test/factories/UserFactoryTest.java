package test.factories;

import main.entities.users.User;
import main.factories.UserFactory;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.HashMap;

public class UserFactoryTest {

    UserFactory factory;

    @Before
    public void setUp() {
        factory = new UserFactory();
    }

    @Test
    public void createCustomer() {
        HashMap<String, String> userData = new HashMap<>();

        userData.put("type", User.CUSTOMER);
        userData.put("_id", "607c33d9900b360004c4ad18");
        userData.put("email", "email@exmaple.com");
        userData.put("password", "password");
        userData.put("address", "Limerick, Ireland");

        User customer1 = factory.createUser(userData);

        assertTrue(customer1.getType().equals(User.CUSTOMER));
    }

    @Test
    public void createRestaurantOwner() {
        HashMap<String, String> userData = new HashMap<>();

        userData.put("type", User.RESTAURANT_OWNER);
        userData.put("_id", "607c33d9900b360004c4ad18");
        userData.put("email", "email@exmaple.com");
        userData.put("password", "password");
        userData.put("restaurant", "617c33d9900b360004c4ad18");

        User owner = factory.createUser(userData);

        assertTrue(owner.getType().equals(User.RESTAURANT_OWNER));
    }
}
