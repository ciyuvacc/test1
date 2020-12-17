package com.dist.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Title: 日期读取工具类 Description: Copyright: Copyright (c)2010 Company: hkrt
 * 
 * @author zhen.wang
 */

public final class DateUtil {
    

	/**
	 * Calendar
	 */
	static GregorianCalendar calendar = new GregorianCalendar();

	/**
	 * 取得通用日期时间格式字符串
	 * 
	 * @param Date
	 * @return String
	 */

	public static String formatDate(Date date) {
		if (date == null) {
			return "";
		}

		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		return dateFormat.format(date);
	}

	/**
	 * 获取当日的日期格式串
	 * 
	 * @param
	 * @return String
	 */
	public static String today() {
		return formatDate(new Date(), "yyyy-MM-dd");
	}
	public static String today_no() {
		return formatDate(new Date(), "yyyyMMdd");
	}
	
	public static String today_all() {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}
	
	public static String getCurrentMonth(Date date) {
	    return formatDate(date, "yyyy-MM");
	}
	
	/**
	 * 获取当日的日期格式串
	 * @return yyyyMMddhhmmssSSS
	 */
	public static String today_alltoSSS() {
		return formatDate(new Date(), "yyyyMMddhhmmssSSS");
	}
	
	/**
	 * 获得当日的日期格式串 yyyyMMdd
	 * @return
	 */
	public static String todayStirng(){
		return formatDate(new Date(), "yyyyMMdd");
	}
	/**
	 * 取得指定日期格式的 字符串
	 * 
	 * @param Date
	 * @return String
	 */
	public static String formatDate(Date date, String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
	}

