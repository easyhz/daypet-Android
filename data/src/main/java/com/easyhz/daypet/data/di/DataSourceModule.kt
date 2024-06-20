package com.easyhz.daypet.data.di

import com.easyhz.daypet.data.datasource.home.ThumbnailDataSource
import com.easyhz.daypet.data.datasource.home.ThumbnailDataSourceImpl
import com.easyhz.daypet.data.datasource.memory.MemoryDataSource
import com.easyhz.daypet.data.datasource.memory.MemoryDataSourceImpl
import com.easyhz.daypet.data.datasource.todo.TodoDataSource
import com.easyhz.daypet.data.datasource.todo.TodoDataSourceImpl
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

    @Binds
    fun bindTodoDataSource(
        todoDataSourceImpl: TodoDataSourceImpl
    ): TodoDataSource

    @Binds
    fun bindThumbnailDataSource(
        thumbnailDataSourceImpl: ThumbnailDataSourceImpl
    ): ThumbnailDataSource
}