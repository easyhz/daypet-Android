package com.easyhz.daypet.domain.usecase.sign

import com.easyhz.daypet.domain.base.BaseUseCase
import com.easyhz.daypet.domain.model.sign.LoginStep
import com.easyhz.daypet.domain.repository.sign.AuthRepository
import javax.inject.Inject

class CheckUserInfoUseCase @Inject constructor(
    private val authRepository: AuthRepository
): BaseUseCase<String, LoginStep>() {
    override suspend fun invoke(param: String): Result<LoginStep> =
        authRepository.checkUserStep(param)
}