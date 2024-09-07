package com.easyhz.daypet.data.service

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.app.NotificationCompat
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
        val title = messageData["title"]
        val body = messageData["body"]

        val pendingIntent = setIntent()
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
    private fun setIntent(): PendingIntent? {
        val intent = Intent().apply {
            action = Intent.ACTION_VIEW
            data = Uri.parse("daypet://notification") // TODO DEEP LINK 설정 알림 화면으로 이동
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        return PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )
    }

    companion object {
        const val CHANNEL_ID = "testId"
        const val CHANNEL_NAME = "testName"
    }
}