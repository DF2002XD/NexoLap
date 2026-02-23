package com.example.nexolap.viewmodel.uistate

/**
 * Representa el estado de la interfaz de usuario para la pantalla de inicio de sesión.
 *
 * @property email El valor actual del campo de entrada para el correo electrónico.
 * @property password El valor actual del campo de entrada para la contraseña.
 * @property isLoading Indica si hay un proceso de autenticación en curso.
 * @property errorMessage Contiene el mensaje de error a mostrar si el proceso de inicio de sesión falla.
 * @property keepLogged Indica si el usuario ha seleccionado la opción de mantener la sesión iniciada.
 * @property isLoginSuccessful Indica si el intento de inicio de sesión se completó con éxito.
 */
data class LoginPageUIState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val keepLogged: Boolean = false,
    val isLoginSuccessful: Boolean = false
)
