package com.example.bancos.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.bancos.model.Banco

@Dao
interface BancoDao {
    @Query("SELECT *FROM banco")
    fun getAllData(): LiveData<List<Banco>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addBanco(banco: Banco)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updateBanco(banco: Banco)

    @Delete
    suspend fun deleteBanco(banco: Banco)
}