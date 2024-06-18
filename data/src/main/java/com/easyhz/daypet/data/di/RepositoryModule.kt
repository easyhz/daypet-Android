package com.easyhz.daypet.data.di

import com.easyhz.daypet.data.repository.memory.MemoryRepositoryImpl
import com.easyhz.daypet.domain.repository.memory.MemoryRepository
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
}