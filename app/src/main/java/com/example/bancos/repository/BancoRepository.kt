package com.example.bancos.repository

import androidx.lifecycle.LiveData
import com.example.bancos.data.BancoDao
import com.example.bancos.model.Banco

class BancoRepository (private val bancoDao: BancoDao) {

    val getAllData: LiveData<List<Banco>> = bancoDao.getAllData()

    suspend fun addBanco(banco: Banco){
        bancoDao.addBanco(banco)
    }

    suspend fun updateBanco(banco: Banco){
        bancoDao.updateBanco(banco)
    }

    suspend fun deleteBanco(banco: Banco){
        bancoDao.deleteBanco(banco)
    }

}