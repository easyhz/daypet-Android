package com.easyhz.daypet.domain.usecase.memory

import com.easyhz.daypet.domain.base.BaseUseCase
import com.easyhz.daypet.domain.model.memory.Memory
import com.easyhz.daypet.domain.param.memory.MemoryParam
import com.easyhz.daypet.domain.repository.memory.MemoryRepository
import javax.inject.Inject

class FetchMemoriesUseCase @Inject constructor(
    private val memoryRepository: MemoryRepository
): BaseUseCase<MemoryParam, List<Memory>>() {
    override suspend fun invoke(data: MemoryParam): Result<List<Memory>> =
        memoryRepository.fetchMemoriesOnDate(data)
}