package com.example.homeworktwenty.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homeworktwenty.data.common.Resource
import com.example.homeworktwenty.domain.model.UserResponse
import com.example.homeworktwenty.domain.useCase.DeleteUserUseCase
import com.example.homeworktwenty.domain.useCase.InsertUserUseCase
import com.example.homeworktwenty.domain.useCase.UpdateUserUseCase
import com.example.homeworktwenty.presentation.mapper.toDomain
import com.example.homeworktwenty.presentation.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val insertUserUseCase: InsertUserUseCase,
    private val deleteUserUseCase: DeleteUserUseCase,
    private val updateUserUseCase: UpdateUserUseCase
) : ViewModel() {


    private val _insertUserFlow = MutableSharedFlow<Resource<Boolean>>()
    val insertUserFlow: SharedFlow<Resource<Boolean>> = _insertUserFlow.asSharedFlow()

    private val _deleteUserFlow = MutableSharedFlow<Resource<Boolean>>()
    val deleteUserFlow: SharedFlow<Resource<Boolean>> = _deleteUserFlow.asSharedFlow()

    private val _updateUserFlow = MutableSharedFlow<Resource<Boolean>>()
    val updateUserFlow: SharedFlow<Resource<Boolean>> = _updateUserFlow.asSharedFlow()


    fun insertUser(firstName: String, lastName: String, age: Int?, email: String) {
        viewModelScope.launch {
            insertUserUseCase.invoke(
                UserResponse(
                    firstName = firstName,
                    lastName = lastName,
                    age = age,
                    email = email
                )
            ).collect {
                when (it) {
                    is Resource.Loading -> _insertUserFlow.emit(Resource.Loading())
                    is Resource.Success -> _insertUserFlow.emit(Resource.Success(it.responseData))
                    is Resource.Failed -> _insertUserFlow.emit(Resource.Failed(it.message))
                }
            }
        }
    }

    fun deleteUser(firstName: String, lastName: String, age: Int?, email: String) {
        viewModelScope.launch {
            deleteUserUseCase.invoke(
                UserResponse(
                    firstName = firstName,
                    lastName = lastName,
                    age = age,
                    email = email
                )
            ).collect {
                when (it) {
                    is Resource.Loading -> _deleteUserFlow.emit(Resource.Loading())
                    is Resource.Success -> _deleteUserFlow.emit(Resource.Success(it.responseData))
                    is Resource.Failed -> _deleteUserFlow.emit(Resource.Failed(it.message))
                }
            }
        }
    }
    fun updateUser(newFirstName: String, newLastName: String, newAge: Int?, email: String) {
        viewModelScope.launch {
            updateUserUseCase.invoke(
                UserResponse(
                    firstName = newFirstName,
                    lastName = newLastName,
                    age = newAge,
                    email = email
                )
            )
                .collect {
                when (it) {
                    is Resource.Loading -> _updateUserFlow.emit(Resource.Loading())
                    is Resource.Success -> _updateUserFlow.emit(Resource.Success(it.responseData))
                    is Resource.Failed -> _updateUserFlow.emit(Resource.Failed(it.message))
                }
            }
        }
    }


}