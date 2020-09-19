package com.dino.something.module.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EmployeeDetailViewModel : ViewModel(){
    private val _eventBack = MutableLiveData<Boolean>()
    val eventBack: LiveData<Boolean> get() = _eventBack

    fun onBackClick(){
        _eventBack.value = true
    }
    fun onBackClickComplete(){
        _eventBack.value = false
    }
}