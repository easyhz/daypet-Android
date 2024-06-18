package com.easyhz.daypet.data.datasource.memory

import com.easyhz.daypet.data.model.request.memory.MemoryRequest
import com.easyhz.daypet.data.model.response.memory.MemoryResponse
import com.easyhz.daypet.data.util.Collections.MEMORIES
import com.easyhz.daypet.data.util.Fields.CREATION_TIME
import com.easyhz.daypet.data.util.collectionHandler
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class MemoryDataSourceImpl @Inject constructor(
    private val firestore: FirebaseFirestore
): MemoryDataSource {

    override suspend fun getMemoriesOnDate(data: MemoryRequest): Result<List<MemoryResponse>> = collectionHandler {
        firestore.collection(MEMORIES)
            .whereGreaterThan(CREATION_TIME, data.startDate)
            .whereLessThan(CREATION_TIME, data.endDate)
            .orderBy(CREATION_TIME).get()
    }

}