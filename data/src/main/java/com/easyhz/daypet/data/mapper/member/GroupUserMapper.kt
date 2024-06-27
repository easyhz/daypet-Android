package com.easyhz.daypet.data.mapper.member

import com.easyhz.daypet.common.extension.toLocalDate
import com.easyhz.daypet.data.model.response.member.GroupUserResponse
import com.easyhz.daypet.database.entity.member.GroupUserEntity
import com.easyhz.daypet.domain.model.member.GroupUser

fun GroupUserResponse.toModel(): GroupUser = GroupUser(
    joinDate = this.joinDate.toLocalDate(),
    name = this.name,
    groupId = this.groupId,
    thumbnailUrl = this.thumbnailUrl,
    fcmToken = this.fcmToken
)

fun GroupUserResponse.toEntity(id: Int): GroupUserEntity = GroupUserEntity(
    id = id,
    joinDate = this.joinDate.toLocalDate(),
    name = this.name,
    groupId = this.groupId,
    thumbnailUrl = this.thumbnailUrl,
    fcmToken = this.fcmToken
)