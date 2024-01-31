package com.example.homeworktwenty.data.local.mapper

import com.example.homeworktwenty.data.local.entity.UserEntity
import com.example.homeworktwenty.domain.model.UserResponse


fun UserEntity.toDomain() = UserResponse(
    uid = uid,
    firstName = firstName,
    lastName = lastName,
    age = age,
    email = email
)


fun UserResponse.toData() = UserEntity(
    uid = uid,
    firstName = firstName,
    lastName = lastName,
    age = age!!,
    email = email
)