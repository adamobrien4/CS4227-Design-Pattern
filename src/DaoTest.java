import main.dao.RestaurantDaoImpl;

public class DaoTest {
    public static void main(String[] args) {
        RestaurantDaoImpl restaurantDao = new RestaurantDaoImpl();

        restaurantDao.getAll();
    }
}
