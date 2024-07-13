package com.easyhz.daypet.data.datasource.sign

import com.easyhz.daypet.data.model.request.sign.UserInfoRequest
import com.easyhz.daypet.data.model.response.sign.UserInfoResponse
import com.easyhz.daypet.data.util.Collections.USERS
import com.easyhz.daypet.data.util.Fields.GROUP_ID
import com.easyhz.daypet.data.util.documentHandler
import com.easyhz.daypet.data.util.existHandler
import com.easyhz.daypet.data.util.setHandler
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : AuthDataSource {
    override fun isLogin(): Boolean = firebaseAuth.currentUser != null
    override fun getUserId(): String? = firebaseAuth.currentUser?.uid

    override suspend fun signInWithGoogle(idToken: String): Result<FirebaseUser> =
        try {
            val credential = GoogleAuthProvider.getCredential(idToken, null)
            val result = firebaseAuth.signInWithCredential(credential).await()
            result.user?.let {
                Result.success(it)
            } ?: throw Exception()
        } catch (e: Exception) {
            Result.failure(e)
        }

    override suspend fun saveUserInfo(uid: String, userInfoRequest: UserInfoRequest): Result<Unit> = setHandler {
        firestore.collection(USERS).document(uid).set(userInfoRequest)
    }

    override suspend fun fetchUserInfo(uid: String): Result<UserInfoResponse> = documentHandler {
        firestore.collection(USERS).document(uid).get()
    }

    override suspend fun hasUserInfo(uid: String): Result<Boolean> = existHandler {
        firestore.collection(USERS).document(uid).get()
    }

    override suspend fun updateUserGroupId(userId: String, groupId: String): Result<Unit> = setHandler {
        val ref = firestore.collection(USERS).document(userId)
        firestore.runTransaction { transaction ->
            transaction.update(ref, GROUP_ID, groupId)
            null
        }
    }
}