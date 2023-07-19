package com.iagocarvalho.smartflash.screen.login

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.iagocarvalho.smartflash.navegation.ReaderScreensNavigationC
import com.iagocarvalho.smartflash.screen.login.FirebaseAuth.FlashCardLoginAndCreateAccViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResetPasswordScreen(
    navController: NavController,
    viewModel: FlashCardLoginAndCreateAccViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val emailResetPassword = remember {
        mutableStateOf("")

    }
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = emailResetPassword.value,
            onValueChange = { emailResetPassword.value = it },
            label = {
                Text(
                    text = "Email"
                )
            })
        Button(onClick = {
            viewModel.sendPasswordResetEmail(emailCurrentUser = emailResetPassword.value).run {
                navController.navigate(ReaderScreensNavigationC.FlashLoginAndCreatAccScreen.name)
                Toast.makeText(context, "Senha Enviada para Email", Toast.LENGTH_LONG).show()
            }
        }) {
            Text(text = "Redefinir Senha ")

        }

    }
}