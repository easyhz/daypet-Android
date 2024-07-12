package com.easyhz.daypet.data.repository.upload

import android.content.Context
import androidx.core.net.toUri
import com.easyhz.daypet.data.provider.FileProvider
import com.easyhz.daypet.domain.repository.upload.ImageRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
): ImageRepository {
    override suspend fun getTakePictureUri(): Result<String> = FileProvider.getTakePictureUri(context).map { it.toString() }

}