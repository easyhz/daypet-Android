package com.easyhz.daypet.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.easyhz.daypet.database.converter.DateTypeConverter
import com.easyhz.daypet.database.dao.member.GroupDao
import com.easyhz.daypet.database.dao.member.GroupUserDao
import com.easyhz.daypet.database.dao.member.PetDao
import com.easyhz.daypet.database.entity.member.GroupEntity
import com.easyhz.daypet.database.entity.member.GroupUserEntity
import com.easyhz.daypet.database.entity.member.PetEntity

@Database(
    entities = [
        GroupEntity::class,
        GroupUserEntity::class,
        PetEntity::class,
    ],
    version = 1,
    exportSchema = false,
)
@TypeConverters(
    value = [
        DateTypeConverter::class,
    ],
)
abstract class DayPetDatabase : RoomDatabase() {
    abstract fun groupDao(): GroupDao
    abstract fun groupUserDao(): GroupUserDao
    abstract fun petDao(): PetDao

}