package com.easyhz.daypet.domain.repository.sign

import com.easyhz.daypet.domain.model.sign.AuthUser

interface AuthRepository {
    suspend fun signInWithGoogle(idToken: String): Result<AuthUser>
}