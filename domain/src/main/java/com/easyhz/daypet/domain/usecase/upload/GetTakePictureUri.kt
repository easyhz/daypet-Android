package com.easyhz.daypet.domain.usecase.upload

import com.easyhz.daypet.domain.base.BaseUseCase
import com.easyhz.daypet.domain.repository.upload.MemoryImageRepository
import javax.inject.Inject

class GetTakePictureUri @Inject constructor(
    private val memoryImageRepository: MemoryImageRepository
): BaseUseCase<Unit, String>() {
    override suspend fun invoke(param: Unit): Result<String> = memoryImageRepository.getTakePictureUri()
}