package test.dao;

import main.Globals;
import main.dao.RestaurantDaoImpl;
import main.entities.businesses.locationTypes.FamilyFriendly;
import main.entities.businesses.locationTypes.Location;
import main.entities.users.Customer;
import main.exceptions.APIException;
import main.services.HttpService;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

public class RestaurantDaoImplTest {

    @Test
    public void getRestaurantDaoInstance() {
        assertNotNull(RestaurantDaoImpl.getInstance());
    }

    @Test
    public void getRestaurantById() {

        try (MockedStatic<HttpService> theMock = Mockito.mockStatic(HttpService.class)) {
            ObjectId exampleObjectId = new ObjectId("60798e4f946a682d40281a2d");
            String name = "Example Restaurant";
            String genre = "Fast Food";
            ObjectId menuId = new ObjectId("60798e4f946a682d40281a2c");
            ObjectId ownerId = new ObjectId("60798e4f946a682d40281a2d");

            theMock.when(() -> { HttpService.get(any(String.class)); }).thenReturn("{" +
                    "\"_id\": \""+ exampleObjectId.toHexString() + "\"," +
                    "\"name\": \""+ name +"\"," +
                    "\"genre\": \""+ genre +"\"," +
                    "\"menu\": \""+ menuId.toHexString() + "\"," +
                    "\"type\": \"Family_Friendly\"," +
                    "\"owner\": \""+ ownerId.toHexString() + "\"" +
                    "}");

            Location loc = new FamilyFriendly(exampleObjectId, name, genre, menuId);

            assertTrue(loc.equals(RestaurantDaoImpl.getInstance().get("123")));
        }
    }

    @Test
    public void getAllRestaurant() {
        try (MockedStatic<HttpService> mockedHTTPService = Mockito.mockStatic(HttpService.class)) {
            ObjectId exampleObjectId1 = new ObjectId("60798e4f946a682d40281a2d");
            ObjectId exampleObjectId2 = new ObjectId("70798e4f946a682d40281a2d");

            String name1 = "Example Restaurant One";
            String name2 = "Example Restaurant Two";

            String genre = "Fast Food";

            ObjectId menuId1 = new ObjectId("60798e4f946a682d40281a2c");
            ObjectId menuId2 = new ObjectId("70798e4f946a682d40281a2c");

            ObjectId ownerId1 = new ObjectId("60798e4f946a682d40281a2d");
            ObjectId ownerId2 = new ObjectId("70798e4f946a682d40281a2d");

            mockedHTTPService.when(() -> { HttpService.get(any(String.class)); }).thenReturn("[{" +
                    "\"_id\": \""+ exampleObjectId1.toHexString() + "\"," +
                    "\"name\": \""+ name1 +"\"," +
                    "\"genre\": \""+ genre +"\"," +
                    "\"menu\": \""+ menuId1.toHexString() + "\"," +
                    "\"type\": \"Family_Friendly\"," +
                    "\"owner\": \""+ ownerId1.toHexString() + "\"" +
                    "},{" +
                    "\"_id\": \""+ exampleObjectId2.toHexString() + "\"," +
                    "\"name\": \""+ name2 +"\"," +
                    "\"genre\": \""+ genre +"\"," +
                    "\"menu\": \""+ menuId2.toHexString() + "\"," +
                    "\"type\": \"Family_Friendly\"," +
                    "\"owner\": \""+ ownerId2.toHexString() + "\"" +
                    "}]");

            Location loc1 = new FamilyFriendly(exampleObjectId1, name1, genre, menuId1);
            Location loc2 = new FamilyFriendly(exampleObjectId2, name2, genre, menuId2);

            ArrayList<Location> locs = RestaurantDaoImpl.getInstance().getAll();

            assertTrue(locs.get(0).equals(loc1));
            assertTrue(locs.get(1).equals(loc2));
        }
    }

    @Test
    public void insertRestaurant() {
        try (MockedStatic<HttpService> theMock = Mockito.mockStatic(HttpService.class)) {
            ObjectId exampleObjectId = new ObjectId("60798e4f946a682d40281a2d");
            String name = "Example Restaurant";
            String genre = "Fast Food";
            ObjectId menuId = new ObjectId("60798e4f946a682d40281a2c");

            theMock.when(() -> { HttpService.post(any(String.class), any(String.class)); }).thenReturn("\"Restaurant Added\"");

            Globals.setLoggedInUser(new Customer("example@example.com", "Password", "Example Address, Limerick"));

            System.out.println(HttpService.post("ABC", "{abc}"));

            Location newLoc = new FamilyFriendly(exampleObjectId, name, genre, menuId);

            try {
                assertTrue(RestaurantDaoImpl.getInstance().insert(newLoc));
            } catch (APIException e) {
                e.printStackTrace();
            }
        }
    }

}
