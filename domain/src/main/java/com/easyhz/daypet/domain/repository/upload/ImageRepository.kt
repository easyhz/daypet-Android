package com.easyhz.daypet.domain.repository.upload


interface ImageRepository {
    suspend fun getTakePictureUri(): Result<String>
}