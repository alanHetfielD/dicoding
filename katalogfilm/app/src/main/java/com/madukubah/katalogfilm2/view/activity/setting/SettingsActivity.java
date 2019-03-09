package com.madukubah.katalogfilm2.view.activity.setting;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import com.madukubah.katalogfilm2.R;
import com.madukubah.katalogfilm2.service.AlarmReceiver;
import com.madukubah.katalogfilm2.service.SchedulerTask;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class SettingsActivity extends AppCompatActivity {
    private AlarmReceiver alarmReceiver = new AlarmReceiver();
    private SchedulerTask schedulerTask;

    public static final String KEY_HEADER_UPCOMING_REMINDER = "upcomingReminder";
    public static final String KEY_HEADER_DAILY_REMINDER = "dailyReminder";
    public static final String KEY_FIELD_UPCOMING_REMINDER = "checkedUpcoming";
    public static final String KEY_FIELD_DAILY_REMINDER = "checkedDaily";

    @BindView(R.id.daily_reminder)
    Switch dailyReminder;
    @BindView(R.id.release_Reminder)
    Switch releaseReminder;

    public SharedPreferences sReleaseReminder, sDailyReminder;
    public SharedPreferences.Editor editorReleaseReminder, editorDailyReminder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle(R.string.action_settings);
        schedulerTask = new SchedulerTask( this );
        setPreference();
    }

//    @OnClick(R.id.local_setting)
//    public void onViewClicked(View view) {
//        Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
//        startActivity(mIntent);
//    }
    @OnCheckedChanged(R.id.daily_reminder)
    public void setDailyReminder(boolean isChecked) {
        editorDailyReminder = sDailyReminder.edit();
        if (isChecked) {
            editorDailyReminder.putBoolean(KEY_FIELD_DAILY_REMINDER, true);
            editorDailyReminder.commit();
            alarmReceiver.setRepeatingAlarm(this, alarmReceiver.TYPE_REPEATING, "07:00", getString(R.string.label_alarm_daily_reminder));
        } else {
            editorDailyReminder.putBoolean(KEY_FIELD_DAILY_REMINDER, false);
            editorDailyReminder.commit();
            alarmReceiver.cancelAlarm(this, alarmReceiver.TYPE_REPEATING);
        }
        Toast.makeText(SettingsActivity.this, getString(R.string.label_daily_reminder) + " " + (isChecked ? getString(R.string.label_activated) : getString(R.string.label_deactivated)), Toast.LENGTH_SHORT).show();
    }

    @OnCheckedChanged(R.id.release_Reminder)
    public void setReleaseReminder(boolean isChecked) {
        editorReleaseReminder = sReleaseReminder.edit();
        if (isChecked) {
            editorReleaseReminder.putBoolean(KEY_FIELD_UPCOMING_REMINDER, true);
            editorReleaseReminder.commit();
            schedulerTask.createPeriodicTask();

        } else {
            editorReleaseReminder.putBoolean(KEY_FIELD_UPCOMING_REMINDER, false);
            editorReleaseReminder.commit();
            schedulerTask.cancelPeriodicTask();
        }

        Toast.makeText(SettingsActivity.this, getString(R.string.label_upcoming_reminder) + " " + (isChecked ? getString(R.string.label_activated) : getString(R.string.label_deactivated)), Toast.LENGTH_SHORT).show();
    }

    private void setPreference() {
        sReleaseReminder = getSharedPreferences(KEY_HEADER_UPCOMING_REMINDER, MODE_PRIVATE);
        sDailyReminder = getSharedPreferences(KEY_HEADER_DAILY_REMINDER, MODE_PRIVATE);
        boolean checkUpcomingReminder = sReleaseReminder.getBoolean(KEY_FIELD_UPCOMING_REMINDER, false);
        releaseReminder.setChecked(checkUpcomingReminder);
        boolean checkDailyReminder = sDailyReminder.getBoolean(KEY_FIELD_DAILY_REMINDER, false);
        dailyReminder.setChecked(checkDailyReminder);
    }

}
