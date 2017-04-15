package com.tiantiannews.broadcastreceiver;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.tiantiannews.base.Constants;

import java.util.Calendar;


public class ActionBootCompletedBroadcastReceiver extends BroadcastReceiver {

    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;

    public static final String ACTION_NOTIFICATION = "com.tiantiannews.notification";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Constants.ACTION_BOOT_COMPLETED) || intent.getAction().equals(ACTION_NOTIFICATION)) {
            Log.d(getClass().getName(), "boot_completed");
            alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            Intent intentReceiver = new Intent(context, AlarmNotificationBroadcastReceiver.class);
            alarmIntent = PendingIntent.getBroadcast(context, 0, intentReceiver, 0);

            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            calendar.set(Calendar.HOUR_OF_DAY, 9);
            calendar.set(Calendar.MINUTE, 12);

            alarmMgr.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), alarmIntent);
        }
    }
}
