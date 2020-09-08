package com.example.calcularm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.provider.AlarmClock;
import android.widget.CheckBox;
import android.widget.TimePicker;
import android.view.View;
import android.widget.Button;

import android.view.View;
import android.widget.Toast;

import java.util.Calendar;

public class Activity2_addAlarm extends AppCompatActivity {
    TimePicker picker;
    int hour, minute;
    int am_pm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity2_add_alarm);

        Button btnGet;
        picker = (TimePicker) findViewById(R.id.timePicker1);
        picker.setIs24HourView(true);
    }
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= 23) {
                    hour = picker.getHour();
                    minute = picker.getMinute();
                } else {
                    hour = picker.getCurrentHour();
                    minute = picker.getCurrentMinute();
                }
                if (hour > 12) {
                    am_pm = Calendar.PM;
                    hour = hour - 12;
                } else {
                    am_pm = Calendar.AM;
                }
                CheckBox monday = (CheckBox) findViewById(R.id.checkBox);
                CheckBox tuesday = (CheckBox) findViewById(R.id.checkBox2);
                CheckBox wednesday = (CheckBox) findViewById(R.id.checkBox3);
                CheckBox thursday = (CheckBox) findViewById(R.id.checkBox4);
                CheckBox friday = (CheckBox) findViewById(R.id.checkBox5);
                CheckBox saturday = (CheckBox) findViewById(R.id.checkBox6);
                CheckBox sunday = (CheckBox) findViewById(R.id.checkBox7);

                if((monday.isChecked()) || (tuesday.isChecked()) || (wednesday.isChecked()) || (thursday.isChecked()) || (friday.isChecked()) || (saturday.isChecked()) || (sunday.isChecked())) {

                    if (monday.isChecked()) {
                        setAlarm(hour, minute, am_pm, Calendar.MONDAY);
                    }
                    if (tuesday.isChecked()) {
                        setAlarm(hour, minute, am_pm, Calendar.TUESDAY);
                    }
                    if (wednesday.isChecked()) {
                        setAlarm(hour, minute, am_pm, Calendar.WEDNESDAY);
                    }
                    if (thursday.isChecked()) {
                        setAlarm(hour, minute, am_pm, Calendar.THURSDAY);
                    }
                    if (friday.isChecked()) {
                        setAlarm(hour, minute, am_pm, Calendar.FRIDAY);
                    }
                    if (saturday.isChecked()) {
                        setAlarm(hour, minute, am_pm, Calendar.SATURDAY);
                    }
                    if (sunday.isChecked()) {
                        setAlarm(hour, minute, am_pm, Calendar.SUNDAY);
                    }
                }
                else{
                    if(((hour*3600000)+(minute*60000)) < (System.currentTimeMillis())){
                        setAlarm(hour, minute, am_pm, (Calendar.DAY_OF_WEEK + 1));
                    }
                    else{
                        setAlarm(hour, minute, am_pm, Calendar.DAY_OF_WEEK);
                    }
                }

            }
                    public void setAlarm(int hr, int min, int ampm, int day){
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(System.currentTimeMillis());
                    calendar.set(Calendar.HOUR_OF_DAY, hr);
                    calendar.set(Calendar.MINUTE, min);
                    calendar.set(Calendar.AM_PM, ampm);
                    calendar.set(Calendar.DAY_OF_WEEK, day);
                    Intent intent = new Intent(this, MyBroadcastReceiver.class);
                    PendingIntent pendingIntent =  PendingIntent.getBroadcast(this.getApplicationContext(), 0, intent, 0 );
                    AlarmManager alarmManager= (AlarmManager)getSystemService(ALARM_SERVICE);
                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY * 7, pendingIntent);
                    Toast.makeText(this, "The alarm is set", Toast.LENGTH_LONG).show();
                    finish();
            }


}