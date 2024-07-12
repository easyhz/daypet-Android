package com.easyhz.daypet.data.datasource.image

import android.net.Uri
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ImageDataSourceImpl @Inject constructor(
    private val storage: FirebaseStorage
): ImageDataSource {
    override suspend fun uploadImage(
        pathId: String,
        imageUri: Uri,
        imageName: String
    ): Result<String> = withContext(Dispatchers.IO) {
        runCatching {
            val imageRef = storage.reference.child(pathId).child(imageName)
            imageRef.putFile(imageUri).await()
            imageRef.downloadUrl.await().toString()
        }.fold(
            onSuccess = { Result.success(it) },
            onFailure = { Result.failure(it) }
        )
    }
}