package com.easyhz.daypet

import android.app.Application
import com.easyhz.daypet.work.DayPetWorkManger
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DayPetApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        DayPetWorkManger.scheduleCameraCacheCleanWork(this)
    }
}