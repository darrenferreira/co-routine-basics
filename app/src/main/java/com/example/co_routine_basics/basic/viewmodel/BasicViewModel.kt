package com.example.co_routine_basics.basic.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BasicViewModel : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading : LiveData<Boolean> = _loading

    private val _result = MutableLiveData<String>()
    val result : LiveData<String> = _result

    fun getMeSomeData() {
        _loading.value = true
        _result.value = "button clicked"
    }

}