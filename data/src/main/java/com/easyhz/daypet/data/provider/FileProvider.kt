package com.easyhz.daypet.data.provider

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import com.easyhz.daypet.data.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

class FileProvider: FileProvider(R.xml.file_paths) {
    companion object {
        suspend fun getTakePictureUri(context: Context): Result<Uri> =
            withContext(Dispatchers.IO) {
                try {
                    val directory = File(context.cacheDir, "images")
                    directory.mkdirs()
                    val file = File.createTempFile(
                        "upload_take_",
                        ".jpeg",
                        directory,
                    )
                    val authority = context.packageName + ".fileprovider"
                    return@withContext Result.success(getUriForFile(
                        context,
                        authority,
                        file,
                    ))
                } catch (e: Exception) {
                    return@withContext Result.failure(e)
                }
            }
    }
}