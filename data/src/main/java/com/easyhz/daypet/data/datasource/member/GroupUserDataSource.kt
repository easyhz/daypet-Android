package com.easyhz.daypet.data.datasource.member

import com.easyhz.daypet.data.model.request.member.GroupUserRequest
import com.easyhz.daypet.data.model.response.member.GroupUserResponse

interface GroupUserDataSource {
    suspend fun fetchGroupUsers(data: GroupUserRequest): Result<List<GroupUserResponse>>
}