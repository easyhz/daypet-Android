package com.easyhz.daypet.domain.usecase.memory

import com.easyhz.daypet.domain.base.BaseUseCase
import com.easyhz.daypet.domain.model.memory.MemoryDetail
import com.easyhz.daypet.domain.repository.memory.MemoryRepository
import javax.inject.Inject

class FetchMemoryUseCase @Inject constructor(
    private val memoryRepository: MemoryRepository
) : BaseUseCase<String, MemoryDetail>() {
    override suspend fun invoke(param: String): Result<MemoryDetail> =
        memoryRepository.fetchMemoryDetail(param)
}