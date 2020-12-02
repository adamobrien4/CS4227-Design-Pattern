package test.entities;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.*;

import main.entities.Customer;

public class CustomerTest {
  @Test
  public void test_to_string() {

    int customerID = 1;
    String customerEmail = "customer@mail.com";

    Customer customer = new Customer(customerID, customerEmail);

    String custToString = customer.toString();
    assertEquals("Customer.toString() failed to return the expected value", "ID: "+customerID+"\nEmail: "+customerEmail+"\n", custToString);
  }
}
