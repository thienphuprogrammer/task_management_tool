package application.utilities;

import com.mysql.cj.protocol.Message;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Locale;
import java.util.Scanner;

public class InputUtil {
    private static final Scanner scanner = new Scanner(System.in);

    // Read int
    public static int readInt(String message) throws Exception {
        int number;
        System.out.print(message);
        String input = scanner.nextLine();
        if (!input.matches("[-+]?\\d+")) {
            throw new Exception("Data invalid.");
        } else {
            number = Integer.parseInt(input);
        }
        return number;
    }
    public static int readInt(String message, int defaultValue) throws Exception {
        int number;
        System.out.print(message);
        String input = scanner.nextLine();
        if (input.isBlank()) {
            return defaultValue;
        }
        if (!input.matches("[-+]?\\d+")) {
            throw new Exception("Data invalid.");
        } else {
            number = Integer.parseInt(input);
        }
        return number;
    }

    // Read string
    public static String readString(String message) {
        System.out.print(message);
        String input = scanner.nextLine();
        if(input.isBlank()) {
            return "";
        }
        return input;
    }
    public static String readString(String message, String defaultValue) {
        System.out.print(message);
        String input = scanner.nextLine();
        if(input.isBlank()) {
            return defaultValue;
        }
        return input;
    }

    // Read double
    public static double readDouble(String message) throws Exception {
        System.out.print(message);
        double number;
        String input = scanner.nextLine();
        if (!input.matches("^[-+]?\\d*\\.?\\d+([eE][-+]?\\d+)?$")) {
            throw new Exception("Data invalid.");
        } else {
            number = Double.parseDouble(input);
        }
        return number;
    }
    public static double readDouble(String message, double defaultValue) throws Exception {
        System.out.print(message);
        double number;
        String input = scanner.nextLine();
        if (input.isBlank()) {
            return defaultValue;
        }
        if (!input.matches("^[-+]?\\d*\\.?\\d+([eE][-+]?\\d+)?$")) {
            throw new Exception("Data invalid.");
        } else {
            number = Double.parseDouble(input);
        }
        return number;
    }

    public static LocalDate readLocalDate(String message) throws Exception {
        System.out.print(message);
        String input = scanner.nextLine();
        LocalDate date;
        if (!input.matches("^\\d{4}-\\d{2}-\\d{2}$")) { // YYYY-MM-DD
            throw new Exception("Data invalid.");
        }else {
            date = LocalDate.parse(input);
        }
        return date;
    }
    public static LocalDate readLocalDate(String message, LocalDate defaultValue) throws Exception {
        System.out.print(message);
        String input = scanner.nextLine();
        LocalDate date;
        if (input.isBlank()) {
            return defaultValue;
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
        LocalTime time;
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

    public static LocalTime readLocalTime(String message, LocalTime defaultValue) throws Exception {
        System.out.print(message);
        String input = scanner.nextLine();
        LocalTime time;
        if (input.isBlank()) {
            return defaultValue;
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
        boolean bool;
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
    public static boolean readBoolean(String message, boolean defaultValue) throws Exception {
        System.out.print(message);
        String input = scanner.nextLine();
        boolean bool;
        if (input.isBlank()) {
            return defaultValue;
        }
        if (!input.matches("true|false")) {
            throw new Exception("Data invalid.");
        } else {
            bool = Boolean.parseBoolean(input);
        }
        return bool;
    }

    public static boolean readConfirm(String message) throws Exception {
        System.out.println(message);
        String input = scanner.nextLine();

        if(!input.matches("[YNyn]")) {
            throw new Exception("Please input correct given character");
        }

        return input.equalsIgnoreCase("y");
    }

    public static LocalDate readStartDate(String message) throws Exception{
        System.out.print(message);
        String input = scanner.nextLine();
        LocalDate date;
        if (!input.matches("^\\d{4}-\\d{2}-\\d{2}$")) { // YYYY-MM-DD
            throw new Exception("Data invalid.");
        }else {
            date = LocalDate.parse(input);
        }
        return date;
    }

    public static LocalDate readStartDate(String message, LocalDate defaultValue) throws Exception{
        System.out.print(message);
        String input = scanner.nextLine();
        LocalDate date;
        if (input.isBlank()) {
            return defaultValue;
        }
        if (!input.matches("^\\d{4}-\\d{2}-\\d{2}$")) { // YYYY-MM-DD
            throw new Exception("Data invalid.");
        }else {
            date = LocalDate.parse(input);
        }
        return date;
    }

    public static LocalDate readEndDate(String message, LocalDate startDate) throws Exception {
        System.out.print(message);
        String input = scanner.nextLine();
        LocalDate date;
        if (!input.matches("^\\d{4}-\\d{2}-\\d{2}$")) { // YYYY-MM-DD
            throw new Exception("Data invalid.");
        }else {
            date = LocalDate.parse(input);
        }

        if(startDate.isAfter(date)) {
            throw new Exception("End date cannot be before start date");
        }
        return date;
    }

    public static LocalDate readEndDate(String message, LocalDate startDate, LocalDate defaultValue) throws Exception {
        System.out.print(message);
        String input = scanner.nextLine();
        LocalDate date;
        if (input.isBlank()) {
            return defaultValue;
        }
        if (!input.matches("^\\d{4}-\\d{2}-\\d{2}$")) { // YYYY-MM-DD
            throw new Exception("Data invalid.");
        }else {
            date = LocalDate.parse(input);
        }

        if(startDate.isAfter(date)) {
            throw new Exception("End date cannot be before start date");
        }
        return date;
    }

    public static void closeScanner() {
        scanner.close();
    }
}