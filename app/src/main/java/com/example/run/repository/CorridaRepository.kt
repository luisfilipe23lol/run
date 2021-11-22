package com.example.run.repository

import androidx.lifecycle.LiveData
import com.example.run.data.CorridaDao
import com.example.run.model.Corrida

class CorridaRepository(private val corridaDao: CorridaDao) {

    val readAllData: LiveData<List<Corrida>> = corridaDao.readAllData()

    fun addCorrida(corrida: Corrida){  //tirei o suspend daqui
        corridaDao.addCorrida(corrida)
    }

    fun updateCorrida(corrida: Corrida){ //tirei o suspend daqui
        corridaDao.updateCorrida(corrida)
    }

    fun deleteCorrida(corrida:Corrida){
        corridaDao.deleteCorrida(corrida)
    }


}

