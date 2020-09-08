package com.example.calcularm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class AlarmScreen extends AppCompatActivity {
    TextView AlarmScreenText = new TextView(this);
    TextView answerText = (TextView) findViewById(R.id.editTextNumber);
    int answer;
    int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_screen);

        count=0;
        Random randnum = new Random();
        int RandNum1 = randnum.nextInt(100)+1;
        String RandNum1String = String.valueOf(RandNum1);
        int RandNum2 = randnum.nextInt(100)+1;
        String RandNum2String = String.valueOf(RandNum2);
        int RandNum3 = randnum.nextInt(100)+1;
        String RandNum3String = String.valueOf(RandNum3);
        answer = RandNum1 - RandNum2 + RandNum3;

        AlarmScreenText.setText("What is" + (RandNum1String) + "-" + (RandNum2String) + "+" + (RandNum3String) + "?");

        ConstraintLayout constraintlayout = (ConstraintLayout) findViewById(R.id.ConstraintLayout);
        constraintlayout.addView(AlarmScreenText);


    }
    public void submit(View v){
        String answergiven = (String) answerText.getText();
        if (((Integer.parseInt(answergiven)) != answer) && (count==0)){
            Intent intent = new Intent(this, MyBroadcastReceiver.class);
            PendingIntent pendingIntent =  PendingIntent.getBroadcast(this.getApplicationContext(), 1, intent, 0 );
            AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 300000, pendingIntent);
            count++;
        }
        else if (((Integer.parseInt(answergiven)) == answer)){
            Intent intent = new Intent(this, MyBroadcastReceiver.class);
            PendingIntent pendingIntent =  PendingIntent.getBroadcast(this.getApplicationContext(), 1, intent, 0 );
            AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
            alarmManager.cancel(pendingIntent);
            finish();
        }


    }
    public void snooze(View v){
        Intent intent = new Intent(this, MyBroadcastReceiver.class);
        PendingIntent pendingIntent =  PendingIntent.getBroadcast(this.getApplicationContext(), 2, intent, 0 );
        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 300000, pendingIntent);
        finish();
    }

}