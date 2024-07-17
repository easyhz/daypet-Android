package com.easyhz.daypet.domain.usecase.sign

import com.easyhz.daypet.domain.base.BaseUseCase
import com.easyhz.daypet.domain.repository.sign.AuthRepository
import javax.inject.Inject

class LogOutUseCase @Inject constructor(
    private val authRepository: AuthRepository
): BaseUseCase<Unit, Unit>() {
    override suspend fun invoke(param: Unit): Result<Unit> =
        authRepository.logOut()
}