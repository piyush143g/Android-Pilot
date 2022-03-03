package com.servicefinder.local.global

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.servicefinder.local.MainActivity

class GlobalViewModelFactory: ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T? {
        when(modelClass){
            MainActivity::class.java ->{
                @Suppress("UNCHECKED_CAST")
                return MainActivity() as T
            }
            else -> {
                throw IllegalArgumentException("No ViewModel Found for this modelClass.")
            }
        }
        return null
    }
}