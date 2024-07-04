package com.easyhz.daypet.domain.repository.member

import com.easyhz.daypet.domain.model.member.GroupMember
import com.easyhz.daypet.domain.param.member.GroupInfoParam
import com.easyhz.daypet.domain.param.member.GroupMemberParam

interface GroupMemberRepository {
    suspend fun fetchGroupMember(param: GroupMemberParam): Result<GroupMember>
    suspend fun createGroup(param: GroupInfoParam): Result<String>
}