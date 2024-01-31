package com.example.homeworktwenty.domain.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class UserResponse(
    val uid: Int = 0,
    val firstName: String,
    val lastName: String,
    val age: Int?,
    val email: String
)