	/**
	 * 获取昨日的日期格式串
	 * 
	 * @param 无
	 * @return Date
	 */
	public static String getYesterday() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);
		return formatDate(calendar.getTime(), "yyyy-MM-dd");
	}

	/**
	 *获取明天日期格式串 
	 */
	public static String getTomorrow() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, +1);
		return formatDate(calendar.getTime(), "yyyy-MM-dd");
	}
	/**
	 * 获取三天前的日期
	 */
	public static String getSen() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -3);
		return formatDate(calendar.getTime(), "yyyy-MM-dd");
	}
	
	/**
	 * 获取上个月的日期格式(yyyy-MM)
	 */
	public static String getPreMonth(){
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		return formatDate(calendar.getTime(), "yyyy-MM");
	}

	/**
	 * 获取上个月的日期
	 */
	public static String getLastMonth(){
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		return formatDate(calendar.getTime(), "yyyy-MM-dd");
	}
	
	/**
	 * 获取上个月的日期
	 */
	public static String getBefMonth(){
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		return formatDate(calendar.getTime(), "yyyyMMdd");
	}
	
	/**
	 * 获取昨天的开始
	 * 
	 * @return
	 */
	public static Date getYesterdayStart() {
		return getSomeDayStart(-1);
	}

	/**
	 * 获取昨天的结束
	 * 
	 * @return
	 */
	public static Date getYesterdayEnd() {
		return getSomeDayEnd(-1);
	}

	/**
	 * 获取距离当前日期某天的开始
	 * 
	 * @return
	 */
	public static Date getSomeDayStart(int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, day);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 获取距离规定日期某天的开始
	 * 
	 * @return
	 */
	public static Date getSomeDayStart(Date date, int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, day);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 获取距离当前日期某天的结束
	 * 
	 * @return
	 */
	public static Date getSomeDayEnd(Date date, int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, day);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime();
	}

	/**
	 * 获取距离规定日期某天的结束
	 * 
	 * @return
	 */
	public static Date getSomeDayEnd(int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, day);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime();
	}

	/**
	 * 获取明年的日期格式串
	 * 
	 * @param 无
	 * @return Date
	 */
	public static String getNextYear() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, 1);
		return formatDate(calendar.getTime(), "yyyy-MM-dd");
	}

	/**
	 * 获取几年后的日期
	 * 
	 * @param 无
	 * @return Date
	 */
	public static Date getSomeYearAfer(Date date, int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, year);
		return calendar.getTime();
	}

	/**
	 * 获取几天后的日期
	 * 
	 * @param 无
	 * @return Date
	 */
	public static Date getSomeDayAfer(Date date, int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, day);
		return calendar.getTime();
	}

	/**
	 * 判断日期之间的间隔
	 * 
	 * @param
	 * @return boolean
	 */
	public static boolean CompareDays(Date beginTime, Date endTime, int days) {

		calendar.setTime(beginTime);
		int beginDays = calendar.get(Calendar.DAY_OF_YEAR);
		calendar.setTime(endTime);
		int endDays = calendar.get(Calendar.DAY_OF_YEAR);
		if (endDays - beginDays <= days) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断当前时间是否在一定的时间范围内
	 * 
	 * @param String
	 * @return boolean
	 */
	public static boolean isInBetweenTimes(String startTime, String endTime) {
		Date nowTime = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String time = sdf.format(nowTime);
		if (time.compareTo(startTime) >= 0 && time.compareTo(endTime) <= 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断时间是否在一定的时间范围内
	 * 
	 * @param String
	 * @return boolean
	 */
	public static boolean isInBetweenTimes(String startTime, String endTime,
			String times) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String time = sdf.format(times);
		if (time.compareTo(startTime) >= 0 && time.compareTo(endTime) <= 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 时间字符串类型转换日期类型
	 * 
	 * @param String
	 * @return Date
	 */

	public static Date getDateByStr(String dateStr) {
		SimpleDateFormat formatter = null;
		if (dateStr.length() == 10) {
			formatter = new SimpleDateFormat("yyyy-MM-dd");
		} else if (dateStr.length() == 16) {
			formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		} else if (dateStr.length() == 19) {
			formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		} else if (dateStr.length() > 19) {
			dateStr = dateStr.substring(0, 19);
			formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		} else {
			return null;
		}
		try {
			return formatter.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String getFirstDayOfMonth(Date date, String datePattern,
			int day) {

		return DateUtil.formatDate(DateUtil.getFirstDayOfMonth(date, day),
				datePattern);
	}

	public static Date getFirstDayOfMonth(Date date, int a) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int dom = c.get(Calendar.DAY_OF_MONTH);
		c.add(Calendar.DATE, -dom + a);

		return c.getTime();
	}

	/**
	 * 检查curDate是否在startDate和endDate内
	 * 
	 * @param curDate
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static boolean isInDateRange(Date curDate, Date startDate,
			Date endDate) {
		if (startDate == null || curDate == null) {
			return false;
		}

		if (curDate.compareTo(startDate) >= 0) {
			if (endDate == null) {
				return true;
			} else if (curDate.compareTo(endDate) <= 0) {
				return true;
			}
		}
		return false;
	}

	public static int getIntMonth(Date dt) {
		return new Integer(formatDate(dt, "yyyyMM")).intValue();
	}

	public static int getDay(Date dt) {
		Calendar c = Calendar.getInstance();
		c.setTime(dt);
		return c.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 返回上个月的某一天
	 * 
	 * @param a
	 * @return
	 */
	public static Date getUpDayOfMonth(int a) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -1);
		int dom = c.get(Calendar.DAY_OF_MONTH);
		c.add(Calendar.DATE, -dom + a);

		return c.getTime();
	}

	/**
	 * 判断日期之间的间隔的月数
	 * 
	 * @param
	 * @return boolean
	 */
	public static int getMonthNum(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		return (cal2.get(1) - cal1.get(1)) * 12 + (cal2.get(2) - cal1.get(2));
	}

	/**
	 * 判断日期之间的间隔的天数
	 * 
	 * @param
	 * @return Long
	 */
	public static Long getDateDiff(Date d1, Date d2) {
		long diff = 0;
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		try {
			d1 = ft.parse(ft.format(d1));
			d2 = ft.parse(ft.format(d2));
			diff = d2.getTime() - d1.getTime();
			diff = diff / 1000 / 60 / 60 / 24;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Long(diff) + 1l;
	}

	public static boolean checkDate(String date, String format) {
		DateFormat df = new SimpleDateFormat(format);
		try {
			Date d = df.parse(date);
			return date.equals(df.format(d));
		} catch (ParseException e) {
			return false;
		}
	}

	/**
	 * 时间字符串类型转换日期类型
	 * 
	 * @param String
	 * @return Date
	 */

	public static Date getDateByStr(String dateStr, String format) {

		SimpleDateFormat formatter = new SimpleDateFormat(format);

		try {
			return formatter.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Date parseToTodayDesignatedDate(Date date, String time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd " + time);
		Date end = null;
		try {
			end = sdf.parse(sdf1.format(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return end;
	}

	public static Date getDateBegin(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	public static Date getDateEnd(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime();
	}

	/**
	 * 取得日期前（后）month月份的第一天
	 * 
	 * @param date
	 * @param month
	 * @return
	 */
	public static Date getMonthFirstDate(Date date, int month) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getDateBegin(date));
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.MONTH, month);
		return cal.getTime();
	}

	/**
	 * 取得当前月份的第一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getMonthFirstDate(Date date) {
		return getMonthFirstDate(date, 0);
	}

	/**
	 * 取得日期前（后）month月份的最后一天
	 * 
	 * @param date
	 * @param month
	 * @return
	 */
	public static Date getMonthLastDate(Date date, int month) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getMonthFirstDate(date));
		cal.add(Calendar.MONTH, month + 1);
		cal.add(Calendar.MILLISECOND, -1);
		return cal.getTime();
	}

	/**
	 * 取得当前月份的最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getMonthLastDate(Date date) {
		return getMonthLastDate(date, 0);
	}

	public static Date getSomeMonthAfter(Date date, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, month);
		return calendar.getTime();
	}
	
	/**
	 * 获取当前时间前一分钟的时间字符串
	 */
	public static String   getOneMinuteBefo(){
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, -1);
		return formatDate(calendar.getTime(), "HH:mm:ss");
	}
	/**
	 * 获取当前时间前一分钟的时间字符串
	 */
	public static String   getTodayBegin(){
		Date date = new Date();
		Date todBeg = getDateBegin(date);
		
		return formatDate(todBeg, "HH:mm:ss");
	}
	
	public static String getNextMonthFirstDay(){
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar lastDate = Calendar.getInstance();
		lastDate.add(Calendar.MONTH, 1);// 加一个月
		lastDate.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		str = sdf.format(lastDate.getTime());
		return str;
	}
	
	/**
	 * 获取当前时间的偏移时间
	 * c :Calendar时间类型参数
	 * deviationNum :偏移量
	 * yyyy-MM-dd HH:mm:ss
	 */
	public static String getNowTimeDeviation(int c,int deviationNum){
		Calendar calendar = Calendar.getInstance();
		calendar.add(c, deviationNum);
		return formatDate(calendar.getTime(), "yyyy-MM-dd HH:mm:ss");
	}
	
}
