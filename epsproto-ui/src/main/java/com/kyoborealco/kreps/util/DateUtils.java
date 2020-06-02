package com.kyoborealco.kreps.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * 날짜 유틸리티 모음
 * </pre>
 */
@Slf4j
public class DateUtils {
	
	/**
	 * 날짜 문자열로 변환
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String toString(Date date, String format) {
	
		DateFormat df = new SimpleDateFormat(format);
    	
		return df.format(date);
	}
	
	public static String addSeperator(String date, String seperator) {
		
		log.debug("★★★★★★ => ", date);
		
		// yyyyMMdd => yyyy-MM-dd
		String yyyy = date.substring(0,4);
		String mm =  date.substring(4,6);
		String dd =  date.substring(6,8);
		
		return String.format("%s-%s-%s", yyyy, mm, dd);
	}
	
	/**
	 * 날짜차이 계산
	 */
	public static long dateBetweens(Date startDate, Date endDate) {

		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(startDate);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(endDate);

		return daysBetween(cal1, cal2);
	}
	
	private static long daysBetween(Calendar startDate, Calendar endDate) {
		Calendar date = (Calendar) startDate.clone();
		long daysBetween = 0;
		while (date.before(endDate)) {
			date.add(Calendar.DAY_OF_MONTH, 1);
			daysBetween++;
		}
		return daysBetween;
	}
	
	public static String timeBetween(Date startDate, Date endDate) {
		double seconds = (endDate.getTime() - startDate.getTime()) / 1000;
		
		double minutes = Math.floor( seconds/60 );
		seconds = seconds % 60;
		
		double hours = Math.floor( minutes/60 );
		minutes = minutes % 60;
		
		
		return (hours>0? doubleToIntString(hours) +"시간 ":"") 
				+ (minutes>0? doubleToIntString(minutes) +"분 ":"")
				+ doubleToIntString(seconds)+"초";
	}
	
	private static String doubleToIntString(double number) {
		return (""+number).split("\\.")[0];
	}

	/**
	 * 특정날짜에 일자 더하기 (format = yyyyMMdd 고정)
	 * 
	 * @param date
	 * @param day
	 * @param format 문자형타입 (ex. yyyyMMdd, yyyy-MM-dd, ...)
	 * @return
	 */
    public static String addDays(String date, int day, String format) {
    	DateFormat df = new SimpleDateFormat(format);
    	String ret = null;
    	
    	try {
        	Date d = df.parse(date);
        	
        	// 날짜 더하기
        	Calendar cal = Calendar.getInstance();
        	cal.setTime(d);
        	cal.add(Calendar.DATE, day);
        	
        	// Date형을 String형으로 
        	ret = df.format(cal.getTime()); 
    	}
    	catch (ParseException e) {
    		ret = null;
    	}
    	
        return ret; 
    }

    /**
     * 특정날짜에 일자 더하기 (format = yyyyMMdd 고정)
     * 
     * @param date
     * @param day
     * @return
     */
    public static String addDays(String date, int day) {

        return addDays(date, day, "yyyyMMdd");
    }
    
    
    public static Date mobileToDate(String value) {
    	String format = "yyyy-MM-dd HH:mm:ss";
    	return toDate(value, format);
    }
    
    public static Date toDate(String value, String format) {
    	
    	Date convResult = new Date();
    	SimpleDateFormat sdf = new SimpleDateFormat(format);
    	try {
    		convResult = sdf.parse(value);
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	
    	return convResult;
    }

    /**
	 * 현재시간에서 자정(24:00:00)까지 시간차이
	 * 
	 * @return
	 */
	public static long diffNowTo2400() {
		
		String endOfTime = "24:00:00";
		Date now = new Date();
		Date eot = null;

		SimpleDateFormat sdfday = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdfdatetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String dayStr = sdfday.format(now);
		
		try {
			eot = sdfdatetime.parse(String.format("%s %s", dayStr, endOfTime));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		log.debug("now => {}", now);
		log.debug("eot => {}", eot);
		
		long diff = eot.getTime() - now.getTime(); 
		
		return diff;
	}
}

