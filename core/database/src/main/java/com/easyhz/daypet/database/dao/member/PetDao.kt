package com.easyhz.daypet.database.dao.member

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.easyhz.daypet.database.entity.member.PetEntity

@Dao
interface PetDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPets(pets: List<PetEntity>)

    @Query("DELETE FROM PET")
    suspend fun deleteAll()
}