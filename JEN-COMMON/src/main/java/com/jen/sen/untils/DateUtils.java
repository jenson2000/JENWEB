package com.jen.sen.untils;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.SimpleTimeZone;

/**
 * 
 * filename DateUtils.java
 * company jen
 * @author jenson
 * @email jenson2000@sina.com
 */
public abstract class DateUtils {

    public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";


    /**
     * Private constructor
     */
    private DateUtils() {
    }

    public static Date now() {
        return new Date();
    }
    
    public static Date getNowDate() {  
        Date currentTime = new Date();  
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        String dateString = formatter.format(currentTime);  
        ParsePosition pos = new ParsePosition(8);  
        Date currentTime_2 = formatter.parse(dateString, pos);  
        return currentTime_2;  
    }  


    //Create new  SimpleDateFormat
    private static SimpleDateFormat newDateFormat(String pattern) {
        return new SimpleDateFormat(pattern, Locale.SIMPLIFIED_CHINESE);
    }

    public static String toDateTime(Date date) {
        return toDateText(date, DEFAULT_DATE_TIME_FORMAT);
    }


    public static String toDateText(Date date, String pattern) {
        if (date == null || pattern == null) {
            return new SimpleDateFormat(pattern).format(new Date());
        }
        SimpleDateFormat dateFormat = newDateFormat(pattern);
        return dateFormat.format(date);
    }

    public static Date strToDate(String dateStr){
    	try {
			return new SimpleDateFormat(DEFAULT_DATE_TIME_FORMAT).parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
    }
    
    public static Date strToDateWithFormat(String dateStr,String format){
    	try {      
    		if(Util.isNullOrEmpty(dateStr)){
    			return null;
    		}else{
			return new SimpleDateFormat(format).parse(dateStr);
    		}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
    }
    
    
    public static Date mongoDbTimeToLocalDate(Object o){
	    	if (o instanceof Date) {
	            Date d = (Date) o;
	            SimpleDateFormat format1 =  new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
	            String s=format1.format(d);
	            try {
	            	SimpleDateFormat returnFormat =  new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
	 	            returnFormat.setCalendar(new GregorianCalendar(new SimpleTimeZone(0, "GMT")));
					return returnFormat.parse(s);
				} catch (ParseException e) {
					e.printStackTrace();
				}
	    	}
			return null;
    }
    
    
	public static Date MongoDbTimeToDate(Object mongodbTime) {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		Date returnD=null;
		try {
			if (mongodbTime instanceof Date) {
				Date d = (Date) mongodbTime;
				returnD= d;
			} else {
				returnD= format1.parse(mongodbTime.toString());
			}
		} catch (ParseException e) {
			try {
				returnD=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(mongodbTime.toString());
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}
		return returnD;
	}
	
	public static String getStrDateByAdd(Date date,int dayCount,String formatterStr){
		 Calendar calendar = new GregorianCalendar();
		 calendar.setTime(date);
		 calendar.add(calendar.DATE,dayCount);//把日期往后增加一天.整数往后推,负数往前移动
		 date=calendar.getTime(); //这个时间就是日期往后推一天的结果 
		 SimpleDateFormat formatter = new SimpleDateFormat(formatterStr);
		 String dateString = formatter.format(date);
		 return dateString;
	}
	
	
	public static Date getDateByAdd(Date date,int dayCount){
		 Calendar calendar = new GregorianCalendar();
		 calendar.setTime(date);
		 calendar.add(calendar.DATE,dayCount);//把日期往后增加一天.整数往后推,负数往前移动
		 date=calendar.getTime(); //这个时间就是日期往后推一天的结果 
		 return date;
	}
	
	public static String getTongJiQueryStartTime(String time){
		String startTime=getStrDateByAdd(DateUtils.strToDateWithFormat(time, "yyyy-MM-dd"), 0, "yyyy-MM-dd")+" 00:00:00";
		return startTime;
	}
	
	public static String getTongJiQueryEndTime(String time){
		String startTime=getStrDateByAdd(DateUtils.strToDateWithFormat(time, "yyyy-MM-dd"), 1, "yyyy-MM-dd")+" 00:00:00";
		return startTime;
	}
	
	
	/** 

     * 计算两个日期之间相差的天数 

     * @param date1 

     * @param date2 

     * @return 

     */  

    public static int daysBetween(Date date1,Date date2)  

    {  

        Calendar cal = Calendar.getInstance();  

        cal.setTime(date1);  

        long time1 = cal.getTimeInMillis();               

        cal.setTime(date2);  

        long time2 = cal.getTimeInMillis();       

        long between_days=(time2-time1)/(1000*3600*24);  

          

       return Integer.parseInt(String.valueOf(between_days));         

    } 
    
    
	public static String getStrDateByAddMonth(Date date,int monthCount,String formatterStr){
		 Calendar calendar = new GregorianCalendar();
		 calendar.setTime(date);
		 calendar.add(calendar.MONTH,monthCount);//把日期往后增加一个月.整数往后推,负数往前移动
		 date=calendar.getTime(); //这个时间就是日期往后推一个月的结果 
		 SimpleDateFormat formatter = new SimpleDateFormat(formatterStr);
		 String dateString = formatter.format(date);
		 return dateString;
	}
    
}