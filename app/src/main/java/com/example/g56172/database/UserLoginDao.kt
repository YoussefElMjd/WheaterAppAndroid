package com.example.g56172.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserLoginDao {

    @Insert
    fun insert(user: UserLogin)


    @Delete
    fun delete(user: UserLogin)


    @Update
    fun update(user: UserLogin)


    @Query("DELETE FROM UserConnection")
    fun deleteAll()


    @Query("SELECT * FROM UserConnection")
    fun getAll() : List<UserLogin>?

    @Query("SELECT * FROM UserConnection where login = :email")
    fun get(email: String) : UserLogin?


}