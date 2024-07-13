package com.easyhz.daypet.data.datasource.home

import com.easyhz.daypet.data.model.request.home.ThumbnailRequest
import com.easyhz.daypet.data.model.response.home.ThumbnailResponse
import com.easyhz.daypet.data.util.Collections.MONTHLY_THUMBNAILS
import com.easyhz.daypet.data.util.Fields.GROUP_ID
import com.easyhz.daypet.data.util.Fields.MONTH_DATE
import com.easyhz.daypet.data.util.collectionHandler
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query.Direction.ASCENDING
import javax.inject.Inject

class ThumbnailDataSourceImpl @Inject constructor(
    private val firestore: FirebaseFirestore
): ThumbnailDataSource {
    override suspend fun fetchMonthlyThumbnail(data: ThumbnailRequest): Result<List<ThumbnailResponse>> = collectionHandler {
        firestore.collection(MONTHLY_THUMBNAILS)
            .whereEqualTo(GROUP_ID, data.groupId)
            .whereGreaterThan(MONTH_DATE, data.startDate)
            .whereLessThan(MONTH_DATE, data.endDate)
            .orderBy(MONTH_DATE, ASCENDING)
            .get()
    }
}