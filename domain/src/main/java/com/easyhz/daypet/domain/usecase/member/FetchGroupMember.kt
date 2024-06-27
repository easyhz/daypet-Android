package com.easyhz.daypet.domain.usecase.member

import com.easyhz.daypet.domain.base.BaseUseCase
import com.easyhz.daypet.domain.model.member.GroupMember
import com.easyhz.daypet.domain.param.member.GroupMemberParam
import com.easyhz.daypet.domain.repository.member.GroupMemberRepository
import javax.inject.Inject

class FetchGroupMember @Inject constructor(
    private val groupMemberRepository: GroupMemberRepository
): BaseUseCase<GroupMemberParam, GroupMember>() {
    override suspend fun invoke(param: GroupMemberParam): Result<GroupMember> =
        groupMemberRepository.fetchGroupMember(param)
    // TODO: DB에서 받는 걸로 수정!! -> 서버에서는 처음 fetch or FCM onMessageReceived 에서 type 에 따라 fecth or 내가 가족 업데이트 했을때
}