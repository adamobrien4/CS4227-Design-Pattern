import main.data_layer.DatabaseRepository;
import main.entities.Customer;

public class FoodOrderingApplication {
    public static void main(String args[]) {
        DatabaseRepository db = new DatabaseRepository();

        db.test();



        // Customer c1 = Customer.fromDocument(db.getUserByName("Adam"));

        // System.out.println(c1.toString());
    }
}
