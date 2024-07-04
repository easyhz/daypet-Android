package com.easyhz.daypet.data.util

import android.util.Log
import com.easyhz.daypet.common.error.DayPetError
import com.easyhz.daypet.common.error.getErrorByCode
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

const val TAG = "ResponseHandler"

/**
 * collection 에서 원하는 document 리스트를 받는 함수
 *
 * @param execute [QuerySnapshot] 파이어스토어 쿼리문이 들어옴.
 */
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

/**
 * collection 에서 원하는 document 를 받는 함수
 *
 * @param execute [DocumentSnapshot] 파이어스토어 쿼리문이 들어옴.
 */
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

/**
 * collection 에 document 이름을 지정하고 저장하는 함수
 *
 * @param execute document id 를 지정한 set 쿼리문이 들어옴.
 */
internal suspend inline fun setHandler(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    crossinline execute: () -> Task<Void>
): Result<Unit> = withContext(dispatcher) {
    runCatching {
        execute().await()
    }.fold(
        onSuccess = { Result.success(Unit) },
        onFailure = { e -> handleException(e, "set") }
    )
}

/**
 * collection 에 저장하고 document id 를 받는 함수
 *
 * @param execute [DocumentReference] add 쿼리문이 들어옴.
 */
internal suspend inline fun addAndGetIdHandler(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    crossinline execute: () -> Task<DocumentReference>
): Result<String> = withContext(dispatcher) {
    runCatching {
        val result = execute().await()
        result.id
    }.fold(
        onSuccess = { Result.success(it) },
        onFailure = { e -> handleException(e, "addAndGetId") }
    )
}

/**
 * collection 에 원하는 document 존재 여부를 리턴하는 함수
 *
 * @param execute [DocumentSnapshot] get 쿼리문이 들어옴.
 */
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

internal suspend inline fun transactionHandler(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    noinline execute: () -> Task<Unit>
): Result<Unit> = withContext(dispatcher) {
    runCatching {
        execute().await()
    }.fold(
        onSuccess = { Result.success(it) },
        onFailure = { e -> handleException(e, "transaction") }
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