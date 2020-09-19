package com.dino.something.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dino.something.module.home.Employee

//
//@Database(entities = [Employee::class], version = 1,exportSchema = false)
//abstract class EmployeeDatabase : RoomDatabase() {
//
//    abstract val employeeDao: EmployeeDao
//
//    companion object{
//        @Volatile
//        private var INSTANCE: EmployeeDatabase?= null
//
//        fun getInstance(context: Context): EmployeeDatabase{
//            synchronized(this){
//                var instance = INSTANCE
//                if (instance == null){
//                    instance = Room.databaseBuilder(
//                        context.applicationContext,
//                        EmployeeDatabase::class.java,
//                        "player_database"
//                    )
//                        .fallbackToDestructiveMigration()
//                        .build()
//                    INSTANCE = instance
//                }
//                return instance
//            }
//        }
//    }
//}