package com.example.homeworktwenty.presentation.mapper

import com.example.homeworktwenty.domain.model.UserResponse
import com.example.homeworktwenty.presentation.model.User

fun User.toDomain() = UserResponse(
    uid = uid,
    firstName = firstName,
    lastName = lastName,
    age = age,
    email = email
)
