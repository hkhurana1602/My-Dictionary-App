package com.example.dictionaryapp.di

import com.example.dictionaryapp.data.repository.DictionaryRepositoryImplementation
import com.example.dictionaryapp.domain.repository.DictionaryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindDictionaryRepository(
        dictionaryRepository: DictionaryRepositoryImplementation
    ): DictionaryRepository
}