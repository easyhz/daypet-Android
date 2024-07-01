package com.easyhz.daypet.domain.usecase.sign

import com.easyhz.daypet.domain.base.BaseUseCase
import com.easyhz.daypet.domain.model.sign.AuthUser
import com.easyhz.daypet.domain.repository.sign.AuthRepository
import javax.inject.Inject

class SignInWithGoogleUseCase @Inject constructor(
    private val authRepository: AuthRepository
): BaseUseCase<String, AuthUser>() {
    override suspend fun invoke(param: String): Result<AuthUser> {
        return authRepository.signInWithGoogle(param)
    }
}