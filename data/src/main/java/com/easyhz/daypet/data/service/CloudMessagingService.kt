package com.easyhz.daypet.data.service

import android.content.Intent
import android.util.Log
import com.google.firebase.messaging.Constants
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CloudMessagingService: FirebaseMessagingService() {

    @Inject
    lateinit var notificationService: NotificationService

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("FCM SERVICE ", "Refreshed token: $token")
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        Log.d("FCM SERVICE ", "message data: ${message.data}")
        notificationService.showNotification(message)
    }

    override fun handleIntent(intent: Intent?) {
        val new = intent?.apply {
            val temp = extras?.apply {
                remove(Constants.MessageNotificationKeys.ENABLE_NOTIFICATION)
                remove("gcm.notification.title")
                remove("gcm.notification.e")
                remove("gcm.notification.body")
            }
            replaceExtras(temp)
        }
        super.handleIntent(new)
    }
}