package com.dino.something.repository

import com.dino.something.database.EmployeeData
import com.dino.something.database.EmployeeDatabase

class EmployeeRepository(database: EmployeeDatabase) {

    val employees: EmployeeData? = database.employeeDao.getAllEmployee()
}
