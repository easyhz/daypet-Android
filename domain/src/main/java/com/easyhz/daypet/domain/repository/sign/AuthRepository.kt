package com.easyhz.daypet.domain.repository.sign

import com.easyhz.daypet.domain.model.sign.AuthUser
import com.easyhz.daypet.domain.param.sign.UserInfoParam

interface AuthRepository {
    suspend fun signInWithGoogle(idToken: String): Result<AuthUser>
    suspend fun saveUserInfo(userInfoParam: UserInfoParam): Result<Unit>
}