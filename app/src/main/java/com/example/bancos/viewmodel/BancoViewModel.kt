package com.example.bancos.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.bancos.data.BancoDao
import com.example.bancos.model.Banco
import com.example.bancos.repository.BancoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BancoViewModel(application: Application) : AndroidViewModel(application) {

    val getAllData:  LiveData<List<Banco>>

    private val repository: BancoRepository = BancoRepository(BancoDao())

    init {

        getAllData=repository.getAllData
    }

    fun addBanco(banco: Banco){
        viewModelScope.launch (Dispatchers.IO){repository.addBanco(banco)  }
    }

    fun updateBanco(banco: Banco){
        viewModelScope.launch (Dispatchers.IO){repository.updateBanco(banco)  }
    }

    fun deleteBanco(banco: Banco){
        viewModelScope.launch (Dispatchers.IO){repository.deleteBanco(banco)  }
    }
}