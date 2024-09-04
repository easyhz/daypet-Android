package com.easyhz.daypet.data.service

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.net.toUri
import com.easyhz.daypet.data.R
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class NotificationService
@Inject constructor(
    @ApplicationContext private val context: Context,
){
    private val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    fun showNotification(message: RemoteMessage) {
        val messageData = message.data
        println(">>> $messageData")
        val title = message.notification?.title ?: return
        val body = message.notification?.body ?: return
////        val announcementId = messageData["announcementId"]?.toIntOrNull()
////        val organizationId = messageData["organizationId"]?.toIntOrNull()
        val id = message.notification?.clickAction
        println("id>> $id")
        val pendingIntent = setIntent(id = messageData["id"]?.toIntOrNull(), id)
        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(androidx.core.R.drawable.notification_bg)
            .setContentTitle(title)
            .setContentText(body)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        notificationManager.notify(0, notification)
    }

    private fun setIntent(id: Int?, clickAction: String?): PendingIntent? {
        if (id == null || clickAction == null) return PendingIntent.getActivity(context, 0, Intent(), PendingIntent.FLAG_IMMUTABLE)
        val intent = Intent().apply {
            action = Intent.ACTION_VIEW
            data = clickAction.toUri()
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        return PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
    }

    companion object {
        val CHANNEL_ID = "testId"
        val CHANNEL_NAME = "testName"
    }
}