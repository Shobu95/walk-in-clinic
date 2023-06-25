package com.shobu.walk_in_clinic.data.local.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert
    fun createUser(user: UserEntity)

    @Query("SELECT * FROM user_table")
    fun getAllUsers(): Flow<List<UserEntity>>

}