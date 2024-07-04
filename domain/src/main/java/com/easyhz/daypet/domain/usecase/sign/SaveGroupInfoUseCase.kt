package com.easyhz.daypet.domain.usecase.sign

import com.easyhz.daypet.domain.base.BaseUseCase
import com.easyhz.daypet.domain.param.member.GroupInfoParam
import com.easyhz.daypet.domain.repository.member.GroupMemberRepository
import com.easyhz.daypet.domain.repository.sign.AuthRepository
import javax.inject.Inject

class SaveGroupInfoUseCase @Inject constructor(
    private val groupMemberRepository: GroupMemberRepository,
    private val authRepository: AuthRepository,
): BaseUseCase<GroupInfoParam, Unit>() {
    override suspend fun invoke(param: GroupInfoParam): Result<Unit> =
        runCatching {
            val groupId = groupMemberRepository.createGroup(param).getOrThrow()
            authRepository.updateUserGroupId(userId = param.ownerId, groupId = groupId).getOrThrow()
        }

}