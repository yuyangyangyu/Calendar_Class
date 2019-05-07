package com.example.calendar_class;

import android.Manifest;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.CalendarContract;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.calendar_class.B_G.Calender;
import com.example.calendar_class.B_G.Class_detail;
import com.example.calendar_class.B_G.Methon;
import com.example.calendar_class.B_G.MyAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;
public class MainActivity extends AppCompatActivity {
    private static String calanderURL = "content://com.android.calendar/calendars";
    private static String calanderEventURL = "content://com.android.calendar/events";
    private static String calanderRemiderURL = "content://com.android.calendar/reminders";
    //添加本地账户
    private static String CALENDARS_NAME = "test";
    private static String CALENDARS_ACCOUNT_NAME = "test@gmail.com";
    private static String CALENDARS_ACCOUNT_TYPE = "com.android.exchange";
    private static String CALENDARS_DISPLAY_NAME = "测试账户";

    static String State = "线路1";

    private long start;
    private long end;
    private Button OK;
    private Spinner Sp;
    private EditText Stu_num;
    private MyAdapter myAdapter;
    ArrayList<Class_detail> list =new ArrayList<>();
    private static boolean Flag =false;
    private Cursor userCursor;
    private ArrayList<String> list_ch=new ArrayList<>();
    private ArrayAdapter<String> adapter_ch;
    //关于动态权限的申请(对日历的读写和网络请求)
    private String[] permissions =new String[]{Manifest.permission.WRITE_CALENDAR,Manifest.permission.READ_CALENDAR,
    Manifest.permission.INTERNET};
    private List<String> mpermissions =new ArrayList<>();
    private final int mrequestcode=100;
    private void initperssion(){
        mpermissions.clear();
        for (int i=0;i<permissions.length;i++){
            if (ContextCompat.checkSelfPermission(this,permissions[i])!=
                    PackageManager.PERMISSION_GRANTED){
                mpermissions.add(permissions[i]);
            }
        }
        if (mpermissions.size()>0){
            //Log.v("sss","2222222");
            ActivityCompat.requestPermissions(this,permissions,mrequestcode);
        }
        else{
            userCursor = getContentResolver().query(Uri.parse(calanderURL), null, null, null, null);
        }
    }

