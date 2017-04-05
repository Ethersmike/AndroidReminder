package a123.com.example.vgani.remindermaterial;

import java.text.SimpleDateFormat;

public class Utils {

    public static String getDate(long date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yy");
        return dateFormat.format(date);
    }

    public static String getTome(long time) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH.mm");
        return timeFormat.format(time);
    }
}
