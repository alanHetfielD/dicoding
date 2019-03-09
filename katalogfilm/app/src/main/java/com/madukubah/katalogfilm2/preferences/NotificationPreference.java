package com.madukubah.katalogfilm2.preferences;

import android.content.Context;
import android.content.SharedPreferences;

public class NotificationPreference {
    public final static String KEY_REMINDER_DAILY = "KEY_REMINDER_DAILY";
    public final static String PREF_NAME = "PREF_NAME";
    public final static String KEY_REMINDER_MESSAGE_Release = "KEY_REMINDER_MESSAGE_Release";
    public final static String KEY_REMINDER_MESSAGE_Daily = "KEY_REMINDER_MESSAGE_Daily";

    public SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public NotificationPreference(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }
    public void setReminderReleaseTime(String time){
        editor.putString(KEY_REMINDER_DAILY,time);
        editor.commit();
    }
    public void setReminderReleaseMessage (String message){
        editor.putString(KEY_REMINDER_MESSAGE_Release,message);
    }
    public void setReminderDailyTime(String time){
        editor.putString(KEY_REMINDER_DAILY,time);
        editor.commit();
    }
    public void setReminderDailyMessage(String message){
        editor.putString(KEY_REMINDER_MESSAGE_Daily,message);
    }
}