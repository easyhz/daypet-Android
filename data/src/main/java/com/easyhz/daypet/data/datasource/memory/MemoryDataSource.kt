package com.easyhz.daypet.data.datasource.memory

import com.easyhz.daypet.data.model.request.memory.MemoryRequest
import com.easyhz.daypet.data.model.response.memory.MemoryResponse

interface MemoryDataSource {
    suspend fun getMemoriesOnDate(data: MemoryRequest): Result<List<MemoryResponse>>
}