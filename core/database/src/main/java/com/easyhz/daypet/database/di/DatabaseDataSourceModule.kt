package com.easyhz.daypet.database.di

import com.easyhz.daypet.database.datasource.member.GroupMemberDatabaseDataSource
import com.easyhz.daypet.database.datasource.member.GroupMemberDatabaseDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
internal abstract class DatabaseDataSourceModule {
    @Binds
    @Singleton
    abstract fun bindGroupMemberDatabaseDataSource(
        groupMemberDatabaseDataSourceImpl: GroupMemberDatabaseDataSourceImpl
    ): GroupMemberDatabaseDataSource
}