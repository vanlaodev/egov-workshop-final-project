package inventory.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    public static Date getDateFromYearAndMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, 1);
        return cal.getTime();
    }

    public static String toFormatDateString(Date date, String dateFormat) {
        if (StringUtils.isEmpty(dateFormat))
            return DateUtils.yyyyMMddFormat().format(date);

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        Integer year = c.get(Calendar.YEAR);
        Integer month = c.get(Calendar.MONTH) + 1;
        Integer day = c.get(Calendar.DATE);

        String dateString = dateFormat.replaceAll("yyyy", year.toString());
        dateString = dateString.replaceAll("MM", String.format("%02d", month));
        dateString = dateString.replaceAll("dd", String.format("%02d", day));

        return dateString;

    }

    public static Date toDate(String str, String dateFormat){
        try {
            SimpleDateFormat format = new SimpleDateFormat(dateFormat);
            return format.parse(str);
        }catch(Exception ex){
            return null;
        }
    }
    public static String toyyyyMMdd(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        format.setLenient(false);
        return format.format(date).toString();
    }

    public static String toyyyy_MM_dd(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setLenient(false);
        return format.format(date).toString();
    }

    public static String toyyyyMMddhhmmss(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmss");
        format.setLenient(false);
        return format.format(date).toString();
    }

    public static String toyyyyMMddhhmmss2(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        format.setLenient(false);
        return format.format(date).toString();
    }

    public static String toyyyyMMdd24HHmmss(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        format.setLenient(false);
        return format.format(date).toString();
    }

    public static SimpleDateFormat ddMMyyyyEFormat() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy, E");
        format.setLenient(false);
        return format;
    }

    public static SimpleDateFormat ddMMMyyyyFormat() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MMM/yyyy");
        format.setLenient(false);
        return format;
    }

    public static SimpleDateFormat yyyyMMddFormat() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        format.setLenient(false);
        return format;
    }

    public static SimpleDateFormat yyyymmddFormat() {
        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
        format.setLenient(false);
        return format;
    }

    public static SimpleDateFormat ddMMyyyyFormat() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        format.setLenient(false);
        return format;
    }

    public static SimpleDateFormat ddMMyyyyhhmmssaFormat() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy, hh:mm:ss a");
        format.setLenient(false);
        return format;
    }

    public static SimpleDateFormat ddMMyyyyHHmmFormat() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy, hh:mm");
        format.setLenient(false);
        return format;
    }

    public static SimpleDateFormat HmmFormat() {
        SimpleDateFormat format = new SimpleDateFormat("H:mm");
        format.setLenient(false);
        return format;
    }

    public static SimpleDateFormat hhmmaFormat() {
        SimpleDateFormat format = new SimpleDateFormat("hh:mm a");
        format.setLenient(false);
        return format;
    }

    public static SimpleDateFormat yyyy_MM_dd_HmmssS() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.S");
        format.setLenient(false);
        return format;
    }

    public static SimpleDateFormat yyyyMMddHmmssS() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd H:mm:ss.S");
        format.setLenient(false);
        return format;
    }

    public static SimpleDateFormat ddMMyyyyHmmssS() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy H:mm:ss.S");
        format.setLenient(false);
        return format;
    }

    public static SimpleDateFormat MMddyyyyHmmssS() {
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy H:mm:ss.S");
        format.setLenient(false);
        return format;
    }

    public static SimpleDateFormat yyyyMMddHmmssSInOracleFormat() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.S");
        format.setLenient(false);
        return format;
    }

    public static Date getDateObject(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date trimDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date getDateObject(int year, int month, int date) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date getDateObject(int year, int month, int date, int hour, int minute, int second, int millisecond) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MILLISECOND, millisecond);
        cal.set(year, month, date, hour, minute, second);
        return cal.getTime();
    }

    public static Date getNextDate(Date date) {
        return getDateAfter(date, 1);
    }

    public static Date getPreviousDate(Date date) {
        return getDateAfter(date, -1);
    }

    public static Date getSunday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(DateUtils.getDateObject(date));
        if (DateUtils.getDayOfWeek(date) != Calendar.SUNDAY) {
            cal.set(Calendar.DATE, cal.get(Calendar.DATE) + (8 - cal.get(Calendar.DAY_OF_WEEK)));
        }
        return cal.getTime();
    }

    public static Date getMonday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == Calendar.SUNDAY)
            dayOfWeek = 8;
        cal.set(Calendar.DATE, cal.get(Calendar.DATE) - dayOfWeek + 2);
        return cal.getTime();
    }

    public static Date getDateOfWeekBy(Date date, int wantedDay) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        while (cal.get(Calendar.DAY_OF_WEEK) != wantedDay) {
            if (cal.get(Calendar.DAY_OF_WEEK) > wantedDay) {
                cal.set(Calendar.DAY_OF_WEEK, cal.get(Calendar.DAY_OF_WEEK) - 1);
            } else {
                cal.set(Calendar.DAY_OF_WEEK, cal.get(Calendar.DAY_OF_WEEK) + 1);
            }
        }
        return cal.getTime();
    }

    public static int getYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    public static void setYear(Date date, int year) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.YEAR, year);
        date.setTime(cal.getTime().getTime());
    }

    public static int getMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH);
    }

    public static int getDateMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH) + 1;
    }

    public static void setMonth(Date date, int month) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.MONTH, month);
        date.setTime(cal.getTime().getTime());
    }

    public static void setDateMonth(Date date, int month) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.MONTH, month + 1);
        date.setTime(cal.getTime().getTime());
    }

    public static int getDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DATE);
    }

    public static void setDate(Date date, int d) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DATE, d);
        date.setTime(cal.getTime().getTime());
    }

    public static Date genDate(int year, int month, int d) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DATE, d);

        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static void setYearMonthDate(Date date, int year, int month, int d) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DATE, d);
        date.setTime(cal.getTime().getTime());
    }

    public static int getHours(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.HOUR_OF_DAY);
    }

    public static void setHHmmss(Date date, int hours, int minutes, int secounds) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, hours);
        cal.set(Calendar.MINUTE, minutes);
        cal.set(Calendar.SECOND, secounds);
        cal.set(Calendar.MILLISECOND, 0);

        date.setTime(cal.getTime().getTime());
    }

    public static void setHours(Date date, int hours) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, hours);
        date.setTime(cal.getTime().getTime());
    }

    public static int getMinutes(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MINUTE);
    }

    public static void setMinutes(Date date, int minutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.MINUTE, minutes);
        date.setTime(cal.getTime().getTime());
    }

    public static int getSeconds(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.SECOND);
    }

    public static void setSeconds(Date date, int seconds) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.SECOND, seconds);
        date.setTime(cal.getTime().getTime());
    }

    public static int getDayOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    public static Date getDateAfter(Date startDate, int i) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        cal.set(Calendar.DATE, cal.get(Calendar.DATE) + i);
        return cal.getTime();
    }

    public static long getTimeInMin(long time, boolean isFloor) {
        if (isFloor)
            return (long) Math.floor(time / (long) 60000);
        else
            return (long) Math.ceil(time / (long) 60000);
    }

    public static long getTimeDifferenceInMin(Date date1, Date date2, boolean isFloor) {
        long d1 = (getTimeInMin(date1.getTime(), isFloor));
        long d2 = (getTimeInMin(date2.getTime(), isFloor));
        return d1 - d2;
    }

    public static boolean isBefore(Date source, Date target) {
        Date s = DateUtils.getDateObject(source);
        Date t = DateUtils.getDateObject(target);
        return (s.compareTo(t) > 0);
    }

    public static String minToTimeString(Integer min) {
        int hour = (int) (min.doubleValue() / 60);
        int minute = min.intValue() % 60;
        String result = ((hour < 10) ? "0" + hour : "" + hour) + ":" + ((minute < 10) ? "0" + minute : "" + minute);
        return result;
    }

    public static String getDateStr(Date date) {
        return yyyyMMddHmmssS().format(date);
    }

    public static Date getPreviousMonday(Date inputDate) {
        Date monday = getMonday(getPreviousFriday(inputDate));
        Calendar cal = Calendar.getInstance();
        int year = getYear(monday);
        int month = getMonth(monday);
        int date = getDate(monday);
        cal.set(year, month, date, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date getPreviousFriday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DATE, cal.get(Calendar.DATE) - ((cal.get(Calendar.DAY_OF_WEEK) + 12) % 7) - 3);
        return cal.getTime();
        // return null;
    }

    public static int getNumberOfDayBy(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DATE, 1);
        return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public static Date getLastDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DATE, 1);
        cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        return getDateObject(cal.getTime());
    }

    public static long getDateDifference(Date date, Date date2) {

        try {
            long temp = (long) ((((long) (date2.getTime() / 1000)) - ((long) (date.getTime() / 1000)))
                    / (24 * 60 * 60));
            return temp;
        } catch (Exception ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    @SuppressWarnings("deprecation")
    public static int calDaysBetween(Date startDate, Date endDate) {

        if (endDate.before(startDate))
            return 0;

        Date start = startDate;
        start.setHours(0);
        start.setMinutes(0);
        start.setSeconds(0);

        Date end = endDate;
        end.setHours(0);
        end.setMinutes(0);
        end.setSeconds(0);
        long daterange = end.getTime() - start.getTime();

        long time = 1000 * 3600 * 24;
        int k = (int) (daterange / time);

        return k;
    }

    public static int getWorkingDay(Date start, Date end) {

        if (start == null || end == null)
            return 0;
        int daysBetween = calDaysBetween(start, end);

        Calendar c = Calendar.getInstance();
        c.setTime(start);

        int workingDays = 0;
        for (int i = 0; i < daysBetween; i++) {
            c.add(Calendar.DATE, 1);
            if (c.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && c.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY)
                workingDays++;

        }

        return workingDays;
    }

    public static String getDisplayDay(int i) {
        switch (i) {
            case Calendar.SUNDAY:
                return "SUN";
            case Calendar.MONDAY:
                return "MON";
            case Calendar.TUESDAY:
                return "TUE";
            case Calendar.WEDNESDAY:
                return "WED";
            case Calendar.THURSDAY:
                return "THU";
            case Calendar.FRIDAY:
                return "FRI";
            case Calendar.SATURDAY:
                return "SAT";
            default:
                return null;
        }
    }

    public static Date getTimeWithRatio(Date preEndTime, Date curStartTime, double ratio) {
        long l = (long) ((curStartTime.getTime() - preEndTime.getTime()) * ratio);
        return new Date(l + preEndTime.getTime());
    }

    public static boolean isOverMidnight(Date start, Date end) {
        return (getHours(start) > getHours(end)
                || (getHours(start) == getHours(end) && getMinutes(start) > getMinutes(end)));
    }

    public static Date getHourMinute(String timeStr, Date date) throws Exception {
        Date result = DateUtils.getDateObject(date);
        String[] time = timeStr.split(":");
        int hour = Integer.parseInt(time[0]);
        int minute = Integer.parseInt(time[1]);
        DateUtils.setHours(result, hour);
        DateUtils.setMinutes(result, minute);
        return result;
    }

    // Hour minute only, including over midnight
    public static boolean isBetweenHourMinute(Date startTime, Date endTime, Date checkTime) {
        Date start = new Date(startTime.getTime());
        Date end = new Date(endTime.getTime());
        Date check = new Date(checkTime.getTime());

        setYearMonthDate(check, 2006, Calendar.JANUARY, 1);
        setYearMonthDate(start, 2006, Calendar.JANUARY, 1);

        if (!isOverMidnight(startTime, endTime)) {
            setYearMonthDate(end, 2006, Calendar.JANUARY, 1);
            if (start.compareTo(check) < 0 && end.compareTo(check) >= 0) {
                return true;
            }
        } else {
            setYearMonthDate(end, 2006, Calendar.JANUARY, 2);
            Date startTimeEnd = getDateObject(2006, Calendar.JANUARY, 2);
            if (start.compareTo(check) < 0 && startTimeEnd.compareTo(check) >= 0) {
                return true;
            } else {
                setYearMonthDate(check, 2006, Calendar.JANUARY, 2);
                if (startTimeEnd.compareTo(check) <= 0 && end.compareTo(check) >= 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isBetween(Date startTime, Date endTime, Date checkTime) {
        Date start = new Date(startTime.getTime());
        Date end = new Date(endTime.getTime());
        Date check = new Date(checkTime.getTime());
        return (start.compareTo(check) <= 0 && end.compareTo(check) >= 0);
    }

    public static Date getHourAfter(Date date, int i) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY) + i);
        return cal.getTime();
    }

    public static Date getMinuteAfter(Date date, int i) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE) + i);
        return cal.getTime();
    }

    public static boolean isOverLap(Date startDate1, Date endDate1, Date startDate2, Date endDate2) {
        return (isBetween(startDate1, endDate1, startDate2) || isBetween(startDate2, endDate2, startDate1));
    }

    public static Date getAfter(Date date, int year, int month, int day, int hour, int minute, int second,
                                int milliSecond) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) + year);
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + month);
        cal.set(Calendar.DATE, cal.get(Calendar.DATE) + day);
        cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) + hour);
        cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE) + minute);
        cal.set(Calendar.SECOND, cal.get(Calendar.SECOND) + second);
        cal.set(Calendar.MILLISECOND, cal.get(Calendar.MILLISECOND) + milliSecond);
        return cal.getTime();
    }

    public static Date getToday() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }


    public static String getUsingTime(Date startDate, Date endDate) {
        if (endDate != null && startDate != null) {
            long time = endDate.getTime() - startDate.getTime();
            if (time >= 0) {
                return DateUtils.getTimeText(time);
            } else {
                return "";
            }
        }
        return "";
    }

    public static long getUsingTimeValue(Date startDate, Date endDate) {
        if (endDate != null && startDate != null) {
            long time = endDate.getTime() - startDate.getTime();
            if (time > 0) {
                return time;
            } else {
                return 0;
            }
        }
        return 0;
    }

    public static Date getOneDateAfter(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR, 24);
        return cal.getTime();

    }

    public static String getTimeText(long time) {
        String s = "";
        if (time > 0) {
            time = time / 1000;
            long day = time / 86400;
            if (day != 0) {
                s += day + "d ";
            }
            time %= 86400;
            long hour = time / 3600;
            if (hour != 0) {
                s += hour + "h ";
            }
            time %= 3600;
            long min = time / 60;
            if (min != 0) {
                s += min + "m ";
            }
            time %= 60;
            s += time + "s";
        }
        return s;
    }


}
