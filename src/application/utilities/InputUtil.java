package application.utilities;

import java.util.Scanner;

public class InputUtil {
    private static final Scanner scanner = new Scanner(System.in);

    public static int readInt(String message) throws Exception {
        int number = 0;
        System.out.print(message);
        String input = scanner.nextLine();
        if (!input.matches("[-+]?\\d+")) {
            throw new Exception("Data invalid.");
        } else {
            number = Integer.parseInt(input);
        }
        return number;
    }

    public static String readString(String message) {
        System.out.print(message);
        String input = scanner.nextLine();
        return input;
    }

    public static double readDouble(String message) throws Exception {
        System.out.print(message);
        double number = 0.0;
        String input = scanner.nextLine();
        if (!input.matches("^[-+]?\\d*\\.?\\d+([eE][-+]?\\d+)?$")) {
            throw new Exception("Data invalid.");
        } else {
            number = Double.parseDouble(input);
        }
        return number;
    }

    // Các phương thức khác cho việc đọc các kiểu dữ liệu khác như boolean, long, vv. có thể được thêm vào

    public static void closeScanner() {
        scanner.close();
    }
}