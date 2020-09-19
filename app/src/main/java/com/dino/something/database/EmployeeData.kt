package com.dino.something.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "employee_table")
data class EmployeeData(
    @PrimaryKey(autoGenerate = true)
    var Id: Long = 0L,
    @ColumnInfo(name ="user_name")
    var userName: String = ""
)