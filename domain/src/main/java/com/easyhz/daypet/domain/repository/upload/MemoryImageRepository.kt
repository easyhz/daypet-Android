package com.easyhz.daypet.domain.repository.upload


interface MemoryImageRepository {
    suspend fun getTakePictureUri(): Result<String>
}