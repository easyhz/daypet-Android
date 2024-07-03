package com.easyhz.daypet.data.datasource.sign

import com.easyhz.daypet.data.model.request.sign.UserInfoRequest
import com.easyhz.daypet.data.util.Collections.USERS
import com.easyhz.daypet.data.util.writeHandler
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
    override suspend fun signInWithGoogle(idToken: String): Result<FirebaseUser> =
        try {
            val credential = GoogleAuthProvider.getCredential(idToken, null)
            val result = firebaseAuth.signInWithCredential(credential).await()
            result?.user?.let {
                Result.success(it)
            } ?: throw Exception()
        } catch (e: Exception) {
            Result.failure(e)
        }

    override suspend fun saveUserInfo(uid: String, userInfoRequest: UserInfoRequest): Result<Unit> = writeHandler {
        firestore.collection(USERS).document(uid).set(userInfoRequest)
    }
}