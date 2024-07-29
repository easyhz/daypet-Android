package com.easyhz.daypet.domain.usecase.member

import com.easyhz.daypet.domain.base.BaseUseCase
import com.easyhz.daypet.domain.model.member.GroupMember
import com.easyhz.daypet.domain.param.member.GroupMemberParam
import com.easyhz.daypet.domain.repository.member.GroupMemberRepository
import javax.inject.Inject

class FetchGroupMemberUseCase @Inject constructor(
    private val groupMemberRepository: GroupMemberRepository
): BaseUseCase<GroupMemberParam, GroupMember>() {
    override suspend fun invoke(param: GroupMemberParam): Result<GroupMember> =
        groupMemberRepository.fetchGroupMember(param)
}