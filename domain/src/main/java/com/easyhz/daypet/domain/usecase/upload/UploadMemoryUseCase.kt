package com.easyhz.daypet.domain.usecase.upload

import com.easyhz.daypet.domain.base.BaseUseCase
import com.easyhz.daypet.domain.param.upload.UploadMemoryParam
import com.easyhz.daypet.domain.repository.memory.MemoryRepository
import javax.inject.Inject

class UploadMemoryUseCase @Inject constructor(
    private val memoryRepository: MemoryRepository
): BaseUseCase<UploadMemoryParam, Unit>() {
    override suspend fun invoke(param: UploadMemoryParam): Result<Unit> =
        memoryRepository.uploadMemory(param)
}