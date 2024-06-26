package com.easyhz.daypet.database.di

import android.content.Context
import androidx.room.Room
import com.easyhz.daypet.database.DayPetDatabase
import com.easyhz.daypet.database.converter.DateTypeConverter
import com.easyhz.daypet.database.dao.member.GroupDao
import com.easyhz.daypet.database.dao.member.GroupUserDao
import com.easyhz.daypet.database.dao.member.PetDao
import com.easyhz.daypet.database.util.addTypeConverters
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Provides
    @Singleton
    fun provideDmsDataBase(
        @ApplicationContext context: Context,
    ): DayPetDatabase = Room.databaseBuilder(
        context = context,
        klass = DayPetDatabase::class.java,
        name = "daypet.db",
    ).addTypeConverters(
        DateTypeConverter(),
    ).build()

    @Provides
    @Singleton
    fun provideGroupDao(db: DayPetDatabase): GroupDao = db.groupDao()

    @Provides
    @Singleton
    fun provideGroupUserDao(db: DayPetDatabase): GroupUserDao = db.groupUserDao()

    @Provides
    @Singleton
    fun providePetDao(db: DayPetDatabase): PetDao = db.petDao()

}