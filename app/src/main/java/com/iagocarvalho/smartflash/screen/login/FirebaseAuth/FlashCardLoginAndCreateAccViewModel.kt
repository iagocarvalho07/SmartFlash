package com.iagocarvalho.smartflash.screen.login.FirebaseAuth

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await


class FlashCardLoginAndCreateAccViewModel : ViewModel() {
    private var auth: FirebaseAuth = Firebase.auth
    private var currentUser = Firebase.auth.currentUser

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading


    fun LoginVerific() {
        val currentUser = auth.currentUser
        if (currentUser != null) {
            reload()
        }
    }

    fun singInWithEmailAndPassword(
        email: String,
        passaword: String,
        home: () -> Unit,
        errors: (Exception?) -> Unit
    ) {
        if (_loading.value == false) {
            _loading.value = true
            auth.signInWithEmailAndPassword(email, passaword)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("FB", "singInWithEmailAndPassword: ${task.result.toString()}")
                        val displayName = task.result.user?.email?.split('@')?.get(0)
                        home()
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.d("fb", "singInWithEmailAndPassword: ${task.exception}")
                        errors(task.exception)
                    }
                    _loading.value = false
                }
        }
    }


    fun createUserWithEmailAndPassword(
        email: String,
        passaword: String,
        home: () -> Unit,
        sizePassword: (Exception?) -> Unit
    ) {
        if (_loading.value == false) {
            _loading.value = true
            auth.createUserWithEmailAndPassword(email, passaword)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("FBC", "createUserWithEmailAndPassword: ${task.result}")
                        home()
                    } else {
                        Log.w("FBC", "createUserWithEmailAndPassword:", task.exception)
                        sizePassword(task.exception)
                    }
                    _loading.value = false
                }
        }
    }

    private fun reload() {
    }

    fun SingOut() {
        FirebaseAuth.getInstance().signOut()
    }

    fun sendEmailVerification() {
        currentUser!!.sendEmailVerification()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("FB", "sendEmailVerification: Email enviado com sucesso ")
                } else {
                    Log.d("FB", "sendEmailVerification: ${task.exception}")
                }
            }
    }

    fun sendPasswordResetEmail(emailCurrentUser: String) {

        Firebase.auth.sendPasswordResetEmail(emailCurrentUser.toString())
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(
                        "Fb",
                        "sendPasswordResetEmail: Email para redefinir Enviado ${task.result}"
                    )
                } else {
                    Log.d("FB", "sendPasswordResetEmail: ${task.exception}")
                }

            }
    }

}


//oneTapClient = Identity.getSignInClient(context)
//signInRequest = BeginSignInRequest.builder()
////            .setPasswordRequestOptions(BeginSignInRequest.PasswordRequestOptions.builder()
////                .setSupported(true)
////                .build())
//.setGoogleIdTokenRequestOptions(
//GoogleIdTokenRequestOptions.builder()
//.setSupported(true)
//.setServerClientId(context.getString(R.string.default_web_client_id))
//.setFilterByAuthorizedAccounts(true)
//.build())
//.setAutoSelectEnabled(true)
//.build()
//
//signInRequest = BeginSignInRequest.builder()
//.setGoogleIdTokenRequestOptions(
//GoogleIdTokenRequestOptions.builder()
//.setSupported(true)
//.setFilterByAuthorizedAccounts(false)
//.setServerClientId(context.getString(R.string.default_web_client_id))
//.build()
//)
//.setAutoSelectEnabled(true)
//.build()
//
