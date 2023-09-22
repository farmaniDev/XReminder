package com.farmani.xreminder.notification

import android.annotation.SuppressLint
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.farmani.xreminder.R
import com.farmani.xreminder.fragment.dataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class NotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val currentId = intent!!.extras!!.getString("id")
        val notificationManager =
            context!!.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        // Use runBlocking because we have no access to lifecycleScope
        runBlocking {
            val currentReminder = context.dataStore.data.first().remindersList.find {
                it.hashCode() == currentId!!.toInt()
            }

            createNotification(
                context,
                currentId.toString(),
                currentReminder!!.title,
                currentReminder.memo,
                notificationManager
            )
        }
    }

    private fun createNotification(
        context: Context?,
        id: String,
        title: String,
        memo: String,
        notificationManager: NotificationManager
    ) {
        val notification = NotificationCompat.Builder(context!!, id)
            .setSmallIcon(R.drawable.baseline_notification_important)
            .setContentTitle(title)
            .setContentText(memo)
            .setPriority(NotificationCompat.PRIORITY_HIGH).build()

        notificationManager.notify(id.toInt(), notification)
    }
}