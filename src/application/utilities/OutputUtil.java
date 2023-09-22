package application.utilities;

import static application.utilities.InputUtil.*;

public class OutputUtil {
    private static final int length = 60;

    public static void waitForInput() {
        printLineSeparate();
        System.out.println("Nhấn phím bất kỳ để trở về menu.");
        readString("");
        for (int i = 0; i < 100; i++) System.out.println(" ");
    }
    public static void clearScreen() {
        for (int i = 0; i < 100; i++) System.out.println(" ");
    }

    public static void printLineSeparate() {
        System.out.println("+" + "-".repeat(length - 2) + "+");
    }
    public static void printLineSeparate(String str) {
        int i = str.length() / 2;
        System.out.println("+" + "-".repeat((length / 2  - 1) - i) + str + "-".repeat((length / 2 - 1) - i) + "+");
    }

    public static void printValueMenu(String value) {
        System.out.println("|   " + value + " ".repeat(length - value.length() - 5) + "|");
    }

    public static void printValueln(String value) {
        System.out.println(value);
    }

    public static void printValue(String value) {
        System.out.print(value);
    }
}
