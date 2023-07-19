package com.iagocarvalho.smartflash.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.iagocarvalho.smartflash.components.HeaderLogo
import com.iagocarvalho.smartflash.navegation.ReaderScreensNavigationC
import kotlinx.coroutines.delay

@Composable
fun ReaderSplashScreen(navController: NavController){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LaunchedEffect(key1 = true, ){
            delay(1500L)

            navController.navigate(ReaderScreensNavigationC.FlashLoginAndCreatAccScreen.name)
        }
        HeaderLogo()
        Text(text = "Preparando o Ambiete")
    }
//    if(FirebaseAuth.getInstance().currentUser?.email.isNullOrEmpty()){
//            navController.navigate(ReaderScreensNavigationC.FlashLoginAndCreatAccScreen.name)
//        }else{
//            navController.navigate(ReaderScreensNavigationC.HomeScreen.name)
//        }
}