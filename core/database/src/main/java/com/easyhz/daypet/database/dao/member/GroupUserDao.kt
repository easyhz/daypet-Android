package com.easyhz.daypet.database.dao.member

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.easyhz.daypet.database.entity.member.GroupUserEntity

@Dao
interface GroupUserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGroupUsers(users: List<GroupUserEntity>)

    @Query("DELETE FROM GROUP_USER")
    suspend fun deleteAll()
}