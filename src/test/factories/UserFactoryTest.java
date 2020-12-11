package test.factories;

import org.junit.*;

import main.entities.User;
import main.factories.UserFactory;

import static org.junit.Assert.*;

import org.bson.Document;

public class UserFactoryTest {
    Document userDoc;
    UserFactory userFactory;

    @Before
    public void setup() {
        userDoc = null;
        userFactory = new UserFactory();
    }

    @Test
    public void customerUserFactoryTest() {

        String email = "user@gmail.com";
        String password = "abc";
        String type = User.CUSTOMER;

        userDoc = new Document("email", email);
        userDoc.append("password", password);
        userDoc.append("type", type);

        User u = userFactory.createUser(userDoc);

        assertTrue(u.getType().equals(type));
    }

    @Test
    public void deliveryUserFactoryTest() {

        String email = "user@gmail.com";
        String password = "abc";
        String type = User.DRIVER;

        userDoc = new Document("email", email);
        userDoc.append("password", password);
        userDoc.append("type", type);

        User u = userFactory.createUser(userDoc);

        assertTrue(u.getType().equals(type));
    }

    @Test
    public void restaurantUserFactoryTest() {

        String email = "user@gmail.com";
        String password = "abc";
        String type = User.RESTAURANT_OWNER;

        userDoc = new Document("email", email);
        userDoc.append("password", password);
        userDoc.append("type", type);

        User u = userFactory.createUser(userDoc);

        assertTrue(u.getType().equals(type));
    }
}
