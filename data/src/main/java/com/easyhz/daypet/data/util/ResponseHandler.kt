package com.easyhz.daypet.data.util

import android.util.Log
import com.easyhz.daypet.common.error.DayPetError
import com.easyhz.daypet.common.error.getErrorByCode
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

const val TAG = "ResponseHandler"

suspend inline fun < reified T: Any> collectionHandler(
    crossinline execute: () -> Task<QuerySnapshot>
): Result<List<T>> = withContext(Dispatchers.IO) {
    try {
        val result = execute().await().toObjects(T::class.java)
        Result.success(result)
    } catch (e: FirebaseFirestoreException) {
        Log.e(TAG, "In handler - FireStore: ${e.message}")
        Result.failure(getErrorByCode(e.code))
    } catch (e: Exception) {
        Log.e(TAG, "In handler - Exception: ${e.message}")
        Result.failure(DayPetError.UnexpectedError)
    }
}

suspend inline fun <reified T> documentHandler(
    crossinline execute: () -> Task<DocumentSnapshot>
): Result<T> = withContext(Dispatchers.IO) {
    try {
        val result = execute().await().toObject(T::class.java)
        result?.let {
            Result.success(result)
        } ?: run {
            Result.failure(DayPetError.NoResultError)
        }
    } catch (e: FirebaseFirestoreException) {
        Log.e(TAG, "In handler - FireStore: ${e.message}")
        Result.failure(getErrorByCode(e.code))
    } catch (e: Exception) {
        Log.e(TAG, "In handler - Exception: ${e.message}")
        Result.failure(DayPetError.UnexpectedError)
    }
}