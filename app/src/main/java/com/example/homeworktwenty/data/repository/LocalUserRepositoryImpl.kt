package com.example.homeworktwenty.data.repository

import com.example.homeworktwenty.data.common.Resource
import com.example.homeworktwenty.data.local.dao.UserDao
import com.example.homeworktwenty.data.local.mapper.toData
import com.example.homeworktwenty.domain.model.UserResponse
import com.example.homeworktwenty.domain.repository.LocalUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocalUserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : LocalUserRepository {
    override suspend fun getByEmail(email: String): Int {
        return userDao.getUserCountByEmail(email)
    }

    override suspend fun insertUser(user: UserResponse): Flow<Resource<Boolean>> {

        return flow {
            try {
                emit(Resource.Loading())
                if (getByEmail(user.email) == 0) {
                    userDao.insertUser(user.toData())
                    emit(Resource.Success(true))
                } else {
                    emit(Resource.Failed("User already exists!"))
                }
            } catch (e: Exception) {
                emit(Resource.Failed("Failed to add"))
            }

        }

    }

    override suspend fun delete(user: UserResponse): Flow<Resource<Boolean>> {
        return flow {
            try {
                emit(Resource.Loading())
                if (getByEmail(user.email) != 0) {
                    userDao.delete(user.firstName, user.lastName, user.age!!, user.email)
                    emit(Resource.Success(true))
                } else {
                    emit(Resource.Failed("User Does not exist"))
                }
            } catch (e: Exception) {
                emit(Resource.Failed("Failed to Delete"))
            }

        }
    }

    override suspend fun updateUser(user: UserResponse): Flow<Resource<Boolean>> {
        return flow {
            try {
                emit(Resource.Loading())
                if (getByEmail(user.email) != 0) {
                    userDao.updateByEmail(user.email, user.firstName, user.lastName, user.age!!)
                    emit(Resource.Success(true))
                } else {
                    emit(Resource.Failed("User Does not exist"))
                }
            } catch (e: Exception) {
                emit(Resource.Failed("Failed to Update"))
            }

        }
    }
}