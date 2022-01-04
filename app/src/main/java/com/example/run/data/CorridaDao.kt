package com.example.run.data

import androidx.lifecycle.LiveData
import androidx.room.*

import com.example.run.model.Corrida


@Dao
interface CorridaDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addCorrida(corrida: Corrida) //tirei o suspend daqui

    @Update
    fun updateCorrida(corrida: Corrida) //tirei o suspend daqui

    @Delete
    fun deleteCorrida(corrida: Corrida)  //tirei o suspend daqui


    @Query("SELECT * FROM corrida_table ORDER BY id DESC")
    fun readAllData(): LiveData<List<Corrida>>

}

