package application.utilities;

public class StringUtils {
    public static boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    public static String[] splitString(String str, String delimiter) {
        return str.split(delimiter);
    }

    public static String joinStrings(String[] strings, String delimiter) {
        return String.join(delimiter, strings);
    }
}
