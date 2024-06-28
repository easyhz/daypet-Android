package com.easyhz.daypet.work.cache

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.easyhz.daypet.common.constant.CacheDirConst
import java.io.File

/**
 * Camera 로 찍었을 때 캐싱되는 이미지를 일정 주기마다 없애는 worker
 */
class CameraCacheCleanUpWorker(context: Context, params: WorkerParameters) : Worker(context, params) {
    companion object {
        const val WORK_NAME = "CameraCacheCleanUpWork"
        const val REPEAT_INTERVAL_DAYS = 30L
        const val AGE_LIMIT = REPEAT_INTERVAL_DAYS * 24 * 60 * 60 * 1000L
        const val TEST_LIMIT =  60 * 1000L
    }

    override fun doWork(): Result {
        deleteOldCacheFiles(applicationContext)
        return Result.success()
    }

    private fun deleteOldCacheFiles(context: Context) {
        val cacheDir = context.cacheDir
        val imagesDir = File(cacheDir, CacheDirConst.CAMERA_IMAGES)
        val currentTime = System.currentTimeMillis()
        if (cacheDir.isDirectory) {
            imagesDir.listFiles()?.forEach { file ->
                if (file.name.startsWith(CacheDirConst.CAMERA_IMAGE_PREFIX) && currentTime - file.lastModified() > AGE_LIMIT) {
                    file.deleteRecursively()
                }
            }
        }
    }
}