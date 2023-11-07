package com.example.testnavigation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testnavigation.data.User
import com.example.testnavigation.data.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: UserRepository): ViewModel() {

    val getUser = repository.user

    private var inputName = MutableLiveData<String>()
    val name : LiveData<String>
        get() = inputName


    fun save(user: User){
        inputName.value = user.name.toString()
        insert(user)
    }

    fun insert( user: User) = viewModelScope.launch(Dispatchers.IO) {
        repository.addUser(user)
    }

    fun updateUser(user: User){
        inputName.value = user.name.toString()
        update(user)
    }
    fun update( user: User) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateUser(user)
    }

}