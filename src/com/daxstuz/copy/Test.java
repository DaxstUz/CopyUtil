package com.daxstuz.copy;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test {

	public static void main(String[] args) throws ParseException {
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
//		Long tLong=System.currentTimeMillis();
//		Long tLong=-28800000L;
//		System.out.println(tLong);
//		System.out.println(simpleDateFormat.format(tLong));
//		1495595738380
//		System.out.println(simpleDateFormat.format("2017-04-26 11:34:17"));
//		System.out.println(simpleDateFormat.format(simpleDateFormat.parse("2017-04-26 11:34:17.423")));
	
		System.out.println(getAge(0+""));
	}
	
	   public static Date StringToDate2(String strTime) {
	        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	        Long time = Long.parseLong(strTime);
	        String d = format.format(time);

	        Date date = null;
	        try {
	            date = format.parse(d);
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	        return date;
	    }
	   
	   public static int getAge(String time) {
	        Calendar cal = Calendar.getInstance();
	        int year = cal.get(Calendar.YEAR);
	        if (StringToDate2(time) != null) {
	            int age = year -StringToDate2(time).getYear() - 1900;
	            return age;
	        } else {
	            return 0;
	        }
	    }
}
