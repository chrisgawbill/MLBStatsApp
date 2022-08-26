package com.example.mlbstatsapp.Activities

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Window
import android.view.WindowManager
import com.example.mlbstatsapp.AlarmReciever
import com.example.mlbstatsapp.LoadData
import java.util.*

class SplashScreenActivity : AppCompatActivity() {
    /**
     * Sets the splash screen to load the data and then traverse to the Main Activity
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

            val i = Intent(
                this@SplashScreenActivity,
                MainActivity::class.java
            )
            val populateDB= LoadData.getInstance(applicationContext)
            populateDB.insertTeams()
            populateDB.updateAllTeams()

            setAlarmManager()
            startActivity(i)

            finish()
    }

    /**
     * Sets the alarm to check for stat changes for 5am every day
     */
    fun setAlarmManager(){
        val calendar: Calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, 5)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
        }

        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, AlarmReciever::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )
        Log.d(SplashScreenActivity::class.java.simpleName, "Alarm Set")
    }
}