package com.easyhz.daypet.data.datasource.sign

import com.easyhz.daypet.data.model.request.sign.UserInfoRequest
import com.easyhz.daypet.data.model.response.sign.UserInfoResponse
import com.google.firebase.auth.FirebaseUser

interface AuthDataSource {
    suspend fun signInWithGoogle(idToken: String): Result<FirebaseUser>
    suspend fun saveUserInfo(uid: String, userInfoRequest: UserInfoRequest): Result<Unit>
    suspend fun fetchUserInfo(uid: String): Result<UserInfoResponse>
    suspend fun hasUserInfo(uid: String): Result<Boolean>
    suspend fun updateUserGroupId(userId: String, groupId: String): Result<Unit>
    fun isLogin(): Boolean
    fun getUserId(): String?
}