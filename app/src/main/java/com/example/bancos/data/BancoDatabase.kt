package com.example.bancos.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bancos.model.Banco

@Database(entities = [Banco::class], version = 1, exportSchema = false)
abstract class BancoDatabase: RoomDatabase() {

    abstract  fun bancoDao():BancoDao
    companion object{
        @Volatile
        private var INSTANCE: BancoDatabase?=null

        fun getDatabase(context: android.content.Context):BancoDatabase{
            val tempInstance = INSTANCE
            if(tempInstance!= null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BancoDatabase::class.java,
                    "banco_database"
                ).build()
                INSTANCE=instance
                return instance
            }
        }
    }
}