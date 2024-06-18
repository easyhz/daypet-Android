package com.easyhz.daypet.data.di

import com.easyhz.daypet.data.datasource.memory.MemoryDataSource
import com.easyhz.daypet.data.datasource.memory.MemoryDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Binds
    fun bindMemoryDataSource(
        memoryDataSourceImpl: MemoryDataSourceImpl
    ): MemoryDataSource

}