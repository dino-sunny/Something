package com.dino.something.module.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dino.something.apiHandler.ApiService
import kotlinx.coroutines.launch

class EmployeeListViewModel : ViewModel(){

    private var employeeResponse =  MutableLiveData<List<Employee>?>()
    val response: LiveData<List<Employee>?> get() = employeeResponse

    private val _navigateToDetail = MutableLiveData<Employee>()
    val eventNavigateEmployee: LiveData<Employee> get() = _navigateToDetail

    fun onEmployeeClicked(doctor: Employee) {
        _navigateToDetail.value = doctor
    }

    fun onEmployeeNavigated() {
        _navigateToDetail.value = null
    }
    init {
        getEmployeeList()
    }

    private fun getEmployeeList() {
        viewModelScope.launch {
            val response =  ApiService.instance.getEmployeeList()
            try {
                if (response.isSuccessful){
                    employeeResponse.value = response.body()
                }
            } catch (e: Exception){
                employeeResponse.value = null
            }
        }
    }
}