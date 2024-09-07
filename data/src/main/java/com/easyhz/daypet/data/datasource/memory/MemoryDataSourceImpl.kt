package com.easyhz.daypet.data.datasource.memory

import com.easyhz.daypet.data.model.request.memory.MemoryRequest
import com.easyhz.daypet.data.model.request.memory.UploadMemoryRequest
import com.easyhz.daypet.data.model.response.memory.MemoryResponse
import com.easyhz.daypet.data.util.Collections.MEMORIES
import com.easyhz.daypet.data.util.DocumentWithId
import com.easyhz.daypet.data.util.Fields.GROUP_ID
import com.easyhz.daypet.data.util.Fields.MEMORY_DATE
import com.easyhz.daypet.data.util.collectionWithIdHandler
import com.easyhz.daypet.data.util.documentHandler
import com.easyhz.daypet.data.util.setHandler
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query.Direction.ASCENDING
import javax.inject.Inject

class MemoryDataSourceImpl @Inject constructor(
    private val firestore: FirebaseFirestore
): MemoryDataSource {

    override suspend fun fetchMemoriesOnDate(data: MemoryRequest): Result<List<DocumentWithId<MemoryResponse>>> = collectionWithIdHandler {
        firestore.collection(MEMORIES)
            .whereEqualTo(GROUP_ID, data.groupId)
            .whereGreaterThanOrEqualTo(MEMORY_DATE, data.startDate)
            .whereLessThanOrEqualTo(MEMORY_DATE, data.endDate)
            .orderBy(MEMORY_DATE, ASCENDING)
            .get()
    }

    override suspend fun getUploadMemoryDocumentId(): Result<String> = runCatching {
        firestore.collection(MEMORIES).document().path
    }

    override suspend fun uploadMemory(pathId: String, data: UploadMemoryRequest): Result<Unit> = setHandler {
        firestore.collection(MEMORIES).document(pathId).set(data)
    }

    override suspend fun fetchMemoryDetail(id: String): Result<MemoryResponse> = documentHandler {
        firestore.collection(MEMORIES)
            .document(id)
            .get()
    }
}