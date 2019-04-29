package com.example.calendar_class.B_G;

import android.content.ContentValues;
import android.util.Log;

import java.util.Calendar;

public class Set_ {
    public void Set_Time(Calendar mCalendar, Class_detail detail, ContentValues event, long start, long end){
        switch (detail.getDay()){
            case 0:
                mCalendar.set(Calendar.HOUR_OF_DAY, 8);
                mCalendar.set(Calendar.MINUTE, 00);
                start = mCalendar.getTime().getTime();
                if (detail.getStyle()==3){
                    mCalendar.set(Calendar.HOUR_OF_DAY, 11);
                    mCalendar.set(Calendar.MINUTE, 00);
                }
                else if (detail.getStyle()==4){
                    mCalendar.set(Calendar.HOUR_OF_DAY, 11);
                    mCalendar.set(Calendar.MINUTE, 55);
                }
                else{
                    mCalendar.set(Calendar.HOUR_OF_DAY, 9);
                    mCalendar.set(Calendar.MINUTE, 40);
                }
                end = mCalendar.getTime().getTime();
                event.put("dtstart", start);
                event.put("dtend", end);
                event.put("hasAlarm", 1);
                break;
            case 1:
                mCalendar.set(Calendar.HOUR_OF_DAY, 10);
                mCalendar.set(Calendar.MINUTE, 15);
                start = mCalendar.getTime().getTime();
                mCalendar.set(Calendar.HOUR_OF_DAY, 11);
                mCalendar.set(Calendar.MINUTE, 55);
                end = mCalendar.getTime().getTime();
                event.put("dtstart", start);
                event.put("dtend", end);
                event.put("hasAlarm", 1);
                break;
            case 3:
                mCalendar.set(Calendar.HOUR_OF_DAY, 14);
                mCalendar.set(Calendar.MINUTE, 00);
                start = mCalendar.getTime().getTime();
                if (detail.getStyle()==3){
                    Log.v("sss","3333333");
                    mCalendar.set(Calendar.HOUR_OF_DAY, 17);
                    mCalendar.set(Calendar.MINUTE, 00);
                }
                else if (detail.getStyle()==4){
                    mCalendar.set(Calendar.HOUR_OF_DAY, 17);
                    mCalendar.set(Calendar.MINUTE, 55);
                }
                else{
                    mCalendar.set(Calendar.HOUR_OF_DAY, 15);
                    mCalendar.set(Calendar.MINUTE, 40);
                }
                end = mCalendar.getTime().getTime();
                event.put("dtstart", start);
                event.put("dtend", end);
                event.put("hasAlarm", 1);
                break;
            case 4:
                mCalendar.set(Calendar.HOUR_OF_DAY, 16);
                mCalendar.set(Calendar.MINUTE, 15);
                start = mCalendar.getTime().getTime();
                mCalendar.set(Calendar.HOUR_OF_DAY, 17);
                mCalendar.set(Calendar.MINUTE, 55);
                end = mCalendar.getTime().getTime();
                event.put("dtstart", start);
                event.put("dtend", end);
                event.put("hasAlarm", 1);
                break;
            case 6:
                mCalendar.set(Calendar.HOUR_OF_DAY, 19);
                mCalendar.set(Calendar.MINUTE, 00);
                start = mCalendar.getTime().getTime();
                if (detail.getStyle()==3){
                    mCalendar.set(Calendar.HOUR_OF_DAY,22);
                    mCalendar.set(Calendar.MINUTE, 00);
                }
                else if (detail.getStyle()==4){
                    mCalendar.set(Calendar.HOUR_OF_DAY, 22);
                    mCalendar.set(Calendar.MINUTE, 55);
                }
                else{
                    mCalendar.set(Calendar.HOUR_OF_DAY, 20);
                    mCalendar.set(Calendar.MINUTE, 40);
                }
                end = mCalendar.getTime().getTime();
                event.put("dtstart", start);
                event.put("dtend", end);
                event.put("hasAlarm", 1);
                break;
            case 7:
                mCalendar.set(Calendar.HOUR_OF_DAY, 21);
                mCalendar.set(Calendar.MINUTE, 15);
                start = mCalendar.getTime().getTime();
                mCalendar.set(Calendar.HOUR_OF_DAY, 22);
                mCalendar.set(Calendar.MINUTE, 55);
                end = mCalendar.getTime().getTime();
                event.put("dtstart", start);
                event.put("dtend", end);
                event.put("hasAlarm", 1);
                break;
        }
    }
}
