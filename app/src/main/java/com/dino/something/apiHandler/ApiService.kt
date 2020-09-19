package com.dino.something.apiHandler

import com.dino.something.BuildConfig
import com.dino.something.module.home.Employee
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    /* creating a singleton object for retrofit client */
    companion object {
        val instance: ApiService by lazy {
            ApiFactory.retrofit(BuildConfig.BASEURL).create(ApiService::class.java)
        }
    }

    @GET("5d565297300000680030a986")
    suspend fun getEmployeeList(): Response<List<Employee>?>
}