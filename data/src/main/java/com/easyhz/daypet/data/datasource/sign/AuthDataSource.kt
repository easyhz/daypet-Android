package com.easyhz.daypet.data.datasource.sign

import com.easyhz.daypet.data.model.request.sign.UserInfoRequest
import com.google.firebase.auth.FirebaseUser

interface AuthDataSource {
    suspend fun signInWithGoogle(idToken: String): Result<FirebaseUser>

    suspend fun saveUserInfo(uid: String, userInfoRequest: UserInfoRequest): Result<Unit>
}