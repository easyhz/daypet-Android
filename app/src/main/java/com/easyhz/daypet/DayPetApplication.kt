package com.easyhz.daypet

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.easyhz.daypet.data.service.NotificationService
import com.easyhz.daypet.work.DayPetWorkManger
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DayPetApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        DayPetWorkManger.scheduleCameraCacheCleanWork(this)
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                NotificationService.CHANNEL_ID,
                NotificationService.CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH,
            )
            channel.description = "DayPet Notification Channel"
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}