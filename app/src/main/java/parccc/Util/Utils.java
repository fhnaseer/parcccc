package parccc.Util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Vincent Bode on 16.06.2017.
 */

public class Utils {
    static DateFormat dateFormat = new SimpleDateFormat("dd.MM");
    static DateFormat timeFormat = new SimpleDateFormat("HH:mm");
    static DateFormat flightAPIFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mmX");
    static DateFormat flightDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    static DateFormat flightDBDateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");

    public static String formatDate(Date date) {
        return dateFormat.format(date);
    }

    public static String formatTime(Date time) {
        return timeFormat.format(time);
    }

    public static Date parseTime(String timeString) {
        try {
            return flightAPIFormat.parse(timeString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date parseDate(String date) {
        try {
            return flightDateFormat.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }
    public static Date parseDBDate(String date) {
        try {
            return flightDBDateFormat.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }
}
