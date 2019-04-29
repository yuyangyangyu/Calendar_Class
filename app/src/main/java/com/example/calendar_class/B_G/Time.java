package com.example.calendar_class.B_G;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Time {
    private int year =0;
    private int month=0;
    private int day=0;
	public static String getDate(String dates,String day) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // 日期格式
		Date date = null;
		Date newDate = null;
		try {
			date = dateFormat.parse(dates); // 指定日期
			newDate = addDate(date, Integer.valueOf(day)); // 指定日期天数
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		System.out.println(dateFormat.format(newDate));
		return dateFormat.format(newDate);
	}
 
	public static Date addDate(Date date, long day) throws ParseException {
		long time = date.getTime(); // 得到指定日期的毫秒数
		day = day * 24 * 60 * 60 * 1000; // 要加上的天数转换成毫秒数
		time += day; // 相加得到新的毫秒数
		// time -= day; // 相减得到新的毫秒数
		return new Date(time); // 将毫秒数转换成日期
	}
	public void Get(String s){
        String ptString="\\d+";
        Pattern pattern = Pattern.compile(ptString);
        Matcher matcher=pattern.matcher(s);
        int L=0;
        while(matcher.find()) {
            L++;
            if (L==1){
                year= Integer.parseInt(matcher.group());

            }
            if (L==2){
                month= Integer.parseInt(matcher.group());

            }
            if (L==3){
                day= Integer.parseInt(matcher.group());

            }

        }
    }

    public int getYear() {

        return year;
    }

    public int getMonth() {

        return month;
    }

    public int getDay() {

        return day;
    }
}
