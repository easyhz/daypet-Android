package com.easyhz.daypet.data.provider

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.core.content.FileProvider
import com.easyhz.daypet.common.constant.CacheDirConst
import com.easyhz.daypet.data.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream

class FileProvider: FileProvider(R.xml.file_paths) {
    companion object {
        suspend fun getTakePictureUri(context: Context): Result<Uri> =
            withContext(Dispatchers.IO) {
                try {
                    val directory = File(context.cacheDir, CacheDirConst.CAMERA_IMAGES)
                    directory.mkdirs()
                    val file = File.createTempFile(
                        CacheDirConst.CAMERA_IMAGE_PREFIX,
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
        suspend fun compressImageUri(context: Context, imageUri: Uri, target: Int): Result<Uri> =
            withContext(Dispatchers.IO) {
                runCatching {
                    val originalFile = uriToFile(context, imageUri)
                    val originalSize = originalFile.length()
                    val targetSize = originalSize * target / 100

                    var quality = 100
                    var compressedFile: File

                    do {
                        val bitmap = uriToBitmap(context, imageUri)

                        val compressedBitmapData = compressBitmapQuality(bitmap, quality)   // 압축

                        compressedFile = File(context.cacheDir, "compressed_${originalFile.name}")
                        val fileOutputStream = FileOutputStream(compressedFile)
                        fileOutputStream.write(compressedBitmapData)
                        fileOutputStream.flush()
                        fileOutputStream.close()

                        val compressedFileSize = compressedFile.length()

                        quality -= 5

                    } while (compressedFileSize > targetSize && quality > 0)
                    Uri.fromFile(compressedFile)
                }
            }
        private fun compressBitmapQuality(bitmap: Bitmap, quality: Int): ByteArray {
            val byteArrayOutputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, byteArrayOutputStream)
            return byteArrayOutputStream.toByteArray()
        }

        private fun uriToBitmap(context: Context, uri: Uri): Bitmap {
            return context.contentResolver.openInputStream(uri).use { inputStream ->
                BitmapFactory.decodeStream(inputStream)
            }
        }
        private fun uriToFile(context: Context, uri: Uri): File {
            val inputStream = context.contentResolver.openInputStream(uri)
            val tempFile = File(context.cacheDir, "temp_image_file")
            tempFile.outputStream().use { outputStream ->
                inputStream?.copyTo(outputStream)
            }
            return tempFile
        }

    }
}