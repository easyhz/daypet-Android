package com.easyhz.daypet.domain.usecase.sign

import com.easyhz.daypet.domain.base.BaseUseCase
import com.easyhz.daypet.domain.param.sign.UserInfoParam
import com.easyhz.daypet.domain.repository.sign.AuthRepository
import javax.inject.Inject

class SaveUserInfoUseCase @Inject constructor(
    private val authRepository: AuthRepository
): BaseUseCase<UserInfoParam, String>() {
    override suspend fun invoke(param: UserInfoParam): Result<String> {
        return authRepository.saveUserInfo(param)
    }
}