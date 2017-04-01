package com.jen.sen.untils;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * filename SysDateTimeUtil.java
 * company jen
 * @author jenson
 * @email jenson2000@sina.com
 */
public class SysDateTimeUtil {
	private static SimpleDateFormat format = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	private static SimpleDateFormat formatNoLine = new SimpleDateFormat(
			"yyyyMMddHHmmss");
	private static SimpleDateFormat format1 = new SimpleDateFormat(
			"yyyy-MM-dd-HH-mm-ss");
	private static SimpleDateFormat format2 = new SimpleDateFormat(
			"yyyy-MM-dd 00:00:00");
	private static SimpleDateFormat format3 = new SimpleDateFormat(
			"yyyy-MM-dd 23:59:59");
	private static SimpleDateFormat format4 = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat format5 = new SimpleDateFormat("HH:mm:ss");
	private static SimpleDateFormat format6 = new SimpleDateFormat("yyyyMMdd");
	private static SimpleDateFormat formatyyyy = new SimpleDateFormat("yyyy");
	private static SimpleDateFormat formatmm = new SimpleDateFormat("MM");
	private static SimpleDateFormat format7 = new SimpleDateFormat("yyyy-MM");
	private static SimpleDateFormat fm = new SimpleDateFormat("HH:mm:ss");
	private static SimpleDateFormat fm1 = new SimpleDateFormat("yyyy-MM-dd");
	private static DecimalFormat df1 = new DecimalFormat("00");
	private static SimpleDateFormat formatHHmmssSSS = new SimpleDateFormat(
			"HHmmssSSS");
	private static SimpleDateFormat format8 = new SimpleDateFormat(
			"MM-dd HH:mm:ss");

	private static SimpleDateFormat format9 = new SimpleDateFormat(
			"MM月dd号 HH:mm");

	public static String getCurrentYear() {
		return formatyyyy.format(getSystemDate());
	}

	public static String getCurrentYear(Date date) {
		return formatyyyy.format(date);
	}

	public static String getCurrentMonth() {
		return formatmm.format(getSystemDate());
	}

	public static String getCurrentMonth(Date date) {
		return formatmm.format(date);
	}

	/**
	 * 
	 * desc：获得date日期后nextDay天的最早或最晚时间。
	 * @param @param date
	 * @param @param nextDay
	 * @param @param isfirst
	 * @param @return
	 * @return Date
	 */
	public static Date getTimeOfNextDayFirstOLast(Date date, int nextDay,
			boolean isfirst) {
		int hour = 0;
		int minse = 0;
		if (!isfirst) {
			hour = 23;
			minse = 59;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, hour);
		c.set(Calendar.MINUTE, minse);
		c.set(Calendar.SECOND, minse);
		c.set(Calendar.MILLISECOND, minse);
		c.add(Calendar.DATE, nextDay);
		return c.getTime();
	}

