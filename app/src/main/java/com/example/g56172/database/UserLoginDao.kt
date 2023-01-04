package com.example.g56172.database

import androidx.room.*

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
    fun getAll(): List<UserLogin>?

    @Query("SELECT * FROM UserConnection where login = :email")
    fun get(email: String): UserLogin?

    @Query("SELECT login from UserConnection")
    fun getEmail(): List<String>

    @Query("UPDATE UserConnection  set connectionTime = :date where login = :email")
    fun updateDateConnection(email: String, date: String)

}