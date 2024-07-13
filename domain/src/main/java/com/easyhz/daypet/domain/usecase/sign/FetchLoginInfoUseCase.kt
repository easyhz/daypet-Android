package com.easyhz.daypet.domain.usecase.sign

import com.easyhz.daypet.domain.base.BaseUseCase
import com.easyhz.daypet.domain.model.sign.LoginStep
import com.easyhz.daypet.domain.repository.sign.AuthRepository
import javax.inject.Inject

class FetchLoginInfoUseCase @Inject constructor(
    private val authRepository: AuthRepository
): BaseUseCase<Unit, LoginStep>() {
    override suspend fun invoke(param: Unit): Result<LoginStep> = runCatching {
        authRepository.getUserId().getOrThrow()?.let {
            authRepository.checkUserStep(it).getOrThrow()
        } ?: LoginStep.NewUser
    }
}