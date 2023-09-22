package application.utilities;

import java.util.Scanner;

public class InputUtil {
    private static final Scanner scanner = new Scanner(System.in);

    public static int readInt(String message) {
        System.out.print(message);
        int input = 0;
        try {
            input = scanner.nextInt();
            scanner.nextLine(); // Xóa bộ nhớ đệm sau khi nhập số nguyên
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
            scanner.nextLine(); // Xóa bộ nhớ đệm nếu có lỗi xảy ra
        }

        return input;
    }

    public static String readString(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    public static double readDouble(String message) {
        System.out.print(message);
        double input = 0.0;
        try {
            input = scanner.nextDouble();
            scanner.nextLine(); // Xóa bộ nhớ đệm sau khi nhập số thực
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
            scanner.nextLine(); // Xóa bộ nhớ đệm nếu có lỗi xảy ra
        }
        return input;
    }

    public static float readFloat(String message) {
        System.out.print(message);
        float input = 0.0f;
        try {
            input = scanner.nextFloat();
            scanner.nextLine(); // Xóa bộ nhớ đệm sau khi nhập số thực
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
            scanner.nextLine(); // Xóa bộ nhớ đệm nếu có lỗi xảy ra
        }
        return input;
    }

    // Các phương thức khác cho việc đọc các kiểu dữ liệu khác như boolean, long, vv. có thể được thêm vào

    public static void closeScanner() {
        scanner.close();
    }
}