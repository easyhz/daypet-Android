package com.easyhz.daypet.data.datasource.home

import com.easyhz.daypet.data.BuildConfig
import com.easyhz.daypet.data.mapper.home.toCreateThumbnail
import com.easyhz.daypet.data.model.request.home.CreateThumbnail
import com.easyhz.daypet.data.model.request.home.SetThumbnailRequest
import com.easyhz.daypet.data.model.request.home.ThumbnailRequest
import com.easyhz.daypet.data.model.response.home.ThumbnailResponse
import com.easyhz.daypet.data.util.Collections.MONTHLY_THUMBNAILS
import com.easyhz.daypet.data.util.Fields.GROUP_ID
import com.easyhz.daypet.data.util.Fields.MONTH_DATE
import com.easyhz.daypet.data.util.Fields.THUMBNAIL_URL_DICT
import com.easyhz.daypet.data.util.collectionHandler
import com.easyhz.daypet.data.util.setHandler
import com.easyhz.daypet.data.util.transactionHandler
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query.Direction.ASCENDING
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ThumbnailDataSourceImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : ThumbnailDataSource {
    override suspend fun fetchMonthlyThumbnail(data: ThumbnailRequest): Result<List<ThumbnailResponse>> =
        collectionHandler {
            firestore.collection(MONTHLY_THUMBNAILS)
                .whereEqualTo(GROUP_ID, data.groupId)
                .whereGreaterThanOrEqualTo(MONTH_DATE, data.startDate)
                .whereLessThanOrEqualTo(MONTH_DATE, data.endDate)
                .orderBy(MONTH_DATE, ASCENDING)
                .get()
        }

    override suspend fun setThumbnail(data: SetThumbnailRequest): Result<Unit> =
        withContext(Dispatchers.IO) {
            val ref = firestore.collection(MONTHLY_THUMBNAILS)
                .whereEqualTo(GROUP_ID, data.groupId)
                .whereGreaterThanOrEqualTo(MONTH_DATE, data.startDate)
                .whereLessThanOrEqualTo(MONTH_DATE, data.endDate)
                .orderBy(MONTH_DATE, ASCENDING)
                .get()
                .await().map {
                    it.reference
                }
            if (ref.isEmpty()) {
                uploadMonthlyThumbnail(data.toCreateThumbnail())
            } else {
                transactionHandler {
                    firestore.runTransaction { transaction ->
                        val snapshot = transaction.get(ref[0])

                        val newPopulation = snapshot.get(THUMBNAIL_URL_DICT) as HashMap<String, String>
                        if (newPopulation[data.day] == null || newPopulation[data.day] == BuildConfig.EMPTY_URL) {
                            newPopulation[data.day] = data.url
                        }
                        transaction.update(ref[0], THUMBNAIL_URL_DICT, newPopulation)
                        null
                    }
                }
            }
        }

    private suspend fun uploadMonthlyThumbnail(data: CreateThumbnail) = setHandler {
        firestore.collection(MONTHLY_THUMBNAILS).document().set(data)
    }
}