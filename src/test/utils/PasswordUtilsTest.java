package test.utils;

import org.junit.*;

import main.utils.PasswordUtils;

import static org.junit.Assert.*;

public class PasswordUtilsTest {
  @Test
  public void test_encryptPassword() {

    String password = "my_password";
    String encrytedPassword = "m109y121_95p112a97s115s115w119o111r114d100";

    String result = PasswordUtils.encryptPassword(password);

    assertTrue(result.equals(encrytedPassword));
  }

  @Test
  public void test_encryptPassword2() {

    String password = "password";
    String encrytedPassword = "m109y121_95p112a97s115s115w119o111r114d100";

    String result = PasswordUtils.encryptPassword(password);
    System.out.println(result);

    assertTrue(result.equals(encrytedPassword));
  }
}
