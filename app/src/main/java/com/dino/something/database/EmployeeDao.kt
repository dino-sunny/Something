package com.dino.something.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.dino.something.module.home.Employee

@Dao
interface EmployeeDao {
    @Insert
    fun insert(employeeData: EmployeeData)

    @Query("SELECT * FROM employee_table")
    fun getAllEmployee(): EmployeeData?

}