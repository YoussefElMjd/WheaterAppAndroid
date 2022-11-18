package com.example.g56172.database

import android.icu.text.SimpleDateFormat
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date
import java.sql.Timestamp
import java.time.LocalDateTime

@Entity(tableName = "UserConnection")
data class UserLogin(
    @PrimaryKey(autoGenerate = true)
    var userId: Long = 0L,
    @ColumnInfo(name = "login")
    var login: String = "",
    @ColumnInfo(name = "connectionTime")
    var date : String = LocalDateTime.now().toString(),
)
