package com.example.homeworktwenty.di

import com.example.homeworktwenty.data.local.dao.UserDao
import com.example.homeworktwenty.data.repository.LocalUserRepositoryImpl
import com.example.homeworktwenty.domain.repository.LocalUserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideLocalUserRepository(userDao: UserDao): LocalUserRepository {

        return LocalUserRepositoryImpl(userDao = userDao)

    }
}