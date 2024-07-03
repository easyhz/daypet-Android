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
    runCatching {
        execute().await().toObjects(T::class.java)
    }.fold(
        onSuccess = { Result.success(it) },
        onFailure = { e -> handleException(e, "collection") }
    )
}

internal suspend inline fun <reified T> documentHandler(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    crossinline execute: () -> Task<DocumentSnapshot>
): Result<T> = withContext(dispatcher) {
    runCatching {
        val result = execute().await().toObject(T::class.java)
        result ?: throw DayPetError.NoResultError
    }.fold(
        onSuccess = {  Result.success(it) },
        onFailure = { e -> handleException(e, "document") }
    )
}

internal suspend inline fun writeHandler(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    crossinline execute: () -> Task<Void>
): Result<Unit> = withContext(dispatcher) {
    runCatching {
        execute().await()
    }.fold(
        onSuccess = { Result.success(Unit) },
        onFailure = { e -> handleException(e, "write") }
    )
}

internal suspend inline fun existHandler(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    crossinline execute: () -> Task<DocumentSnapshot>
): Result<Boolean> = withContext(dispatcher) {
    runCatching {
        execute().await().exists()
    }.fold(
        onSuccess = { Result.success(it) },
        onFailure = { e -> handleException(e, "exist") }
    )
}

private fun <T> handleException(e: Throwable, tag: String): Result<T> {
    return when(e) {
        is FirebaseFirestoreException -> { handleFireStoreException(e, tag) }
        is DayPetError.NoResultError -> { handleNoResultException(e, tag) }
        else -> { handleGeneralException(e, tag) }
    }
}

private fun <T> handleFireStoreException(e: FirebaseFirestoreException, tag: String): Result<T> {
    Log.e(TAG, "In $tag handler - FireStore: ${e.message}")
    return Result.failure(getErrorByCode(e.code))
}

private fun <T> handleNoResultException(e: DayPetError.NoResultError, tag: String): Result<T> {
    Log.e(TAG, "In $tag handler - NoResult: ${e.message}")
    return Result.failure(DayPetError.NoResultError)
}

private fun <T> handleGeneralException(e: Throwable, tag: String): Result<T> {
    Log.e(TAG, "In $tag handler - Exception: ${e.message}")
    return Result.failure(DayPetError.UnexpectedError)
}