package com.perso.red.meteo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by pierr on 26/07/2016.
 */

public class Tools {

    public static String getCurrentDate(String format, Locale locale) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, locale);

        return simpleDateFormat.format(calendar.getTime());
    }
}
