package com.easyhz.daypet.data.mapper.member

import com.easyhz.daypet.common.extension.toLocalDate
import com.easyhz.daypet.data.model.response.member.GroupResponse
import com.easyhz.daypet.data.model.response.member.GroupUserResponse
import com.easyhz.daypet.database.entity.member.GroupEntity
import com.easyhz.daypet.database.entity.member.PetEntity


fun GroupResponse.toEntity(groupId: String): GroupEntity = GroupEntity(
    groupId = groupId,
    name = this.name,
    ownerId = this.ownerId
)

fun GroupResponse.toPairEntity(groupId: String): Pair<GroupEntity, List<PetEntity>> {
    val groupEntity = this.toEntity(groupId)
    val petEntity = this.pets.mapIndexed { index, petResponse ->
        petResponse.toEntity(index)
    }
    return Pair(groupEntity, petEntity)
}