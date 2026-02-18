package com.example.nexolap.viewmodel.uistate

data class LoginPageUIState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val keepLogged: Boolean = false,
    val isLoginSuccessful: Boolean = false
)
