package com.easyhz.daypet.data.repository.upload

import android.content.Context
import com.easyhz.daypet.data.provider.FileProvider
import com.easyhz.daypet.domain.repository.upload.MemoryImageRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class MemoryImageRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
): MemoryImageRepository {
    override suspend fun getTakePictureUri(): Result<String> = FileProvider.getTakePictureUri(context).map { it.toString() }

}