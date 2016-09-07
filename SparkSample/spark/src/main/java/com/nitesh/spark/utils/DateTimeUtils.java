package com.nitesh.spark.utils;

import android.support.annotation.Keep;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Nitesh Singh(killer) on 8/28/2016.
 */
@Keep
public class DateTimeUtils {
    /**
     * This will return date string in given format from given timestamp in millis
     * @param timeStamp
     * @param format
     * @return
     */

    public static String getFormattedDate(long timeStamp, String format){
        return new SimpleDateFormat(format).format(new Date(timeStamp));
    }

    /**
     * This will return default date string from given timestamp in millis
     * @param timeStamp
     * @return
     */
    public static String getFormattedDate(long timeStamp){
        return new Date(timeStamp).toString();
    }

    /**
     *
     * @param fromFormat
     * @param toFormat
     * @return
     */
    public static String getFormattedDate(String dateStr, String fromFormat, String toFormat){


        try {
            return new SimpleDateFormat(toFormat).format((new SimpleDateFormat(fromFormat)).parse(dateStr));
        } catch (ParseException e) {
            e.printStackTrace();
            return "wrong format";
        } catch (Exception e){
            e.printStackTrace();
            return "wrong format";
        }
    }
    /**
     * Get age from date of birth
     * Input will be date of birth in milliseconds
     * @return res
     */
    public static int getAge(long dobInMillis) {

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(dobInMillis);
        Calendar now = Calendar.getInstance();
        int res = now.get(Calendar.YEAR) - cal.get(Calendar.YEAR);
        if ((cal.get(Calendar.MONTH) > now.get(Calendar.MONTH)) || (cal.get(Calendar.MONTH) == now.get(Calendar.MONTH) && cal.get(Calendar.DAY_OF_MONTH) > now.get(Calendar.DAY_OF_MONTH))) {
            res--;
        }
        return res;
    }

    /**
     * Get age from date of birth
     * Input will be date of birth in string
     * @param dobStr
     * @param dateStrFormat
     * @return res
     */
    public static int getAge(String dobStr, String dateStrFormat) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(getTimestampInMillisFromDateStamp(dobStr,dateStrFormat));
        Calendar now = Calendar.getInstance();
        int res = now.get(Calendar.YEAR) - cal.get(Calendar.YEAR);
        if ((cal.get(Calendar.MONTH) > now.get(Calendar.MONTH)) || (cal.get(Calendar.MONTH) == now.get(Calendar.MONTH) && cal.get(Calendar.DAY_OF_MONTH) > now.get(Calendar.DAY_OF_MONTH))) {
            res--;
        }
        return res;
    }

    /**
     * Get timestamp in milli seconds from date string
     * @param dobStr
     * @param dateStrFormat
     * @return
     */
    private static long getTimestampInMillisFromDateStamp(String dobStr, String dateStrFormat) {
        try {
            return (new SimpleDateFormat(dateStrFormat)).parse(dobStr).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        } catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * Return true if given year is a leap year
     * @param year
     * @return
     */
    public static boolean isLeapYear(int year) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        return cal.getActualMaximum(cal.DAY_OF_YEAR) > 365;
    }
}
