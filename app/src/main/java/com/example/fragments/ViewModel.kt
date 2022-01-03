package com.example.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

class ViewModel :ViewModel(){
    private var _timer = MutableLiveData(0L)
    val timer :LiveData<Long> get() = _timer
    private lateinit var counterJob :Job


    fun start(){
        val prevTiming = _timer.value ?:0
        val startTiming = System.currentTimeMillis()
        counterJob=GlobalScope.launch{
            while(true){
                delay(1000L)
                _timer.postValue(prevTiming + System.currentTimeMillis() - startTiming)
            }
        }
    }
    fun stop(){
        counterJob.cancel()
    }
}