package com.example.bancos.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "banco")
data class Banco(  @PrimaryKey(autoGenerate = true)
                   val id: Int,
                   @ColumnInfo(name = "cedula")
                   val Cedula:String,
                   @ColumnInfo(name = "nombre")
                   val nombre:String,
                   @ColumnInfo(name = "Correo")
                   val correo:String?,
                   @ColumnInfo(name = "telefono")
                   val telefono:String?,
                   @ColumnInfo(name = "cuenta")
                   val cuenta:String?,
                   @ColumnInfo(name = "codigo")
                   val codigo:String?
): Parcelable
