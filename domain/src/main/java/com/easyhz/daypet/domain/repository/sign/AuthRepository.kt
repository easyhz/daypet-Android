package com.easyhz.daypet.domain.repository.sign

import com.easyhz.daypet.domain.model.sign.AuthUser
import com.easyhz.daypet.domain.model.sign.LoginStep
import com.easyhz.daypet.domain.model.sign.UserInfo
import com.easyhz.daypet.domain.param.sign.UserInfoParam

interface AuthRepository {
    suspend fun signInWithGoogle(idToken: String): Result<AuthUser>
    suspend fun saveUserInfo(userInfoParam: UserInfoParam): Result<Unit>
    suspend fun fetchUserInfo(uid: String): Result<UserInfo>
    suspend fun checkUserStep(uid: String): Result<LoginStep>
    suspend fun updateUserGroupId(userId: String, groupId: String): Result<Unit>
}