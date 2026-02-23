package com.example.nexolap.viewmodel.uistate

/**
 * Representa el estado de la interfaz de usuario para la lógica de navegación de la aplicación.
 *
 * @property isCheckUserSession Indica si la aplicación está verificando actualmente la sesión activa del usuario.
 * @property isUserLoggedIn Indica si el usuario ha iniciado sesión correctamente en la aplicación.
 * @property initialRoute Define la ruta o destino de navegación inicial. Por defecto es "inicio".
 */
data class AppNavigationUIState(
    val isCheckUserSession: Boolean = false,
    val isUserLoggedIn: Boolean = false,
    val initialRoute: String = "inicio"
)
