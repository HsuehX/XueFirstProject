package com.example.lenovo.textviewspannerdalogexercise.utils;


import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils {
    public static final String FORMAT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static final String FORMAT_COMMON = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_COMMON_TIME = "HH:mm:ss";
    public static final String FORMAT_NO_SECOND_TIME = "HH:mm";
    public static final String FORMAT_COMMON_DAY = "yyyy-MM-dd";
    public static final String FORMAT_8_DAY = "yyyyMMdd";
    public static final String FORMAT_MMDDHHMM = "MM-dd HH:mm";
    public static final String FORMAT_CHINESE = "yyyy年MM月dd日";

    public static String formatDate(Date inDate, String pattern) {
        String ret = null;
        if (inDate != null && pattern != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            ret = sdf.format(inDate);
        }
        return ret;
    }

    public static String formatDate(Long inDate, String pattern) {
        return formatDate(new Date(inDate), pattern);
    }

    public static Date parseString(String dateString, String pattern) {
        Date ret = null;
        try {
            if (dateString != null && pattern != null) {
                SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                ret = sdf.parse(dateString);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return ret;
    }

    public static String convertDateString(String fromFormat, String toFormat, String dateString) {
        String retString = "";

        if (TextUtils.isEmpty(fromFormat) || TextUtils.isEmpty(toFormat) || TextUtils.isEmpty(dateString)) {
            return "";
        }

        SimpleDateFormat fromformat = new SimpleDateFormat(fromFormat);
        SimpleDateFormat toformat = new SimpleDateFormat(toFormat);
        try {
            Date date = fromformat.parse(dateString);
            retString = toformat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return retString;

    }

    /**
     * 系统时间格式设置
     *
     * @param year
     * @param month
     * @param day
     * @param hourOfDay
     * @param minute
     * @param secend
     * @return
     */
    public static long setTime(String year, String month, String day, String hourOfDay, String minute, String secend) {
        try {
            Calendar c = Calendar.getInstance();
            c.set(Calendar.YEAR, Integer.parseInt(year));
            c.set(Calendar.MONTH, Integer.parseInt(month) - 1);
            c.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day));
            c.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hourOfDay));
            c.set(Calendar.MINUTE, Integer.parseInt(minute));
            c.set(Calendar.SECOND, Integer.parseInt(secend));
            c.set(Calendar.MILLISECOND, 0);
            long when = c.getTimeInMillis();

            if (when / 1000 < Integer.MAX_VALUE) {
                // SystemClock.setCurrentTimeMillis(when);
                return when;
            }
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * 系统时间格式设置
     *
     * @param pattern 时间格式
     * @param day     减去的天数
     * @return 减去后的时间
     */
    public static String getFormatedAddDateTime(String pattern, int day) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date beginDate = new Date();
        Calendar date = Calendar.getInstance();
        date.setTime(beginDate);
        date.set(Calendar.DATE, date.get(Calendar.DATE) - day);
        return sdf.format(date.getTime());
    }

    /**
     * 将当前时间转化成String型
     *
     * @return
     */
    public static String getToday8() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_8_DAY, Locale.CHINA);
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 将当前时间转化成String型
     *
     * @return
     */
    public static String getToday10() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_COMMON_DAY, Locale.CHINA);
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 将当前时间转化成String型
     *
     * @return
     */
    public static String getTodayTiemNoSecond() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_NO_SECOND_TIME, Locale.CHINA);
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 将当前时间转化成String型
     *
     * @return
     */
    public static String getTodayCommon() {
        Date currentTime = new Date(System.currentTimeMillis());
        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_COMMON, Locale.CHINA);
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 将当前时间转化成String型
     *
     * @return
     */
    public static String getTodayOrderShow() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_MMDDHHMM, Locale.CHINA);
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    public static String getTodayTiemYesSecond() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_COMMON_TIME, Locale.CHINA);
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    public static long getNowLong() {
        return System.currentTimeMillis();
    }


    /**
     * 时间 计算
     * 正常带日期的时间
     *
     * @return
     */
    private static long getNormalTimeMillis(String strTime) {
        long returnMillis = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date d = null;
        if(TextUtils.isEmpty(strTime)){
            return  0;
        }
        try {
            d = sdf.parse(strTime);
            returnMillis = d.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return returnMillis;
    }


    /**
     * 时间 计算
     *
     * @return
     */
    private static long getTimeMillis(String strTime) {
        long returnMillis = 0;
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date d = null;
        try {
            d = sdf.parse(strTime);
            returnMillis = d.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return returnMillis;
    }

    /**
     * 时间是三位的
     */
    private static long getTimeMillisThree(String strTime) {
        long returnMillis = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("H:mm");
        Date d = null;
        try {
            d = sdf.parse(strTime);
            returnMillis = d.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return returnMillis;
    }

    public static String getTimeDifference(String startTime, String endTime) {
        long longStart = getNormalTimeMillis(startTime);
        long longEnd = getNormalTimeMillis(endTime);

        long longExpend = longEnd - longStart;
        long longMinutes = longExpend / (60 * 1000);

        return longMinutes + "";
    }
    public static long getTimeDifferenceLng(String startTime, String endTime) {
        long longStart = getNormalTimeMillis(startTime);
        long longEnd = getNormalTimeMillis(endTime);

        long longExpend = longEnd - longStart;
        long longMinutes = longExpend / (60 * 1000);

        return longMinutes ;
    }


    /***
     * 时间比较大小
     * @param planTime
     * @param realTime
     * @return
     */
    public static boolean timeOut(String planTime, String realTime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");//年-月-日 时-分
        try {
            Date planCanTime = dateFormat.parse(planTime);
            Date getRealTime = dateFormat.parse(realTime);

            if (!(getRealTime.getTime() < planCanTime.getTime())) {//后面的时间不小于前面的时间
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static SimpleDateFormat commonDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public static boolean nowIsLaterThanTarget(String commonFormatTime) {
        try {
            long now = System.currentTimeMillis();
            long target = commonDateFormat.parse(commonFormatTime).getTime();
            if (now > target) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
