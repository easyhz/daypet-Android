package com.easyhz.daypet.domain.model.member

import java.time.LocalDate

data class GroupUser(
    val joinDate: LocalDate,
    val name: String,
    val groupId: String,
    val thumbnailUrl: String,
    val fcmToken: String
)