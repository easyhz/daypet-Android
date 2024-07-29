package com.easyhz.daypet.domain.repository.memory

import com.easyhz.daypet.domain.model.memory.Memory
import com.easyhz.daypet.domain.param.memory.MemoryParam
import com.easyhz.daypet.domain.param.upload.UploadMemoryParam

interface MemoryRepository {
    suspend fun fetchMemoriesOnDate(param: MemoryParam): Result<List<Memory>>
    suspend fun uploadMemory(param: UploadMemoryParam): Result<Unit>
}