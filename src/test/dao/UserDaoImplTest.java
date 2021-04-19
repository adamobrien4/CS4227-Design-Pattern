package test.dao;

import main.dao.UserDaoImpl;
import main.entities.users.Customer;
import main.services.HttpService;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;

public class UserDaoImplTest {
    @Test
    public void getUserByEmailAndPassword() {
        String email = "email@example.com";
        String password = "PaSsWoRd";
        ObjectId userId = new ObjectId("607c33d9900b360004c4ad18");
        String address = "Limerick, Ireland";

        try (MockedStatic<HttpService> mockedHTTPService = Mockito.mockStatic(HttpService.class)) {
            mockedHTTPService.when(() -> { HttpService.post(any(String.class), any(String.class)); }).thenReturn("{\"_id\": \""+ userId.toHexString() +"\", \"address\": \""+ address +"\", \"type\": \"customer\", \"email\": \""+ email +"\", \"password\": \""+ password +"\"}");

            UserDaoImpl inst = new UserDaoImpl();

            Customer user1 = (Customer)inst.getByEmailAndPassword("email", "password");
            Customer user2 = new Customer(userId, email, password, address);

            assertTrue(user1.equals(user2));
        }
    }
}
