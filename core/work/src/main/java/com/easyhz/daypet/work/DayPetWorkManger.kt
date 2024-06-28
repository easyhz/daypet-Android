package com.easyhz.daypet.work

import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.easyhz.daypet.work.cache.CameraCacheCleanUpWorker
import java.util.concurrent.TimeUnit

object DayPetWorkManger {
    fun scheduleCameraCacheCleanWork(context: Context) {
        val workRequest = PeriodicWorkRequestBuilder<CameraCacheCleanUpWorker>(
            CameraCacheCleanUpWorker.REPEAT_INTERVAL_DAYS, TimeUnit.DAYS)
            .build()
        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            CameraCacheCleanUpWorker.WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            workRequest
        )
    }
}