package com.easyhz.daypet.data.repository.sign

import android.net.Uri
import androidx.core.net.toUri
import com.easyhz.daypet.common.error.DayPetError
import com.easyhz.daypet.data.datasource.image.ImageDataSource
import com.easyhz.daypet.data.datasource.sign.AuthDataSource
import com.easyhz.daypet.data.mapper.sign.toModel
import com.easyhz.daypet.data.mapper.sign.toRequest
import com.easyhz.daypet.data.util.Storage
import com.easyhz.daypet.domain.model.sign.AuthUser
import com.easyhz.daypet.domain.model.sign.LoginStep
import com.easyhz.daypet.domain.model.sign.UserInfo
import com.easyhz.daypet.domain.param.sign.UserInfoParam
import com.easyhz.daypet.domain.repository.sign.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource,
    private val imageDataSource: ImageDataSource,
) : AuthRepository {
    override suspend fun signInWithGoogle(idToken: String): Result<AuthUser> =
        authDataSource.signInWithGoogle(idToken).map { it.toModel() }

    override suspend fun saveUserInfo(userInfoParam: UserInfoParam): Result<Unit> =
        runCatching {
            val thumbnailUri = userInfoParam.thumbnailUrl.toUri()
            val updatedThumbnailUrl = if (thumbnailUri != Uri.EMPTY) {
                imageDataSource.uploadImage(
                    Storage.USERS,
                    thumbnailUri,
                    userInfoParam.uid
                ).getOrThrow()
            } else ""

            val updatedUserInfo = userInfoParam.copy(thumbnailUrl = updatedThumbnailUrl)
            authDataSource.saveUserInfo(
                uid = userInfoParam.uid,
                userInfoRequest = updatedUserInfo.toRequest()
            ).getOrThrow()
        }.fold(
            onSuccess = { Result.success(it) },
            onFailure = { Result.failure(it) }
        )

    override suspend fun fetchUserInfo(uid: String): Result<UserInfo> =
        authDataSource.fetchUserInfo(uid).map { it.toModel() }

    override suspend fun checkUserStep(uid: String): Result<LoginStep> {
        return runCatching {
            authDataSource.fetchUserInfo(uid).getOrThrow()
        }.mapCatching { userInfo ->
            when {
                userInfo.groupId.isBlank() -> LoginStep.NoGroup(userInfo.name, uid)
                else -> LoginStep.ExistUser(userInfo.groupId)
            }
        }.recoverCatching { error ->
            if (error is DayPetError.NoResultError) {
                LoginStep.NewUser
            } else {
                throw error
            }
        }
    }

    override suspend fun updateUserGroupId(userId: String, groupId: String): Result<Unit> =
        authDataSource.updateUserGroupId(userId, groupId)

    override fun isLogin(): Result<Boolean> = runCatching{ authDataSource.isLogin() }

    override fun getUserId(): Result<String?> = runCatching{ authDataSource.getUserId() }
}