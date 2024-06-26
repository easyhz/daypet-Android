package com.easyhz.daypet.database.datasource.member

import com.easyhz.daypet.database.entity.member.GroupEntity
import com.easyhz.daypet.database.entity.member.GroupUserEntity
import com.easyhz.daypet.database.entity.member.PetEntity

interface GroupMemberDatabaseDataSource {

    suspend fun getGroupMembers()

    suspend fun updateGroupMembers(
        group: GroupEntity,
        groupUsers: List<GroupUserEntity>,
        pets: List<PetEntity>
    )
}