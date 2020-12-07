package main.utils;

public class PasswordUtils {
    private PasswordUtils(){}

    public static String encryptPassword(String password) {
        char[] passwordChars = password.toCharArray();
        String encryptedPwd = "";

        for(char c : passwordChars) {
            int n = (int)c;

            encryptedPwd += c + Integer.toString(n);
        }

        return encryptedPwd;
    }
}