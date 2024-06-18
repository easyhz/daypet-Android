package com.easyhz.daypet.data.di

import com.easyhz.daypet.data.repository.memory.MemoryRepositoryImpl
import com.easyhz.daypet.data.repository.todo.TodoRepositoryImpl
import com.easyhz.daypet.domain.repository.memory.MemoryRepository
import com.easyhz.daypet.domain.repository.todo.TodoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindMemoryRepository(
        memoryRepositoryImpl: MemoryRepositoryImpl
    ): MemoryRepository

    @Binds
    fun bindTodoRepository(
        todoRepositoryImpl: TodoRepositoryImpl
    ): TodoRepository
}