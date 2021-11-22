package com.example.run.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.run.model.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addUser(user: User) //tirei o suspend daqui

    @Update
    fun updateUser(user: User) //tirei o suspend daqui

    @Delete
    fun deleteUser(user: User)  //tirei o suspend daqui

    @Query("DELETE FROM user_table")
    fun deleteAllUsers()

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<User>>
}