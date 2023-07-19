package com.iagocarvalho.smartflash.navegation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.iagocarvalho.smartflash.screen.ReaderSplashScreen
import com.iagocarvalho.smartflash.screen.home.HomeScreen
import com.iagocarvalho.smartflash.screen.login.EmailVerificationScreen
import com.iagocarvalho.smartflash.screen.login.FlashLoginAndCreateAccScreen
import com.iagocarvalho.smartflash.screen.login.ResetPasswordScreen

@Composable
fun ReaderScreensNavigationfun(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ReaderScreensNavigationC.ReaderSplashScreen.name ){
        composable(ReaderScreensNavigationC.ReaderSplashScreen.name){
            ReaderSplashScreen(navController = navController)
        }
        composable(ReaderScreensNavigationC.FlashLoginAndCreatAccScreen.name){
            FlashLoginAndCreateAccScreen(navController = navController)
        }
        composable(ReaderScreensNavigationC.HomeScreen.name){
            HomeScreen(navController = navController)
        }
        composable(ReaderScreensNavigationC.ResetPasswordScreen.name){
            ResetPasswordScreen(navController = navController)
        }
        composable(ReaderScreensNavigationC.EmailVerificationScreen.name){
            EmailVerificationScreen(navController = navController)
        }
    }
}