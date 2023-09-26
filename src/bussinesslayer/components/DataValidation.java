package bussinesslayer.components;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataValidation {
    public static boolean isValidEmail(String email) {
        // Kiểm tra định dạng email
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        return email.matches(emailRegex);
    }

    public static boolean isInRange(int number, int min, int max) {
        // check if number is between min and max
        return number >= min && number <= max;
    }
    //Method to validate username
    public static boolean isValidName(String userName) {
        return userName.length() >= 5 && userName.length() <= 20;
    }

    // Method to validate password
    public static boolean isValidPassword(String password) {
        return password.length() >= 8 && password.length() <= 30;
    }
    // Method to validate phone number
    public static boolean isValidPhoneNumber(String phoneNumber) {
        // Biểu thức chính quy để kiểm tra cú pháp số điện thoại
        String phoneRegex = "^\\d{10}$"; // Định dạng 10 chữ số

        Pattern pattern = Pattern.compile(phoneRegex);
        Matcher matcher = pattern.matcher(phoneNumber);

        return matcher.matches();
    }
    // Method to validate full name
    public static boolean isValidFullName(String name) {
        return name.length() < 30;
    }

    // Method to validate identity number
    public static boolean isValidIdentityNumber(String str) {
        return str.length() < 30;
    }

}
