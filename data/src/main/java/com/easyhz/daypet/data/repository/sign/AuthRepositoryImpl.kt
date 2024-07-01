package com.easyhz.daypet.data.repository.sign

import com.easyhz.daypet.data.datasource.sign.AuthDataSource
import com.easyhz.daypet.data.mapper.sign.toModel
import com.easyhz.daypet.domain.model.sign.AuthUser
import com.easyhz.daypet.domain.repository.sign.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource
): AuthRepository {
    override suspend fun signInWithGoogle(idToken: String): Result<AuthUser> =
        authDataSource.signInWithGoogle(idToken).map { it.toModel() }

}