package com.easyhz.daypet.data.mapper.member

import com.easyhz.daypet.data.model.request.member.GroupInfoRequest
import com.easyhz.daypet.data.model.response.member.GroupResponse
import com.easyhz.daypet.database.entity.member.GroupEntity
import com.easyhz.daypet.database.entity.member.PetEntity
import com.easyhz.daypet.domain.param.member.GroupInfoParam
import com.google.firebase.Timestamp


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

fun GroupInfoParam.toRequest(): GroupInfoRequest = GroupInfoRequest(
    name = this.groupName,
    ownerId = this.ownerId,
    creationTime = Timestamp.now()
)