package com.example.bancos.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import com.example.bancos.model.Banco
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.ktx.Firebase


class BancoDao {

    private val coleccion1="bancosAPP"
    private val coleccion2="misBancos"
    private var codigoUsuario:String
    private var firestore: FirebaseFirestore

    init{
        val usuario= Firebase.auth.currentUser?.email
        codigoUsuario="$usuario"
        firestore= FirebaseFirestore.getInstance()
        firestore.firestoreSettings= FirebaseFirestoreSettings.Builder().build()

    }

    //obtiene toda la lista de documentos de Banco
    fun getBancos(): MutableLiveData<List<Banco>> {
        val listabancos = MutableLiveData<List<Banco>>()

        //obtener los documentos
        firestore
            .collection(coleccion1)
            .document(codigoUsuario)
            .collection(coleccion2)
            .addSnapshotListener{snapshot, e->
                if (e !=null){ //si se da alguna exepcion a tomar insttannea
                    return@addSnapshotListener
                }
                if(snapshot!=null){//recupere los documentos nft de la instantanea
                    val lista = ArrayList<Banco>()
                    val bancos=snapshot.documents //recupera la lista de dpcumentos
                    bancos.forEach{
                        val banco =it.toObject(Banco::class.java) //transforma de documento a un objeto nft
                        if(banco!=null){
                            lista.add(banco)//si lo transforma se pasa a la lista
                        }
                    }
                    listabancos.value=lista

                }
            }
        return listabancos
    }


    suspend fun saveBanco(banco: Banco){
        val document: DocumentReference//actualiza o crear como nuevo
        if(banco.id.isEmpty()){//si no esta definido es un docuemnto nuevo
            document=firestore.collection(coleccion1).document(codigoUsuario).collection(coleccion2).document()
            banco.id=document.id

        }else{//si hay id entonces actualiza el nft
            document=firestore.collection(coleccion1).document(codigoUsuario).collection(coleccion2).document(banco.id)
        }
        val set=document.set(banco)
        set.addOnSuccessListener { Log.d("savebanco","banco agregado /actualizado") }
            .addOnCanceledListener { Log.d("savebanco","ERROR :banco NO agregado /actualizado") }
    }





    suspend fun deleteBanco(banco: Banco){
        if(banco.id.isNotEmpty()){
            firestore
                .collection(coleccion1)
                .document(codigoUsuario)
                .collection(coleccion2)
                .document(banco.id)
                .delete()
                .addOnSuccessListener { Log.d("delete banco","banco eliminado") }
                .addOnCanceledListener { Log.d("delete banco","banco  no eliminado") }
        }
    }
}