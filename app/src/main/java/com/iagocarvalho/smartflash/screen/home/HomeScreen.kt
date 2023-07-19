package com.iagocarvalho.smartflash.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.google.android.gms.stats.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.iagocarvalho.smartflash.navegation.ReaderScreensNavigationC
import com.iagocarvalho.smartflash.screen.login.FirebaseAuth.FlashCardLoginAndCreateAccViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: FlashCardLoginAndCreateAccViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val user = Firebase.auth.currentUser
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "home Screnn")
        AsyncImage(
            modifier = Modifier.size(100.dp),
            model = if (user?.photoUrl == null) {
                Icon(imageVector = Icons.Default.Person, contentDescription = "")
            } else {
                user.photoUrl
            },
            contentDescription = ""
        )
        Text(text = "${user?.displayName}")
        Text(text = "${user?.email.toString()} o e email esta verificado ${user?.isEmailVerified.toString()}")

        Button(onClick = {
            viewModel.SingOut()
            navController.navigate(ReaderScreensNavigationC.FlashLoginAndCreatAccScreen.name)
        }) {
            Text(text = "Sair do App")

        }

    }
}
