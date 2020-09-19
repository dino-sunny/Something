package com.dino.something.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface EmployeeDao {
    @Insert
    fun insert(employeeData: EmployeeData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(employeeData: List<EmployeeData>)

    @Query("SELECT * FROM employee_table")
    fun getAllEmployee(): EmployeeData?

}