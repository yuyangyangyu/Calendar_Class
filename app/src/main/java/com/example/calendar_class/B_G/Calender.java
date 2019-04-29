package com.example.calendar_class.B_G;


import android.content.ContentValues;
import android.content.Context;
import android.content.ContextWrapper;
import android.database.Cursor;

import android.net.Uri;
import android.provider.CalendarContract;


import android.util.Log;

import java.util.Calendar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calender extends ContextWrapper {
    private static String calanderURL = "content://com.android.calendar/calendars";
    private static String calanderEventURL = "content://com.android.calendar/events";
    private static String calanderRemiderURL = "content://com.android.calendar/reminders";
    private Cursor userCursor;

    public Calender(Context base) {
        super(base);
    }

    public void Set_Calder(Class_detail detail, Cursor userCursor,long start,long end){
        String linString[] =detail.getTime().split(",");
        for (int i = 0; i < linString.length; i++) {
            if (linString[i].length()<=3) {
                String pattern = "(\\d+)";
                Pattern r = Pattern.compile(pattern);
                Matcher m =r.matcher(linString[i]);
                while (m.find()) {
                    int w=Integer.parseInt(m.group());
                    ContentValues event = new ContentValues();
                    event.put("title", detail.getName());
                    //event.put("description",detail.getPlace_Teacher());
                    // 插入账户
                    event.put("calendar_id", userCursor.getCount());
                    //添加地点
                    event.put("eventLocation", "重庆邮电大学"+detail.getPlace_Teacher());

                    Calendar mCalendar = Calendar.getInstance();
                    Time date = new Time();
                    String  ss=date.getDate("2019-02-25", String.valueOf((detail.getWeek()-1)+(w-1)*7));
                    Log.v("sss",ss);
                    date.Get(ss);

                    mCalendar.set(date.getYear(),date.getMonth()-1,date.getDay());

                    Set_ set =new Set_();
                    set.Set_Time(mCalendar,detail,event,start,end);
                    event.put(CalendarContract.Events.EVENT_TIMEZONE, "Asia/Shanghai");  //这个是时区，必须有，
                    //添加事件
                    Uri newEvent = getContentResolver().insert(Uri.parse(calanderEventURL), event);
                    //事件提醒的设定
                    long id = Long.parseLong(newEvent.getLastPathSegment());
                    ContentValues values = new ContentValues();
                    values.put("event_id", id);
                    // 提前30分钟有提醒
                    values.put("minutes", 30);
                    getContentResolver().insert(Uri.parse(calanderRemiderURL), values);

                }
            }
            else {
                String pattern = "(\\d+)";
                Pattern r = Pattern.compile(pattern);
                Matcher m =r.matcher(linString[i]);
                int L=0;
                //求的时间段
                int a = 0,b = 0;
                while (m.find()) {
                    L++;
                    if (L==1) {
                        a=Integer.parseInt(m.group());

                    }
                    if (L==2){
                        b=Integer.parseInt(m.group());
                    }
                }
                //输出时长
                System.out.println(b-a);//时长

                ContentValues event = new ContentValues();
                event.put("title", detail.getName());
                //event.put("description",detail.getPlace_Teacher());
                // 插入账户
                event.put("calendar_id", userCursor.getCount());
                //添加地点
                event.put("eventLocation", "重庆邮电大学"+detail.getPlace_Teacher());
                Calendar mCalendar = Calendar.getInstance();
                Time date = new Time();
                String  ss=date.getDate("2019-02-25", String.valueOf((detail.getWeek()-1)+(a-1)*7));
                Log.v("sss",ss);
                date.Get(ss);
                mCalendar.set(date.getYear(),date.getMonth()-1,date.getDay());
                //设置重复事件
                if(detail.getStyle()==1|detail.getStyle()==2){
                    event.put(CalendarContract.Events.RRULE,String.format("FREQ=WEEKLY;COUNT=%d;INTERVAL=2;WKST=SU",(b-a)/2+1));
                    Set_ set =new Set_();
                    set.Set_Time(mCalendar,detail,event,start,end);
                    event.put(CalendarContract.Events.EVENT_TIMEZONE, "Asia/Shanghai");  //这个是时区，必须有，
                    //添加事件
                    Uri newEvent = getContentResolver().insert(Uri.parse(calanderEventURL), event);
                    //事件提醒的设定
                    long id = Long.parseLong(newEvent.getLastPathSegment());
                    ContentValues values = new ContentValues();
                    values.put("event_id", id);
                    // 提前30分钟有提醒
                    values.put("minutes", 30);
                    getContentResolver().insert(Uri.parse(calanderRemiderURL), values);
                }
                else{
                    Log.v("sss","enenene");
                    event.put(CalendarContract.Events.RRULE,String.format("FREQ=WEEKLY;COUNT=%d",(b-a)+1));
                    Set_ set =new Set_();
                    set.Set_Time(mCalendar,detail,event,start,end);
                    event.put(CalendarContract.Events.EVENT_TIMEZONE, "Asia/Shanghai");  //这个是时区，必须有，
                    //添加事件
                    Uri newEvent = getContentResolver().insert(Uri.parse(calanderEventURL), event);
                    //事件提醒的设定
                    long id = Long.parseLong(newEvent.getLastPathSegment());
                    ContentValues values = new ContentValues();
                    values.put("event_id", id);
                    // 提前30分钟有提醒
                    values.put("minutes", 30);
                    getContentResolver().insert(Uri.parse(calanderRemiderURL), values);
                }
            }

        }
    }
}
