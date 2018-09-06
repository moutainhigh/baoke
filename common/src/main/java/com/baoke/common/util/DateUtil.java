package com.baoke.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 * 
 * @author zjm
 * @date: 2018年6月15日 下午6:06:35
 */
public class DateUtil {

	public static final String DATE_MONTH_FORMAT_STR = "MM-dd";

	public static final String DATE_DAY_FORMAT_STR = "yyyy-MM-dd";

	public static final String DATE_MINUTE_FORMAT_STR = "yyyy-MM-dd HH:mm";

	public static final String DATE_TIME_FORMAT_STR = "yyyy-MM-dd HH:mm:ss";

	public static final String DATE_TIME_EMPTY_SPLIT_FORMAT_STR = "yyyyMMddHHmmss";

	/**
	 * 转化时间格式为String. date: 2018年5月22日 下午2:15:07
	 * 
	 * @author zjm
	 * @param date
	 * @param format
	 * @return
	 */
	public static String format(Date date, SimpleDateFormat format) {
		return format.format(date);
	}

	/**
	 * 默认转换方式，yyyy-MM-dd HH:mm:ss
	 * 
	 * @author zjj
	 * @date 2018年7月21日 下午5:26:30
	 * @param date
	 * @return
	 */
	public static String format(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_TIME_FORMAT_STR);
		return dateFormat.format(date);
	}

	/**
	 * 转化时间格式为String. date: 2018年5月22日 下午2:20:24
	 * 
	 * @author zjm
	 * @param date
	 * @param formatStr
	 * @return
	 */
	public static String format(Date date, String formatStr) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(formatStr);
		return dateFormat.format(date);
	}

	/**
	 * 把yyyy-MM-dd HH:mm:ss格式的字符串转换成日期对象
	 */
	public static Date parseForSecond(String dateStr) throws ParseException {
		return new SimpleDateFormat(DATE_TIME_FORMAT_STR).parse(dateStr);
	}

	/**
	 * 把yyyy-MM-dd HH:mm格式的日期字符串转换成日期对象
	 */
	public static Date parseForMinute(String dateStr) throws ParseException {
		return new SimpleDateFormat(DATE_MINUTE_FORMAT_STR).parse(dateStr);
	}

	/**
	 * 把字符串转换成yyyy-MM-dd格式成日期对象
	 */
	public static Date parseForDay(String dateStr) throws ParseException {
		return new SimpleDateFormat(DATE_DAY_FORMAT_STR).parse(dateStr);
	}

	/**
	 * 
	 * 转化时间String格式为Date.
	 * 
	 * date: 2018年5月22日 下午2:17:02
	 * 
	 * @author zjm
	 * @param dateStr
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	public static Date parse(String dateStr, SimpleDateFormat format) throws ParseException {
		return format.parse(dateStr);
	}

	/**
	 * 
	 * 转化时间String格式为Date.
	 * 
	 * date: 2018年5月22日 下午2:21:06
	 * 
	 * @author zjm
	 * @param dateStr
	 * @param formatStr
	 * @return
	 * @throws ParseException
	 */
	public static Date parse(String dateStr, String formatStr) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(formatStr);
		return dateFormat.parse(dateStr);
	}

	/**
	 * 
	 * addSeconds:(增加毫秒数).
	 * 
	 * date: 2018年6月6日 下午2:31:35
	 * 
	 * @author zjm
	 * @param date
	 * @param seconds
	 * @return
	 */
	public static Date addMillis(Date date, Long millis) {

		if (null == date) {
			throw new RuntimeException("时间不能为空");
		}

		return new Date(date.getTime() + millis);
	}

	/**
	 * 获取某一时刻距离现在的时间差，以字符串返回，返回如下几种类型： 1分钟之内 -> "刚刚" 1小时之类 -> "X分钟前" 1天之类 ->
	 * "X小时前" 24-48小时 -> "昨天" 大于48小时 -> "5-22"(月份和日期)
	 * 
	 * @author zjj
	 * @date 2018年6月15日 下午2:49:42
	 * @param date
	 * @return
	 */
	public static String timePassedFromNow(Date date) {
		if (null == date) {
			throw new RuntimeException("时间不能为空");
		}
		long passed = new Date().getTime() - date.getTime();
		if (passed < 0) {
			throw new RuntimeException("时间不合法");
		}
		if (passed < 1000l * 60) {
			return "刚刚";
		} else if (passed < 1000l * 60 * 60) {
			return (passed / (1000l * 60)) + "分钟前";
		} else if (passed < 1000l * 60 * 60 * 24) {
			return (passed / (1000l * 60 * 60)) + "小时前";
		} else if (passed < 1000l * 60 * 60 * 24 * 2) {
			return "昨天";
		} else {
			return format(date, DATE_MONTH_FORMAT_STR);
		}
	}

	/**
	 * 获取指定日期的开始时间 0点0分0秒
	 * 
	 * @param date
	 * @return
	 */
	public static final Date getFirstTimeOfDate(Date date) {
		if (date == null) {
			return null;
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 获取月份
	 * 
	 * @author zjj
	 * @date 2018年7月21日 下午5:32:21
	 * @param date
	 * @return
	 */
	public static int getMonth(Date date) {
		if (date == null) {
			return 0;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.MONTH);
	}

	/**
	 * 判断两个时间是否在同一自然月
	 * 
	 * @author zdy
	 * @date: 2018年7月25日 下午8:55:06
	 * @param date
	 * @return
	 */
	public static boolean dateYearMonthCompare(Date startDate, Date endTime) {
		if (startDate == null || endTime == null) {
			return false;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		int startYear = cal.get(Calendar.YEAR);
		int startMonth = cal.get(Calendar.MONTH);

		cal.setTime(endTime);
		int endYear = cal.get(Calendar.YEAR);
		int endMonth = cal.get(Calendar.MONTH);

		if (startYear == endYear && startMonth == endMonth) {
			return true;
		}
		return false;
	}

	/**
	 * 获取前N天的当前时间
	 * 
	 * @param date
	 * @return
	 */

	public static Date getLastday(int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -day);
		return calendar.getTime();
	}

	public static void main(String[] args) {
		Date date = new Date(new Date().getTime() - 2223000);
		System.out.println(timePassedFromNow(date));

		System.out.println(DateUtil.getLastday(1).toString());

		try {
			Date startTime = DateUtil.parseForMinute("2015-07-03 12:00:00");
			System.out.println(dateYearMonthCompare(startTime, new Date()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
