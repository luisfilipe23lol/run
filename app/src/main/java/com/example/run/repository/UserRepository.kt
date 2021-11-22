package com.example.run.repository

import androidx.lifecycle.LiveData
import com.example.run.data.UserDao
import com.example.run.model.User

class UserRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readAllData()

    fun addUser(user: User){  //tirei o suspend daqui
        userDao.addUser(user)
    }

    fun updateUser(user: User){ //tirei o suspend daqui
        userDao.updateUser(user)
    }

    fun deleteUser(user:User){
        userDao.deleteUser(user)
    }

    fun deleteAllUsers(){
        userDao.deleteAllUsers()
    }
}