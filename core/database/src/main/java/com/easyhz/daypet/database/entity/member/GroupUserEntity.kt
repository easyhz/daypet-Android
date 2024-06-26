package com.easyhz.daypet.database.entity.member

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity("GROUP_USER")
data class GroupUserEntity(
    @PrimaryKey @ColumnInfo("id") val id: Int,
    @ColumnInfo("join_date") val joinDate: LocalDate,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("group_id") val groupId: String,
    @ColumnInfo("thumbnail_url") val thumbnailUrl: String,
    @ColumnInfo("fcm_token") val fcmToken: String
)
