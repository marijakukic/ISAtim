package ftn.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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

    public static int diffInMinutes(String todaysDate, String date, String time) throws ParseException {

        String format = "yyyyMMdd HH:mm";

        SimpleDateFormat sdf = new SimpleDateFormat(format);

        Date dateObj1 = sdf.parse(todaysDate);
        Date dateObj2 = sdf.parse(date + " " + time);
        System.out.println(dateObj1);
        System.out.println(dateObj2);

        long diff = dateObj2.getTime() - dateObj1.getTime();
        switch ("M") {
            case "D":
                return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
            case "H":
                return (int) TimeUnit.HOURS.convert(diff, TimeUnit.MILLISECONDS);
            case "M":
                return (int) TimeUnit.MINUTES.convert(diff, TimeUnit.MILLISECONDS);
        }
        return 0;

    }


}
