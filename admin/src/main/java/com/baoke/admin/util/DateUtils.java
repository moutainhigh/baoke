package com.baoke.admin.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Title: DateUtils.java
 * @Description: 时间工具类
 */
public class DateUtils {
	
	public static final String DateFormatFullStr = "yyyy-MM-dd HH:mm:ss";
	public static final String DateFormatDateStr = "yyyy-MM-dd";
	
	/**
	 * 多格式日期解析
	 * 
	 * @param s
	 * @return
	 */
	public static final Date mutiPatternParse(String str) {
		Date d = null;

		if (str == null || str.trim().isEmpty())
			return d;

		try {
			d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str);
		} catch (Exception e) {
		}

		if (d == null) {
			try {
				d = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(str);
			} catch (ParseException e) {
			}
		}

		if (d == null) {
			try {
				d = new SimpleDateFormat("yyyy-MM-dd").parse(str);
			} catch (ParseException e) {
			}
		}
		return d;
	}

	/**
	 * 将日期格式化 字符串
	 * 
	 * @param date
	 *            日期
	 * @param format
	 *            时间格式
	 * @return
	 */
	public static final String formatDate(Date date, String format) {
		return date == null ? "" : new SimpleDateFormat(format).format(date);
	}

	/**
	 * 日期转换成字符串时分 格式为HH:mm
	 * 
	 * @param date
	 * @return
	 */
	public static final String formatDateHM(Date date) {
		if (date == null) {
			return "";
		}
		DateFormat hmFormat = new SimpleDateFormat("HH:mm");
		return hmFormat.format(date);
	}

	/**
	 * 日期转换成字符串时分秒 格式为HH:mm:ss
	 * 
	 * @param date
	 * @return
	 */
	public static final String formatDateHMS(Date date) {
		if (date == null) {
			return "";
		}
		DateFormat hmsFormat = new SimpleDateFormat("HH:mm:ss");
		return hmsFormat.format(date);
	}

	/**
	 * 日期转成字符串 格式为yyyy-MM
	 * 
	 * @param date
	 * @return
	 */
	public static final String formatDateYM(Date date) {
		if (date == null) {
			return "";
		}
		DateFormat ymdFormat = new SimpleDateFormat("yyyy-MM");
		return ymdFormat.format(date);
	}
	
	/**
	 * 日期转成字符串 格式为yyyy-MM-dd
	 * 
	 * @param date
	 * @return
	 */
	public static final String formatDateYMD(Date date) {
		if (date == null) {
			return "";
		}
		DateFormat ymdFormat = new SimpleDateFormat("yyyy-MM-dd");
		return ymdFormat.format(date);
	}

	/**
	 * 日期转换成字符串 格式为yyyy-MM-dd HH:mm
	 * 
	 * @param date
	 * @return
	 */
	public static final String formatDateYMDHM(Date date) {
		if (date == null) {
			return "";
		}

		DateFormat ymdhmFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return ymdhmFormat.format(date);
	}

	/**
	 * 日期转换成字符串 格式为yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 * @return
	 */
	public static final String formatDateYMDHMS(Date date) {
		if (date == null) {
			return "";
		}
		DateFormat ymdhmsFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return ymdhmsFormat.format(date);
	}
	
	/**
	 * 将字符串 格式化成 时间格式
	 * 
	 * @param format
	 *            时间格式
	 * @param str
	 *            时间文本
	 * @return
	 * @throws ParseException
	 */
	public static final Date parseDate(String str, String format)
			throws ParseException {
		SimpleDateFormat bartDateFormat = new SimpleDateFormat(format);
		return bartDateFormat.parse(str);
	}
	
	/**
	 * 把字符串转换成yyyy-MM-dd格式的日期对象
	 * 
	 * @param str
	 * @return
	 * @throws ParseException
	 */
	public static final Date parseDateYMD(String str) throws ParseException {
		return new SimpleDateFormat("yyyy-MM-dd").parse(str);
	}
	
	/**
	 * 把字符串转换成yyyy-MM-dd HH:mm格式的日期对象
	 * 
	 * @param str
	 * @return
	 * @throws ParseException
	 */
	public static final Date parseDateYMDHM(String str) throws ParseException {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(str);
	}
	
	/**
	 * 把字符串转换成yyyy-MM-dd HH:mm:ss格式的日期对象 
	 * 
	 * @param str
	 * @return
	 * @throws ParseException
	 */
	public static final Date parseDateYMDHMS(String str) throws ParseException{
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str);
	}
	
	/**
	 * 两个时间之间相差大于days天
	 * 
	 * @param date
	 * @param createTime
	 * @param days
	 * @return
	 */
	public static final boolean getDistanceDays(Date date, Date createTime, long days) {
		try {
			long time1 = date.getTime();
			long time2 = createTime.getTime();
			long diff;
			if (time1 < time2) {
				diff = time2 - time1;
			} else {
				diff = time1 - time2;
			}

			if (diff > (1000 * 60 * 60 * 24) * days) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 判断给定的时间是否是今天
	 * 
	 * @param day
	 * @return
	 */
	public static final boolean isToday(Date date) {
		if (date == null) {
			return false;
		} else {
			String dateStr = new SimpleDateFormat(DateFormatDateStr).format(date);
			return isToday(dateStr);
		}
	}
	
	/**
	 * 判断给定的时间是否是今天
	 * 
	 * @param str
	 * @return
	 */
	public static final boolean isToday(String str) {
		if (StringUtils.isBlank(str)) {
			return false;
		}
		String today = new SimpleDateFormat(DateFormatDateStr).format(new Date());
		if (today.equals(str)) {
			return true;
		} else {
			return false;
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
	 * 获取指定日期的结束时间 23点59分59秒
	 * 
	 * @param date
	 * @return
	 */
	public static final Date getLastTimeOfDate(Date date) {
		if (date == null) {
			return null;
		}
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		return cal.getTime();
	}

	/**
	 * 得到本周周一 0点0分0秒的时间时间
	 */
	public static final Date getMondayOfThisWeek() {
		Calendar calendar = Calendar.getInstance();
		int day_of_week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0)
			day_of_week = 7;
		calendar.add(Calendar.DATE, -day_of_week + 1);

		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 得到本周周日23点59分59秒的时间
	 */
	public static final Date getSundayOfThisWeek() {
		Calendar calendar = Calendar.getInstance();
		int day_of_week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0)
			day_of_week = 7;
		calendar.add(Calendar.DATE, -day_of_week + 7);

		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}
	
	/**
	 * 当前时间是否在 star和end之间
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static boolean isBetweenInTime(Date startTime, Date endTime) {
		if (startTime == null || endTime == null) {
			return false;
		}
		Date now = new Date();
		if (now.compareTo(startTime) >= 0 && now.compareTo(endTime) <= 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 根据seconds大小，时间单位 从小到大求整数商
	 * @param seconds
	 * @return
	 */
	public static String getTimeDescBySeconds(long seconds){
		if(seconds < 60L){
			return seconds + "秒";
		}else if(60L  <= seconds&& seconds< 3600L){
			return seconds/60L + "分钟";
		}else if(3600L  <= seconds&& seconds< 43200L){
			return seconds/3600L + "小时";
		}else{
			return seconds/43200L + "天";
		}
	}
	
	
}
