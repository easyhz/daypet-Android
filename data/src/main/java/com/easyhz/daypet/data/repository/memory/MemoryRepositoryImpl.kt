package com.easyhz.daypet.data.repository.memory

import android.content.Context
import android.net.Uri
import androidx.core.net.toUri
import com.easyhz.daypet.data.BuildConfig
import com.easyhz.daypet.data.datasource.home.ThumbnailDataSource
import com.easyhz.daypet.data.datasource.image.ImageDataSource
import com.easyhz.daypet.data.datasource.memory.MemoryDataSource
import com.easyhz.daypet.data.di.IoDispatcher
import com.easyhz.daypet.data.mapper.memory.toModel
import com.easyhz.daypet.data.mapper.memory.toRequest
import com.easyhz.daypet.data.mapper.memory.toSetThumbnail
import com.easyhz.daypet.data.provider.FileProvider
import com.easyhz.daypet.data.util.Storage
import com.easyhz.daypet.domain.model.memory.Memory
import com.easyhz.daypet.domain.param.memory.MemoryParam
import com.easyhz.daypet.domain.param.upload.UploadMemoryParam
import com.easyhz.daypet.domain.repository.memory.MemoryRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MemoryRepositoryImpl @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    @ApplicationContext private val context: Context,
    private val memoryDataSource: MemoryDataSource,
    private val imageDataSource: ImageDataSource,
    private val thumbnailDataSource: ThumbnailDataSource,
): MemoryRepository {
    override suspend fun fetchMemoriesOnDate(param: MemoryParam): Result<List<Memory>> =
        memoryDataSource.fetchMemoriesOnDate(param.toRequest()).map { list ->
            list.map { res -> res.toModel() }
        }

    override suspend fun uploadMemory(param: UploadMemoryParam): Result<Unit> = withContext(dispatcher) {
        runCatching {
            val documentId = memoryDataSource.getUploadMemoryDocumentId().getOrThrow().split("/")[1]
            val imageListDeferred = async {
                param.images.mapIndexed { index, image ->
                    image.toUri().takeIf { it != Uri.EMPTY }?.let { imageUri ->
                        imageDataSource.uploadImage("${Storage.MEMORIES}/$documentId/", imageUri, "$index").getOrThrow()
                    } ?: image
                }
            }
            val imageList = imageListDeferred.await()
            val thumbnail = if (param.images.isEmpty()) {
                BuildConfig.EMPTY_URL
            } else {
                async {
                    val thumbnail = FileProvider.compressImageUri(context, param.thumbnailUrl.toUri(), 50).getOrThrow()
                    imageDataSource.uploadImage("${Storage.MEMORIES}/$documentId/", thumbnail, "thumbnail").getOrThrow()
                }.await()
            }
            thumbnailDataSource.setThumbnail(param.toSetThumbnail(thumbnail)).getOrThrow()
            memoryDataSource.uploadMemory(documentId, param.toRequest(imageList, thumbnail)).getOrThrow()
        }.fold(
            onSuccess = { Result.success(it) },
            onFailure = { Result.failure(it) }
        )
    }
}