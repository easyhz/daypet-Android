package com.easyhz.daypet.data.di

import com.easyhz.daypet.data.repository.home.ThumbnailRepositoryImpl
import com.easyhz.daypet.data.repository.member.GroupMemberRepositoryImpl
import com.easyhz.daypet.data.repository.memory.MemoryRepositoryImpl
import com.easyhz.daypet.data.repository.sign.AuthRepositoryImpl
import com.easyhz.daypet.data.repository.todo.TodoRepositoryImpl
import com.easyhz.daypet.data.repository.upload.MemoryImageRepositoryImpl
import com.easyhz.daypet.domain.repository.home.ThumbnailRepository
import com.easyhz.daypet.domain.repository.member.GroupMemberRepository
import com.easyhz.daypet.domain.repository.memory.MemoryRepository
import com.easyhz.daypet.domain.repository.sign.AuthRepository
import com.easyhz.daypet.domain.repository.todo.TodoRepository
import com.easyhz.daypet.domain.repository.upload.MemoryImageRepository
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

    @Binds
    fun bindThumbnailRepository(
        thumbnailRepositoryImpl: ThumbnailRepositoryImpl
    ): ThumbnailRepository

    @Binds
    fun bindMemoryImageRepository(
        memoryImageRepositoryImpl: MemoryImageRepositoryImpl
    ): MemoryImageRepository

    @Binds
    fun bindGroupMemberRepository(
        groupMemberRepositoryImpl: GroupMemberRepositoryImpl
    ): GroupMemberRepository

    @Binds
    fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository
}