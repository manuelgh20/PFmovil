package com.example.bancos.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bancos.data.BancoDao
import com.example.bancos.model.Banco

class BancoRepository (private val bancoDao: BancoDao) {

    val getAllData: MutableLiveData<List<Banco>> = bancoDao.getBancos()

    suspend fun addBanco(banco: Banco){
        bancoDao.saveBanco(banco)
    }

    suspend fun updateBanco(banco: Banco){
        bancoDao.saveBanco(banco)
    }

    suspend fun deleteBanco(banco: Banco){
        bancoDao.deleteBanco(banco)
    }

}