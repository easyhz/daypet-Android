package com.easyhz.daypet.data.repository.memory

import com.easyhz.daypet.data.datasource.memory.MemoryDataSource
import com.easyhz.daypet.data.mapper.memory.toEntity
import com.easyhz.daypet.data.mapper.memory.toRequest
import com.easyhz.daypet.domain.model.memory.Memory
import com.easyhz.daypet.domain.param.memory.MemoryParam
import com.easyhz.daypet.domain.repository.memory.MemoryRepository
import javax.inject.Inject

class MemoryRepositoryImpl @Inject constructor(
    private val memoryDataSource: MemoryDataSource
): MemoryRepository {
    override suspend fun fetchMemoriesOnDate(param: MemoryParam): Result<List<Memory>> =
        memoryDataSource.fetchMemoriesOnDate(param.toRequest()).map { list ->
            list.map { res -> res.toEntity() }
        }

}