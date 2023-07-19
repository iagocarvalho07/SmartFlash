package com.iagocarvalho.smartflash.navegation

enum class ReaderScreensNavigationC {
    ReaderSplashScreen,
    FlashLoginAndCreatAccScreen,
    HomeScreen,
    EmailVerificationScreen,
    ResetPasswordScreen;


    companion object{
        fun FromRoute(route: String): ReaderScreensNavigationC
        = when(route?.substringBefore("/")){
            ReaderSplashScreen.name -> ReaderSplashScreen
            FlashLoginAndCreatAccScreen.name -> FlashLoginAndCreatAccScreen
            HomeScreen.name -> HomeScreen
            EmailVerificationScreen.name -> EmailVerificationScreen
            ReaderSplashScreen.name -> ResetPasswordScreen
            null -> HomeScreen
            else -> throw IllegalArgumentException( "Route $route is not recognized")
        }
    }

}