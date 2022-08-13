package com.example.bancos.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.bancos.data.BancoDatabase
import com.example.bancos.model.Banco
import com.example.bancos.repository.BancoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BancoViewModel(application: Application) : AndroidViewModel(application) {

    val getAllData:  LiveData<List<Banco>>

    private val repository: BancoRepository

    init {
        val bancoDao= BancoDatabase.getDatabase(application).bancoDao()
        repository= BancoRepository(bancoDao)
        getAllData=repository.getAllData
    }

    fun addLugar(banco: Banco){
        viewModelScope.launch (Dispatchers.IO){repository.addBanco(banco)  }
    }

    fun updateLugar(banco: Banco){
        viewModelScope.launch (Dispatchers.IO){repository.updateBanco(banco)  }
    }

    fun deleteLugar(banco: Banco){
        viewModelScope.launch (Dispatchers.IO){repository.deleteBanco(banco)  }
    }
}