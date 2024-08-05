package com.easyhz.daypet.data.datasource.memory

import com.easyhz.daypet.data.model.request.memory.MemoryRequest
import com.easyhz.daypet.data.model.request.memory.UploadMemoryRequest
import com.easyhz.daypet.data.model.response.memory.MemoryResponse
import com.easyhz.daypet.data.util.DocumentWithId

interface MemoryDataSource {
    suspend fun fetchMemoriesOnDate(data: MemoryRequest): Result<List<DocumentWithId<MemoryResponse>>>
    suspend fun getUploadMemoryDocumentId(): Result<String>
    suspend fun uploadMemory(pathId: String, data: UploadMemoryRequest): Result<Unit>
    suspend fun fetchMemoryDetail(id: String): Result<MemoryResponse>
}