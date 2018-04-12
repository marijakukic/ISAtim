package ftn.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateService {

    public static String getFormattedDate(String unformattedDate) throws ParseException {
        Date date = new SimpleDateFormat("EEE MMM dd yyyy").parse(unformattedDate.substring(0,15));
        return new SimpleDateFormat("yyyyMMdd").format(date);
    }

    public static String getFormattedDate2(String unformattedDate) throws ParseException {
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(unformattedDate.substring(0,10));
        return new SimpleDateFormat("yyyyMMdd").format(date);
    }

    public static String getFormattedDateUniversal(String unformattedDate) throws ParseException {
        if (unformattedDate.substring(0,15).contains(":")) {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(unformattedDate.substring(0,10));
            return new SimpleDateFormat("yyyyMMdd").format(date);
        }
        else {
            Date date = new SimpleDateFormat("EEE MMM dd yyyy").parse(unformattedDate.substring(0,15));
            return new SimpleDateFormat("yyyyMMdd").format(date);
        }
    }

}
