package com.example.homeworktwenty.domain.useCase

import com.example.homeworktwenty.data.common.Resource
import com.example.homeworktwenty.domain.model.UserResponse
import com.example.homeworktwenty.domain.repository.LocalUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UpdateUserUseCase @Inject constructor(private val repository: LocalUserRepository) {



    suspend operator fun invoke(user: UserResponse): Flow<Resource<Boolean>> {
        return if (!isUserResponseValid(user)){
            flow { emit(Resource.Failed("Invalid input data")) }
        }else{
            repository.updateUser(user)
        }
    }

    private fun isUserResponseValid(user: UserResponse): Boolean {
        return user.firstName.isNotBlank() && user.lastName.isNotBlank() && user.age != null &&
                isEmailValid(user.email)

    }

    private fun isEmailValid(email: String): Boolean {

        val emailRegex = Regex("^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})")
        return email.matches(emailRegex)
    }
}