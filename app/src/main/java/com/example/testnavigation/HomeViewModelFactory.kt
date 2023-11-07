package com.example.testnavigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testnavigation.data.UserRepository
import java.lang.IllegalArgumentException

class HomeViewModelFactory (private val respository: UserRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)){
            return HomeViewModel(respository) as T
        }else{
         throw IllegalArgumentException("Unknow")
        }
    }
}