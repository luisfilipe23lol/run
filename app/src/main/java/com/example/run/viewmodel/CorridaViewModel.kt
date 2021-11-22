package com.example.run.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.run.data.UserDatabase
import com.example.run.model.Corrida
import com.example.run.repository.CorridaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CorridaViewModel(application: Application): AndroidViewModel(application){

    var readAllData: LiveData<List<Corrida>>
    private val repository: CorridaRepository

    init {
        val corridaDao = UserDatabase.getDatabase(application).corridaDao()
        repository = CorridaRepository(corridaDao)
        readAllData = repository.readAllData


    }

    fun addCorrida(corrida: Corrida){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addCorrida(corrida)
        }
    }

    fun updateCorrida(corrida: Corrida){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateCorrida(corrida)
        }

    }

    fun deleteCorrida(corrida: Corrida){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteCorrida(corrida)
        }
    }


}

