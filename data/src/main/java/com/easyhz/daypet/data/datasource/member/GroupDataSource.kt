package com.easyhz.daypet.data.datasource.member

import com.easyhz.daypet.data.model.request.member.GroupRequest
import com.easyhz.daypet.data.model.response.member.GroupResponse

interface GroupDataSource {
    suspend fun fetchGroupInfo(data: GroupRequest): Result<GroupResponse>
}