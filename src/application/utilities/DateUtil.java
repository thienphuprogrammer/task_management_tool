package application.utilities;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class DateUtil {
    private static final String DEFAULT_DATE_FORMAT = "dd/MM/yyyy";

    public static LocalDate getDate() {
        return LocalDate.now();
    }
    public static String formatDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        return formatter.format(date);
    }

    public static Date parseDate(String dateString) {
        SimpleDateFormat formatter = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        try {
            return formatter.parse(dateString);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
