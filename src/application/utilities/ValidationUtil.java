package application.utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtil {
    public static boolean isValidEmail(String email) {
        // Kiểm tra định dạng email
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        return email.matches(emailRegex);
    }

    public static boolean isInRange(int number, int min, int max) {
        // Kiểm tra số nguyên nằm trong khoảng
        return number >= min && number <= max;
    }

    public static boolean isValidUserName(String userName) {
        return userName.length() >= 5 && userName.length() <= 20;
    }
    // Các phương thức khác cho việc kiểm tra dữ liệu đầu vào có thể được thêm vào
    public static boolean isValidPassword(String password) {
        return password.length() >= 8 && password.length() <= 30;
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        // Biểu thức chính quy để kiểm tra cú pháp số điện thoại
        String phoneRegex = "^\\d{10}$"; // Định dạng 10 chữ số

        Pattern pattern = Pattern.compile(phoneRegex);
        Matcher matcher = pattern.matcher(phoneNumber);

        return matcher.matches();
    }
    public static boolean isValidFullName(String name) {
        return name.length() < 30;
    }

    public static boolean isValidIdentityNumber(String str) {
        return str.length() < 30;
    }
}
