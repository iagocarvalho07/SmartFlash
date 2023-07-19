package com.iagocarvalho.smartflash.screen.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.iagocarvalho.smartflash.R
import com.iagocarvalho.smartflash.components.EmailImput
import com.iagocarvalho.smartflash.components.HeaderLogo
import com.iagocarvalho.smartflash.components.PasswordInput
import com.iagocarvalho.smartflash.navegation.ReaderScreensNavigationC
import com.iagocarvalho.smartflash.screen.login.FirebaseAuth.FlashCardLoginAndCreateAccViewModel
import kotlinx.coroutines.coroutineScope

@Composable
fun FlashLoginAndCreateAccScreen(
    navController: NavController,
    viewModel: FlashCardLoginAndCreateAccViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
) {

    val showLoginForm = rememberSaveable { mutableStateOf(true) }
    val context = LocalContext.current
    val user = Firebase.auth.currentUser


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderLogo()
        if (showLoginForm.value) {
            //FB Login
            UserForm() { email, password ->
                viewModel.singInWithEmailAndPassword(
                    email,
                    password,
                    home = {

                            navController.navigate(ReaderScreensNavigationC.EmailVerificationScreen.name)
                            Toast.makeText(context, "Login Com Sucesso", Toast.LENGTH_LONG).show()
                    },
                    errors = {
                        Toast.makeText(context, "Erro na senha ou email", Toast.LENGTH_LONG).show()
                    })
            }
        } else {
            //create FB Acount
            UserForm(iscreateAcount = true) { email, password ->
                viewModel.createUserWithEmailAndPassword(
                    email,
                    password,
                    home = {
                            navController.navigate(ReaderScreensNavigationC.EmailVerificationScreen.name)
                            Toast.makeText(context, "Verifique o email", Toast.LENGTH_LONG).show()
                    },
                    sizePassword = {
                        Toast.makeText(
                            context,
                            "A senha deve Conter 6 ou + Caracteres",
                            Toast.LENGTH_LONG
                        ).show()
                    })
            }
        }
        Row(
            modifier = Modifier.padding(15.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val textLoginOucadastre = if (showLoginForm.value) "Cadastre-se?" else "Login?"
            Text(text = "Esqueceu a Senha?", modifier = Modifier
                .clickable { navController.navigate(ReaderScreensNavigationC.ResetPasswordScreen.name) }
                .padding(8.dp))
            Text(
                text = textLoginOucadastre,
                modifier = Modifier.clickable { showLoginForm.value = !showLoginForm.value })
        }

        SignInButtonGoogle {
        }

    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun UserForm(
    loading: Boolean = false,
    iscreateAcount: Boolean = false,
    onDone: (String, String) -> Unit = { email, pws -> }
) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val passwordVisibility = rememberSaveable { mutableStateOf(false) }
    val keybordController = LocalSoftwareKeyboardController.current
    val valid = remember(email.value, password.value) {
        email.value.trim().isNotEmpty() && password.value.trim().isNotEmpty()
    }
    Column(
        modifier = Modifier
            .padding(16.dp),
    ) {
        EmailImput(emailState = email)
        PasswordInput(
            modifier = Modifier,
            passwordState = password,
            labelId = "Passaword",
            enabled = !loading,
            passwordVisibility = passwordVisibility
        )
        SubmitButton(
            textId = if (iscreateAcount) "Criar Conta" else "Login",
            loadind = loading,
            validInputs = valid
        ) {
            onDone(email.value.trim(), password.value.trim())
            keybordController?.hide()
        }


    }
}

@Composable
fun SubmitButton(
    textId: String,
    loadind: Boolean,
    validInputs: Boolean,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth(),
        enabled = !loadind && validInputs,
        shape = CircleShape
    ) {
        if (loadind) CircularProgressIndicator(modifier = Modifier.size(25.dp))
        else Text(text = textId, modifier = Modifier.padding(5.dp))
    }
}

@Composable
fun SignInButtonGoogle(
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier.padding(bottom = 48.dp),
        shape = RoundedCornerShape(6.dp),
        colors = ButtonDefaults.buttonColors(Color.LightGray),
        onClick = onClick
    ) {
        Image(
            painter = painterResource(
                id = R.drawable.ic_google_logo
            ),
            contentDescription = null
        )
        Text(
            text = "Login Com google ",
            modifier = Modifier.padding(6.dp),
            fontSize = 18.sp
        )
    }
}

