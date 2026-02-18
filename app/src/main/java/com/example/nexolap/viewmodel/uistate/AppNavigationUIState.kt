package com.example.nexolap.viewmodel.uistate

data class AppNavigationUIState(
    val isCheckUserSession: Boolean = false,
    val isUserLoggedIn: Boolean = false,
    val initialRoute: String = "inicio"
)
