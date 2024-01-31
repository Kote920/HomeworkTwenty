package com.example.homeworktwenty.domain.repository

import com.example.homeworktwenty.data.common.Resource
import com.example.homeworktwenty.domain.model.UserResponse
import kotlinx.coroutines.flow.Flow

interface LocalUserRepository {

    suspend fun getByEmail(email: String): Int

    suspend fun insertUser( user: UserResponse): Flow<Resource<Boolean>>

    suspend fun delete(user: UserResponse): Flow<Resource<Boolean>>

    suspend fun updateUser(user: UserResponse): Flow<Resource<Boolean>>
}