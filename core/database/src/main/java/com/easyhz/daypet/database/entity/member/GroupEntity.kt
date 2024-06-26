package com.easyhz.daypet.database.entity.member

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "GROUP")
data class GroupEntity(
    @PrimaryKey @ColumnInfo("group_id") val groupId: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "owner_id") val ownerId: String
)