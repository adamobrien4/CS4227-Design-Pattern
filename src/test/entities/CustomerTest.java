package test.entities;

import org.bson.types.ObjectId;
import org.junit.*;
import static org.junit.Assert.*;

import main.entities.users.Customer;

public class CustomerTest {
  @Test
  public void test_to_string() {

    ObjectId customerID = new ObjectId();
    String customerEmail = "customer@mail.com";
    String password = "password";
    String address = "Apt. 5, Limerick Road";

    Customer customer = new Customer(customerID, customerEmail, password, address);

    String custToString = customer.toString();
    assertEquals("Customer.toString() failed to return the expected value", "ID: "+customerID+"\nEmail: "+customerEmail+"\n", custToString);
  }
}
