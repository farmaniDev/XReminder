package com.farmani.xreminder.notification

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import com.farmani.xreminder.model.Reminder
import java.util.Calendar

@RequiresApi(Build.VERSION_CODES.O)
class Notification(
    name: String,
    description: String,
    private var context: Context,
    var id: String,
    private var reminder: Reminder
) {

    init {
        createChannel(name, description)
        startNotification()
    }

    @SuppressLint("ScheduleExactAlarm")
    private fun startNotification() {
        val calendar = Calendar.getInstance()
//        calendar.set(Calendar.HOUR_OF_DAY, (reminder.time).split(":")[0].toInt())
        calendar.set(Calendar.HOUR_OF_DAY, (reminder.time).split(":")[0].toInt())
        calendar.set(Calendar.MINUTE, (reminder.time).split(":")[1].toInt())
        calendar.set(Calendar.SECOND, 0) // Change its default from 13 to 0 to be precise
        calendar.set(Calendar.MILLISECOND, 0) // Change its default from 14 to 0 to be precise
        calendar.set(Calendar.DAY_OF_YEAR, (reminder.date).split(".")[0].toInt())
        calendar.set(
            Calendar.MONTH,
            (reminder.date).split(".")[1].toInt() - 1
        ) // Month start from 0 but in reality it starts from 1
        calendar.set(Calendar.YEAR, (reminder.date).split(".")[2].toInt())
        val notificationIntent = Intent(context, NotificationReceiver::class.java)
        notificationIntent.putExtra("id", id)
        val alarmManager = context.getSystemService(ALARM_SERVICE) as AlarmManager
        alarmManager.setExact(
            AlarmManager.RTC_WAKEUP, calendar.timeInMillis, PendingIntent.getBroadcast(
                context, id.toInt(), notificationIntent, PendingIntent.FLAG_IMMUTABLE
            )
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createChannel(name: String, description: String) {
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(id, name, importance)
        channel.description = description
        val notificationManager = context.getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(channel)
    }
}