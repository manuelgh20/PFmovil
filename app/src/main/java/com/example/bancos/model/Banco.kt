package com.example.bancos.model

import android.os.Parcelable

import kotlinx.parcelize.Parcelize

@Parcelize

data class Banco(
                   var id: String,

                   val Cedula:String,

                   val nombre:String,

                   val correo:String?,

                   val telefono:String?,

                   val cuenta:String?,

                   val codigo:String?
): Parcelable{
    constructor():
            this("","","","","","","")
}
