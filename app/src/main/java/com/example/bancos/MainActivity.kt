package com.example.bancos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.bancos.databinding.ActivityMainBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    companion object {
        private const val RC_SIGN_IN = 9001
    }
    //cliente de google
    private lateinit var googleSignInClient: GoogleSignInClient

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        FirebaseApp.initializeApp(this)
        auth = Firebase.auth

        binding.btLogin.setOnClickListener{haceLogin()}
        binding.btRegister.setOnClickListener{haceRegister()}

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.id_web))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this,gso)
        binding.btGoogle.setOnClickListener { googleSignIn() }

    }

    private fun googleSignIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    private fun firebaseAuthWithGoogle (idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken,null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    actualiza(user)
                } else {
                    actualiza(null)
                }
            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode== RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val cuenta = task.getResult(ApiException::class.java)!!
                firebaseAuthWithGoogle(cuenta.idToken!!)
            } catch (e: ApiException) {

            }
        }

    }

    private fun haceRegister() {
        val email=binding.etEmail.text.toString()
        val clave=binding.etClave.text.toString()

        //se hace registro
        auth.createUserWithEmailAndPassword(email,clave)
            .addOnCompleteListener(this){task ->
                if(task.isSuccessful){
                    Log.d("Autenticando","creado")
                    val user=auth.currentUser
                    actualiza(user)
                }else{
                    Log.d("Autenticando","fregistroaail")
                    Toast.makeText(baseContext,"fallo",Toast.LENGTH_LONG).show()
                    actualiza(null)

                }}
    }

    private fun actualiza(user: FirebaseUser?) {
        if(user!=null){
            val intent= Intent(this,Principal::class.java)
            startActivity(intent)

        }
    }

    public override fun onStart(){
        super.onStart()
        val usuario= auth.currentUser
        actualiza(usuario)


    }

    private fun haceLogin() {
        val email=binding.etEmail.text.toString()
        val clave=binding.etClave.text.toString()

        //se hace registro
        auth.signInWithEmailAndPassword(email,clave)
            .addOnCompleteListener(this){task ->
                if(task.isSuccessful){
                    Log.d("Autenticando","login")
                    val user=auth.currentUser
                    actualiza(user)
                }else {
                    Log.d("Autenticando", "faail")
                    val user = auth.currentUser
                    Toast.makeText(baseContext,"fallo",Toast.LENGTH_LONG).show()
                    actualiza(null)

                }}
    }
}