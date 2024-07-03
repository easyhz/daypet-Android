package com.easyhz.daypet.data.util

import android.util.Log
import com.easyhz.daypet.common.error.DayPetError
import com.easyhz.daypet.common.error.getErrorByCode
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

const val TAG = "ResponseHandler"

internal suspend inline fun < reified T: Any> collectionHandler(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    crossinline execute: () -> Task<QuerySnapshot>
): Result<List<T>> = withContext(dispatcher) {
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

internal suspend inline fun <reified T> documentHandler(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    crossinline execute: () -> Task<DocumentSnapshot>
): Result<T> = withContext(dispatcher) {
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

internal suspend inline fun writeHandler(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    crossinline execute: () -> Task<Void>
): Result<Unit> = withContext(dispatcher) {
    try {
        val result = execute().await()
        Result.success(Unit)
    }catch (e: FirebaseFirestoreException) {
        Log.e(TAG, "In handler - FireStore: ${e.message}")
        Result.failure(getErrorByCode(e.code))
    } catch (e: Exception) {
        Log.e(TAG, "In handler - Exception: ${e.message}")
        Result.failure(DayPetError.UnexpectedError)
    }

}