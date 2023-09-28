package application.utilities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class InputUtil {
    private static final Scanner scanner = new Scanner(System.in);

    public static int readInt(String message) throws Exception {
        int number = 0;
        System.out.print(message);
        String input = scanner.nextLine();
        if(input.isBlank()) {
            return 0;
        }
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
        if(input.isBlank()) {
            return "";
        }
        return input;
    }

    public static double readDouble(String message) throws Exception {
        System.out.print(message);
        double number = 0.0;
        String input = scanner.nextLine();
        if(input.isBlank()) {
            return -1;
        }
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

    public static LocalDate readLocalDate(String message) throws Exception {
        System.out.print(message);
        String input = scanner.nextLine();
        LocalDate date = null;

        if(input.isBlank()) {
            return null;
        }
        if (!input.matches("^\\d{4}-\\d{2}-\\d{2}$")) { // YYYY-MM-DD
            throw new Exception("Data invalid.");
        }else {
            date = LocalDate.parse(input);
        }
        return date;
    }
    public static LocalTime readLocalTime(String message) throws Exception {
        System.out.print(message);
        String input = scanner.nextLine();
        LocalTime time = null;
        if(input.isBlank()) {
            return null;
        }
        if (!input.matches("^\\d{2}:\\d{2}$")) { // HH:MM
            throw new Exception("Data invalid.");
        }else {
            time = LocalTime.parse(input);
        }
        return time;
    }
    public static boolean readBoolean(String message) throws Exception {
        System.out.print(message);
        String input = scanner.nextLine();
        boolean bool = false;
        if(input.isBlank()) {
            return false;
        }
        if (!input.matches("true|false")) {
            throw new Exception("Data invalid.");
        } else {
            bool = Boolean.parseBoolean(input);
        }
        return bool;
    }
}