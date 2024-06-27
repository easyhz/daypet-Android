package com.easyhz.daypet.domain.model.member

data class GroupMember(
    val groupName: String,
    val ownerId: String,
    val pets: List<Pet>,
    val groupUsers: List<GroupUser>,
)
