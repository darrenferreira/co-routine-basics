package com.example.co_routine_basics.basic.viewmodel


import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.co_routine_basics.basic.repo.BasicRepo
import kotlinx.coroutines.launch

class BasicViewModel @VisibleForTesting internal constructor(private val repo: BasicRepo) : ViewModel() {

    constructor() : this(BasicRepo())

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _result = MutableLiveData<String>()
    val result: LiveData<String> = _result

    fun getMeSomeData() {
        viewModelScope.launch {
            //fetching some data therefore loader displayed
            _loading.value = true
            val data = repo.getDataFromService()
            _loading.value = false
            println(data.toString())
            _result.value = data.joinToString()
        }


    }

}