package com.easyhz.daypet.domain.usecase.upload

import com.easyhz.daypet.domain.base.BaseUseCase
import com.easyhz.daypet.domain.repository.upload.ImageRepository
import javax.inject.Inject

class GetTakePictureUriUseCase @Inject constructor(
    private val imageRepository: ImageRepository
): BaseUseCase<Unit, String>() {
    override suspend fun invoke(param: Unit): Result<String> = imageRepository.getTakePictureUri()
}