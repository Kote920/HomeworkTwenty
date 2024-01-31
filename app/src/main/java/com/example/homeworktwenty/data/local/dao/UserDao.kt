package com.example.homeworktwenty.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.homeworktwenty.data.common.Resource
import com.example.homeworktwenty.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {


    @Query("SELECT COUNT(*) FROM userentity WHERE email = :userEmail")
    suspend fun getUserCountByEmail(userEmail: String): Int


    @Insert
    suspend fun insertUser( user: UserEntity)

    @Query("DELETE FROM userentity WHERE first_Name = :firstName AND last_Name = :lastName AND age = :age AND email = :email")
    suspend fun delete(firstName: String, lastName: String, age: Int, email: String)


    @Query("UPDATE userentity SET first_Name = :newFirstName, last_Name = :newLastName, age = :newAge WHERE email = :email")
    suspend fun updateByEmail(email: String, newFirstName: String, newLastName: String, newAge: Int)
}