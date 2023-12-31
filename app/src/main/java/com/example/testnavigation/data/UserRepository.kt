package com.example.testnavigation.data

import androidx.lifecycle.LiveData

class UserRepository(private val userDao: UserDao) {

    val user = userDao.readAllData()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }

    suspend fun updateUser(user: User){
        userDao.update(user)
    }
}
