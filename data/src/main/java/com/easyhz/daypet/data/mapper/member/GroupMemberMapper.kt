package com.easyhz.daypet.data.mapper.member

import com.easyhz.daypet.data.model.request.member.GroupRequest
import com.easyhz.daypet.data.model.request.member.GroupUserRequest
import com.easyhz.daypet.data.model.response.member.GroupResponse
import com.easyhz.daypet.data.model.response.member.GroupUserResponse
import com.easyhz.daypet.domain.model.member.GroupMember
import com.easyhz.daypet.domain.param.member.GroupMemberParam

object GroupMemberMapper {
    fun toModel(groupResponse: GroupResponse, groupUserResponse: List<GroupUserResponse>): GroupMember =
        GroupMember(
            groupName = groupResponse.name,
            ownerId = groupResponse.ownerId,
            pets = groupResponse.pets.map { it.toModel() },
            groupUsers = groupUserResponse.map { it.toModel() }
        )

    fun toGroupRequest(param: GroupMemberParam): GroupRequest =
        GroupRequest(
            groupId = param.groupId
        )

    fun toGroupUserRequest(param: GroupMemberParam): GroupUserRequest =
        GroupUserRequest(
            groupId = param.groupId
        )
}