    class Back_Task extends AsyncTask<Void,Integer,Boolean>{
        private String Num;
        public Back_Task(String num){
            this.Num=num;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(getBaseContext(),"请稍后...",Toast.LENGTH_SHORT).show();
        }
        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (aBoolean==true){
                Toast.makeText(getBaseContext(),"完成后台获取",Toast.LENGTH_SHORT).show();
                for (int i =0;i<list.size();i++){
                    Log.v("sss",list.get(i).getName());
                }
                Flag = true;
            }
            else{
                Toast.makeText(getBaseContext(),"出错，请检查学号,网络或更换线路",Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            Methon methon =new Methon();
            methon.Get_class(Num,State);
            list=Methon.getList();
            if(list.size()!=0){
                return true;
            }
            else{
                return false;
            }
        }
    }
    //Class_detail detail =new Class_detail("A1100050-毛泽东思想和中国特色社会主义理论体系"," 3502 李净 ","1-16周3节连上",3,2,3);
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initperssion();
        OK=findViewById(R.id.OK);
        //增加线路
        Sp=findViewById(R.id.sp);
        list_ch.add("线路1");
        list_ch.add("线路2");
        adapter_ch=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list_ch);
        Sp.setAdapter(adapter_ch);
        Sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                State=adapter_ch.getItem(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        Stu_num=findViewById(R.id.Stu_num);
        Stu_num.setInputType(InputType.TYPE_CLASS_NUMBER);
        OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    //加载后台数据
                    list.clear();
                    new Back_Task(Stu_num.getText().toString()).execute();
            }
        });
        Button button = findViewById(R.id.readUserButton);
        Button button1 = findViewById(R.id.writeEventButton);
        //final Cursor userCursor = getContentResolver().query(Uri.parse(calanderURL), null, null, null, null);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Cursor userCursor = getContentResolver().query(Uri.parse(calanderURL), null, null, null, null);
                Toast.makeText(MainActivity.this, "Count: " + userCursor.getCount(), Toast.LENGTH_LONG).show();
                if (userCursor.getCount()==0)//添加本地账户
                {
                    addCalendarAccount(getBaseContext());
                }
                    RecyclerView recyclerView =findViewById(R.id.classes);
                    GridLayoutManager layoutManager =new GridLayoutManager(getBaseContext(),1);
                    recyclerView.setLayoutManager(layoutManager);
                    myAdapter=new MyAdapter(list);
                    recyclerView.setAdapter(myAdapter);
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i=0;i<list.size();i++){
                    Calender calender =new Calender(getBaseContext());
                    calender.Set_Calder(list.get(i),userCursor,start,end);
                }
                Toast.makeText(v.getContext(),"✅",Toast.LENGTH_SHORT).show();
            }
        });
    }
    private static long addCalendarAccount(Context context) {
        TimeZone timeZone = TimeZone.getDefault();
        ContentValues value = new ContentValues();
        value.put(CalendarContract.Calendars.NAME, CALENDARS_NAME);
        value.put(CalendarContract.Calendars.ACCOUNT_NAME, CALENDARS_ACCOUNT_NAME);
        value.put(CalendarContract.Calendars.ACCOUNT_TYPE, CALENDARS_ACCOUNT_TYPE);
        value.put(CalendarContract.Calendars.CALENDAR_DISPLAY_NAME, CALENDARS_DISPLAY_NAME);
        value.put(CalendarContract.Calendars.VISIBLE, 1);
        value.put(CalendarContract.Calendars.CALENDAR_COLOR, Color.BLUE);
        value.put(CalendarContract.Calendars.CALENDAR_ACCESS_LEVEL, CalendarContract.Calendars.CAL_ACCESS_OWNER);
        value.put(CalendarContract.Calendars.SYNC_EVENTS, 1);
        value.put(CalendarContract.Calendars.CALENDAR_TIME_ZONE, timeZone.getID());
        value.put(CalendarContract.Calendars.OWNER_ACCOUNT, CALENDARS_ACCOUNT_NAME);
        value.put(CalendarContract.Calendars.CAN_ORGANIZER_RESPOND, 0);
        Uri calendarUri = Uri.parse(calanderURL);
        calendarUri = calendarUri.buildUpon()
                .appendQueryParameter(CalendarContract.CALLER_IS_SYNCADAPTER, "true")
                .appendQueryParameter(CalendarContract.Calendars.ACCOUNT_NAME, CALENDARS_ACCOUNT_NAME)
                .appendQueryParameter(CalendarContract.Calendars.ACCOUNT_TYPE, CALENDARS_ACCOUNT_TYPE)
                .build();
        Uri result = context.getContentResolver().insert(calendarUri, value);
        long id = result == null ? -1 : ContentUris.parseId(result);
        return id;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean hasPermissionDismiss = false;//有权限没有通过
        if (mrequestcode==requestCode){
            for (int i=0;i<grantResults.length;i++){
                Log.v("sss", String.valueOf(grantResults[i]));
                if (grantResults[i]==-1){
                    hasPermissionDismiss=true;
                    break;
                }
            }
        }
        if (hasPermissionDismiss){//如果有没有被允许的权限
            showPermissionDialog();
        }else {
            //权限已经都通过了，可以将程序继续打开了

        }

    }

    AlertDialog mPermissionDialog;
    String mPackName = "com.example.maptest";
    private void showPermissionDialog() {
        if (mPermissionDialog == null) {
            mPermissionDialog = new AlertDialog.Builder(this)
                    .setMessage("已禁用权限，请手动授予")
                    .setPositiveButton("设置", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            cancelPermissionDialog();

                            Uri packageURI = Uri.parse("package:" + mPackName);
                            Intent intent = new Intent(Settings.
                                    ACTION_APPLICATION_DETAILS_SETTINGS, packageURI);
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //关闭页面或者做其他操作
                            cancelPermissionDialog();
                            finish();
                        }
                    })
                    .create();
        }
        mPermissionDialog.show();
    }
    private void cancelPermissionDialog() {
        mPermissionDialog.cancel();
    }

}