	/**
	 * 
	 * desc：获得date距凌晨的秒数
	 * @param @param date
	 * @param @return
	 * @return int
	 */
	public static int getSecondsInDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int seconds = calendar.get(Calendar.HOUR_OF_DAY) * 3600
				+ calendar.get(Calendar.MINUTE) * 60
				+ calendar.get(Calendar.SECOND);
		return seconds;
	}

	/**
	 * 
	 * desc：根据date获得weekday。
	 * @param @param date
	 * @param @return
	 * @return int
	 */
	public static int getWeekDayOfDate(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 
	 * desc：根据dateStr获得年月日格式的Date
	 * @param @param dateStr
	 * @param @return
	 * @return Date
	 */
	public static Date strYMDToDateFormat(String dateStr) {
		Date date = null;
		try {
			date = format4.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 
	 * desc：得到系统的当前时间年月日,时分秒
	 * @param @return
	 * @return String
	 */
	public static String getCurrentSysDateTime() {
		return format.format(new Date());
	}

	/**
	 * 
	 * desc：得到系统的当前时间年-月-日-时-分-秒,String类型 如2012-01-01-12-00-00
	 * @param @return
	 * @return String
	 */

	public static String getCurrentTime() {
		return format1.format(new Date());
	}

	/**
	 * 
	 * desc：根据操作符判定是否对传入的时间进行修改
	 * @param @param requestDate
	 * @param @param modifyYear
	 * @param @param modifyMonth
	 * @param @param modifyDay
	 * @param @param para
	 * @param @return
	 * @return Date
	 */
	public static Date modifyYearAndMonth(Date requestDate, int modifyYear,
			int modifyMonth, int modifyDay, int para) {

		Calendar c = Calendar.getInstance();
		c.setTime(requestDate);

		// 判定当前操作是增加还是减少
		if (para > 0) {

			// 修改年份
			if (modifyYear > 0) {
				c.add(Calendar.YEAR, modifyYear);
			}

			// 修改月份
			if (modifyMonth > 0) {
				c.add(Calendar.MONTH, modifyMonth);
			}

			// 修改天数
			if (modifyDay > 0) {
				c.add(Calendar.DAY_OF_MONTH, modifyDay);
			}

			// 减少日期
		} else {
			// 修改年份
			if (modifyYear > 0) {
				c.add(Calendar.YEAR, -modifyYear);
			}

			// 修改月份
			if (modifyMonth > 0) {
				c.add(Calendar.MONTH, -modifyMonth);
			}

			// 修改天数
			if (modifyDay > 0) {
				c.add(Calendar.DAY_OF_MONTH, -modifyDay);
			}
		}

		return c.getTime();
	}

	public static String getCurrentSysDateTimeNoLine() {
		return formatNoLine.format(new Date());
	}

	public static String getCurrentSysDateTimeNoLine(Date date) {
		return formatNoLine.format(date);
	}

	/** 得到当天最早的时间 */
	public static String getCurrentSysDate1() {
		return format2.format(new Date());
	}

	/** 得到当天最晚的时间 */
	public static String getCurrentSysDate2() {
		return format3.format(new Date());
	}

	/**
	 * 
	 * desc：得到指定日期当天开始的最早的时间
	 * @param @param date
	 * @param @return
	 * @return Date
	 */
	public static Date getCurrentDayFirstTime(Date date) {
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	/**
	 * 
	 * desc：得到指定日期当天开始的最晚的时间。
	 * @param @param date
	 * @param @return
	 * @return Date
	 */
	public static Date getCurrentDayLastTime(Date date) {
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 999);
		return c.getTime();
	}

	public static String parseDate1(String date) {
		ParsePosition pos = new ParsePosition(0);
		return format2.format((format4.parse(date, pos))).toString();
	}

	public static String parseDate2(String date) {
		ParsePosition pos = new ParsePosition(0);
		return format3.format((format4.parse(date, pos))).toString();
	}

	public static String getUniDate() {
		return format4.format(new Date());
	}

	public static String getToChineseDateMonth(String date) {
		String year = date.substring(0, 4);
		String month = date.substring(5, 7);
		return year + "年" + month + "月";
	}

	/**
	 * 把字符串日期转换成24hh Date对象
	 * */
	public static Date toDate(String dateStr) {
		ParsePosition pos = new ParsePosition(0);
		return format.parse(dateStr, pos);
	}
	
	/**
	 * 把字符串日期转换成date类型的
	 * */
	public static Date toDateTime(String dateStr) {
		try {
			return format.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 把字符串日期转换成24hh Date对象 格式(yyyyMMddHHmmss)
	 * */
	public static Date stringtoDate(String dateStr) {
		ParsePosition pos = new ParsePosition(0);
		return formatNoLine.parse(dateStr, pos);
	}

	/**
	 * 把Date日期转换成字符串24hh小制的
	 * */
	public static String toStrDate(Date date) {
		return format.format(date);
	}

	public static String toStrOnlyDateNoLine(Date date) {
		if (date == null || "".equals(date)) {
			return "";
		} else {
			return format6.format(date);
		}
	}

	/**
	 * 
	 * desc：获取当前系统日期 返回 yyyyMMdd 格式的字符串
	 * @param @return
	 * @return String
	 */
	public static String getCurrentOnlyDate() {
		return format6.format(getSystemDate());
	}

	/**
	 * 
	 * desc：返回Date类型的时间 把String转换为Date
	 * @param @param date
	 * @param @return
	 * @return Date
	 */
	public static Date toDateNoLine(String date) {
		if (date == null || "".equals(date)) {
			return null;
		} else {
			ParsePosition pos = new ParsePosition(0);
			return format6.parse(date, pos);
		}
	}

	/**
	 * 把时间转换为 yyyy-MM
	 */
	public static String getSystemYearAndMonth(Date date) {
		if (date == null || "".equals(date)) {
			return "";
		} else {
			return format7.format(date);
		}
	}

	/**
	 * 把Date日期转换成字符串，只有日期
	 * */
	public static String toStrOnlyDate(Date date) {
		if (date == null || "".equals(date)) {
			return "";
		} else {
			return format4.format(date);
		}
	}

	/**
	 * 计算两个日期相差的天数 永远返回非0整数
	 * */
	public static int getMiddDayByTwoDate(Date date1, Date date2) {
		Calendar lastCalendar = Calendar.getInstance();
		if (date1 == null || date2 == null) {
			return -1;
		}

		// 得到第一个日期的年月日。
		lastCalendar.setTime(date1);
		int oneYear = lastCalendar.get(Calendar.YEAR);
		int oneMonth = lastCalendar.get(Calendar.MONTH);
		int oneDay = lastCalendar.get(Calendar.DATE);

		// 得到第二个日期的年月日。
		lastCalendar.setTime(date2);
		int twoYear = lastCalendar.get(Calendar.YEAR);
		int twoMonth = lastCalendar.get(Calendar.MONTH);
		int twoDay = lastCalendar.get(Calendar.DATE);

		// 计算相差的天数
		int yearCountDay = (oneYear - twoYear) * 365;
		int monthCountDay = (oneMonth - twoMonth) * 30;
		int dayCountDay = oneDay - twoDay;

		return Math.abs(yearCountDay + monthCountDay + dayCountDay);
	}

	/**
	 * 
	 * desc：得到两个日期差的天数
	 * @param @param date1
	 * @param @param date2
	 * @param @return
	 * @return Long
	 */
	public static Long getDaysMissByTwoDate(Date date1, Date date2) {
		return Math.abs((date1.getTime() - date2.getTime())
				/ (1000 * 60 * 60 * 24));
	}

	/**
	 * 
	 * desc：取得指定日期所在周的第一天
	 * @param @param date
	 * @param @return
	 * @return Date
	 */
	public static Date getFirstDayOfWeek(Date date) {
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
		return c.getTime();
	}

	/**
	 * 
	 * desc：取得指定日期所在周的最后一天
	 * @param @param date
	 * @param @return
	 * @return Date
	 */
	public static Date getLastDayOfWeek(Date date) {
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 999);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday
		return c.getTime();
	}

	/**
	 * 
	 * desc：获取指定日期的下周第一天
	 * @param @param date
	 * @param @return
	 * @return Date
	 */
	public static Date getNextMonday(Date date) {

		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		c.add(Calendar.DATE, 7);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
		return c.getTime();
	}

	/**
	 * 
	 * desc：获取指定日期的下周最后一天
	 * @param @param date
	 * @param @return
	 * @return Date
	 */
	public static Date getNextSunday(Date date) {

		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		c.add(Calendar.DATE, 7);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 999);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Monday
		return c.getTime();
	}

	/**
	 * 
	 * desc：获取指定日期的上周第一天
	 * @param @param date
	 * @param @return
	 * @return Date
	 */
	public static Date getPreMonday(Date date) {

		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		c.add(Calendar.DATE, -7);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
		return c.getTime();
	}

	/**
	 * 
	 * desc：获取指定日期的上周最后一天。
	 * @param @param date
	 * @param @return
	 * @return Date
	 */
	public static Date getPreSunday(Date date) {

		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		c.add(Calendar.DATE, -7);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 999);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Monday
		return c.getTime();
	}

	/**
	 * 
	 * desc：获取传入日期是星期几
	 * @param @param date
	 * @param @return
	 * @return String
	 */
	public static String getWeekOfDate(Date date) {
		String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;

		return weekDays[w];
	}

	/**
	 * 
	 * desc：根据日期得到中文日期描述，以及星期数
	 * @param @param date
	 * @param @return
	 * @return String
	 */
	public static String getChineseAndWeekDayByDate(Date date) {
		SimpleDateFormat bartDateFormat2 = new SimpleDateFormat(
				"yyyy年MM月dd日EEEE");
		return bartDateFormat2.format(date);
	}

	// 根据传入时间得到毫秒数
	public static Long getSecondsByDateTime(Date TimeStr) {
		String time = formatNoLine.format(TimeStr);
		Long seconds = 0L;
		try {
			seconds = formatNoLine.parse(time).getTime() / 1000;
		} catch (ParseException e) {
			// e.printStackTrace();
		}
		return seconds;
	}

	/**
	 * 
	 * desc：得到当天从0时0分0秒开始的秒数
	 * @param @param TimeStr
	 * @param @return
	 * @return int
	 */
	public static int getSecondsByTimeStr(String TimeStr) {
		ParsePosition pos = new ParsePosition(0);
		Date date = format5.parse(TimeStr, pos);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int seconds = calendar.get(Calendar.HOUR_OF_DAY) * 3600
				+ calendar.get(Calendar.MINUTE) * 60
				+ calendar.get(Calendar.SECOND);
		return seconds;
	}

	/**
	 * 
	 * desc：将秒数转换为HH:mm:ss。
	 * @param @param seconds
	 * @param @return
	 * @return String
	 */
	public static String getTimeStrBySeconds(int seconds) {
		Calendar curCalendar = Calendar.getInstance();
		curCalendar.setTime(new Date());
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, curCalendar.get(Calendar.YEAR));
		calendar.set(Calendar.MONTH, curCalendar.get(Calendar.MONTH));
		calendar.set(Calendar.DATE, curCalendar.get(Calendar.DATE));
		calendar.set(Calendar.HOUR_OF_DAY, seconds / 3600);
		calendar.set(Calendar.MINUTE, seconds % 3600 / 60);
		calendar.set(Calendar.SECOND, seconds % 3600 % 60 % 60);
		return format5.format(calendar.getTime());
	}

	/**
	 * 
	 * desc：比较两个日期是否在同一周
	 * @param @param date1
	 * @param @param date2
	 * @param @return
	 * @return boolean
	 */
	public static boolean isOnSameWeek(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		cal1.add(Calendar.DAY_OF_YEAR, -1);
		int w1 = cal1.get(Calendar.WEEK_OF_YEAR);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		cal2.add(Calendar.DAY_OF_YEAR, -1);
		int w2 = cal2.get(Calendar.WEEK_OF_YEAR);
		if (w1 == w2) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * desc：根据传入的秒，转换成日期时间String 类型的
	 * @param @param seconds
	 * @param @return
	 * @return String
	 */
	public static String getDateTimeFromatBySeconds(int seconds) {
		Date date = new Date();
		int hours = seconds / 3600; // 当前小时
		seconds = seconds % 3600;
		int minutes = seconds / 60;
		seconds = seconds % 60;
		int s = seconds;
		String dTime = fm1.format(date.getTime()) + " " + df1.format(hours)
				+ ":" + df1.format(minutes) + ":" + df1.format(s);
		return dTime;
	}

	/**
	 * 
	 * desc：根据传入的秒，转换成时间
	 * @param @param seconds
	 * @param @return
	 * @return String
	 */
	public static String getTimeFromatBySeconds(int seconds) {
		int hours = seconds / 3600; // 当前小时
		seconds = seconds % 3600;
		int minutes = seconds / 60;
		seconds = seconds % 60;
		int s = seconds;
		String dTime = df1.format(hours) + ":" + df1.format(minutes) + ":"
				+ df1.format(s);
		return dTime;
	}

	/**
	 * 
	 * desc：将当前系统时间转换成秒
	 * @param @return
	 * @return int
	 */
	public static int getSecondsByDateTime() {
		Date date = new Date();
		String dateTime = fm.format(date.getTime());
		String[] b = dateTime.split(":");
		int seconds = (Integer.parseInt(b[0]) * 3600)
				+ (Integer.parseInt(b[1]) * 60) + (Integer.parseInt(b[2]));
		return seconds;
	}

	/**
	 * 
	 * desc：将传入的秒数化为分钟
	 * @param @param seconds
	 * @param @return
	 * @return String
	 */
	public static String getStrSeconds(int seconds) {
		int m = seconds / 60;
		int s = seconds % 60;
		DecimalFormat df = new DecimalFormat("00");
		return m + ":" + df.format(s);
	}

	/**
	 * 
	 * desc：根据传入的时间不带时间转换成秒数
	 * @param @param dateTimes
	 * @param @return
	 * @return int
	 */
	public static int getSecondsByTime(String dateTimes) {
		String[] times = dateTimes.split(":");
		return Integer.parseInt(times[0]) * 60 * 60
				+ Integer.parseInt(times[1]) * 60 + Integer.parseInt(times[2]);
	}

	/**
	 * 
	 * desc：得到传入日期的秒数
	 * @param @param DateStr
	 * @param @return
	 * @return int
	 */
	public static int getSecondsByDate(String DateStr) {
		ParsePosition pos = new ParsePosition(0);
		Date date = format.parse(DateStr, pos);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return (int) (calendar.getTime().getTime() / 1000);
	}

	/**
	 * 
	 * desc：根据传入的时间转换成秒数
	 * @param @param dateTimes
	 * @param @return
	 * @return int
	 */
	public static int getSecondsByDateTime(String dateTimes) {
		String[] dateTime = dateTimes.split(" ");
		String[] times = dateTime[1].split(":");
		return Integer.parseInt(times[0]) * 60 * 60
				+ Integer.parseInt(times[1]) * 60 + Integer.parseInt(times[2]);
	}

	/**
	 * 
	 * desc：计算两个日期时间的之间相差的毫秒数,负数表示第一个日期早于第二个日期
	 * @param @param firstDate
	 * @param @param secondDate
	 * @param @return
	 * @return Long
	 */
	public static Long getSecondsDifference(Date firstDate, Date secondDate) {
		Long secondsDifference = firstDate.getTime() - secondDate.getTime();
		return secondsDifference;
	}

	/**
	 * 
	 * desc：获取系统时间。
	 * @param @return
	 * @return Date
	 */
	public static Date getSystemDate() {
		Date date = new Date();
		return date;
	}

	/**
	 * 
	 * desc：获取当前系统时间的 时分秒毫秒 HHmmssSSS
	 * @param @return
	 * @return String
	 */
	public static String getCurrentTimeHHmmssSSS() {
		return formatHHmmssSSS.format(getSystemDate());
	}

	/**
	 * 
	 * desc：得到两个时间相差的 天，小时，分钟，秒
	 * @param @param startTime
	 * @param @param endTime
	 * @param @return
	 * @return Map<String,Object>
	 */
	public static Map<String, Object> getTimeDifference(Date startTime,
			Date endTime) {
		if (startTime == null || endTime == null) {
			return null;
		}
		long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
		long nh = 1000 * 60 * 60; // 一小时的毫秒数
		long nm = 1000 * 60; // 一分钟的毫秒数
		long ns = 1000; // 一秒钟的毫秒数
		long diff;
		diff = endTime.getTime() - startTime.getTime();
		long day = diff / nd; // 天
		long hour = diff % nd / nh; // 小时
		long min = diff % nd % nh / nm; // 分钟
		long sec = diff % nd % nh % nm / ns;// 秒
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("day", day);
		map.put("hour", hour);
		map.put("min", min);
		map.put("sec", sec);
		return map;
	}

	/**
	 * 
	 * desc：日期加减年，月，天
	 * @param @param date
	 * @param @param year
	 * @param @param month
	 * @param @param day
	 * @param @return
	 * @return String
	 */
	public static String CalculateDate(Date date, int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		if (day != 0) {
			cal.add(Calendar.DATE, day);
		}
		if (month != 0) {
			cal.add(Calendar.MONTH, month);
		}
		if (year != 0) {
			cal.add(Calendar.YEAR, year);

		}
		return format.format(cal.getTime());
	}

	public static Date subMinute(int minute) {
		Date datetest1 = new Date();
		long Time1 = (datetest1.getTime() / 1000) - 60 * minute;// 减去分
		datetest1.setTime(Time1 * 1000);
		return datetest1;
	}

	/**
	 * 把Date日期转换成字符串 MM-dd HH:mm:ss
	 * */
	public static String toStrDate8(Date date) {
		return format8.format(date);
	}

	/**
	 * 把Date日期转换成字符串 MM月dd号 HH:mm
	 * */
	public static String toStrDate9(Date date) {
		return format9.format(date);
	}	
	
	/**
	 * 
	 * desc：某一个月第一天和最后一天
	 * @param @param date
	 * @param @return
	 * @return Map<String,String>
	 */
    private static Map<String, String> getFirstday_Lastday_Month(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -1);
        Date theDate = calendar.getTime();
        
        //上个月第一天
        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
        gcLast.setTime(theDate);
        gcLast.set(Calendar.DAY_OF_MONTH, 1);
        String day_first = df.format(gcLast.getTime());
        StringBuffer str = new StringBuffer().append(day_first).append(" 00:00:00");
        day_first = str.toString();

        //上个月最后一天
        calendar.add(Calendar.MONTH, 1);    //加一个月
        calendar.set(Calendar.DATE, 1);        //设置为该月第一天
        calendar.add(Calendar.DATE, -1);    //再减一天即为上个月最后一天
        String day_last = df.format(calendar.getTime());
        StringBuffer endStr = new StringBuffer().append(day_last).append(" 23:59:59");
        day_last = endStr.toString();

        Map<String, String> map = new HashMap<String, String>();
        map.put("first", day_first);
        map.put("last", day_last);
        return map;
    }

    /**
     * 当月第一天
     * @return
     */
    public static String getFirstDay(String formatPatten) {
        SimpleDateFormat df = new SimpleDateFormat(formatPatten);
        Calendar calendar = Calendar.getInstance();
        Date theDate = calendar.getTime();
        
        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
        gcLast.setTime(theDate);
        gcLast.set(Calendar.DAY_OF_MONTH, 1);
        return df.format(gcLast.getTime());
    }
    
    /**
     * 当月最后一天
     * @return
     */
    public static String getLastDay(String formatPatten) {
        SimpleDateFormat df = new SimpleDateFormat(formatPatten);
        Calendar calendar = Calendar.getInstance();
        Date theDate = calendar.getTime();
        return  df.format(theDate);
    }
    
    public static String getYYYYMMDDStr(Date date){
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    	String str=df.format(date);
    	return str;
    }
	
    public static  Date getDateByFormatPatten(String dateStr,String formatPatten){
    	Date d=null;
    	 SimpleDateFormat df = new SimpleDateFormat(formatPatten);
    	 try {
			d= df.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	 return d;
    }
    
    /**
     * 
     * desc：获取当前时间str，格式yyyyMMddHHmmss
     * @param @return
     * @return String
     */
    public static String getCurrentDateTimeStr()
    {
        SimpleDateFormat dataFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        String timeString = dataFormat.format(date);
        return timeString;
    }    
    
    public static Date getMonthAdd(Date dt,int count){
    	Date returnDate=null;
    	try {
    		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        	System.out.println("计算前的日期为 : "+sdf.format(dt));
            Calendar rightNow = Calendar.getInstance();
            rightNow.setTime(dt);
            rightNow.add(Calendar.MONTH,count);//日期加3个月
            returnDate=rightNow.getTime();
            String reStr = sdf.format(returnDate);
            System.out.println("计算后的日期为 : "+reStr);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("转换时间出错啦");
		}
        return returnDate;
    }
    
    /**
     * 比较两个日期之间的大小
     * 
     * @param d1
     * @param d2
     * @return 前者大于后者返回true 反之false
     */
    public static boolean compareDate(Date d1, Date d2) {
    	Calendar c1 = Calendar.getInstance();
    	Calendar c2 = Calendar.getInstance();
    	c1.setTime(d1);
    	c2.setTime(d2);

    	int result = c1.compareTo(c2);
    	if (result >= 0)
    		return true;
    	else
    		return false;
    }
	
}
