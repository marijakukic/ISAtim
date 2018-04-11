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

}
