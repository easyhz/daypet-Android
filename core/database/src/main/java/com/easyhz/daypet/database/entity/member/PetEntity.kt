package com.easyhz.daypet.database.entity.member

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity("PET")
data class PetEntity(
    @PrimaryKey @ColumnInfo("id") val id: Int,
    @ColumnInfo("birth_time") val birthTime: LocalDate,
    @ColumnInfo("breed") val breed: String,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("thumbnail_url") val thumbnailUrl: String,
    @ColumnInfo("memo") val memo: String
)
