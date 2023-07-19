package com.iagocarvalho.smartflash.screen.login

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.iagocarvalho.smartflash.navegation.ReaderScreensNavigationC
import com.iagocarvalho.smartflash.screen.login.FirebaseAuth.FlashCardLoginAndCreateAccViewModel


@Composable
fun EmailVerificationScreen(
    navController: NavController,
    viewModel: FlashCardLoginAndCreateAccViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val context = LocalContext.current
    val user = Firebase.auth.currentUser
    user?.let {
        val emailVerified = it.isEmailVerified

        LaunchedEffect(key1 = true){
            if (emailVerified) {
                navController.navigate(ReaderScreensNavigationC.HomeScreen.name)
            }
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Realize a Verificação de email para acessar acessar o Ap")
            Button(onClick = {
                viewModel.sendEmailVerification().run {
                    navController.navigate(ReaderScreensNavigationC.FlashLoginAndCreatAccScreen.name)
                    Toast.makeText(context, "Email de Verificação Enviado", Toast.LENGTH_LONG)
                        .show()
                }
            }) {
                Text(text = "Confirmação de email ")
            }
        }

    }


}

