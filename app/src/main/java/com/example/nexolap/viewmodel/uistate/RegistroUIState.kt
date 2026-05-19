package com.example.nexolap.viewmodel.uistate

import com.example.nexolap.data.UsuarioApi

/**
 * Representa el estado de la interfaz de usuario (UI) durante el proceso de registro
 * utilizando el modelo de datos compatible con la API ([UsuarioApi]).
 *
 * @property usuario El objeto que contiene la información del usuario a registrar.
 * @property repitaContrasenha Campo para la confirmación de la contraseña ingresada.
 * @property isLoading Indica si hay una operación de registro en curso.
 * @property registrationSuccess Indica si el proceso de registro se completó correctamente.
 * @property error Mensaje de error en caso de que ocurra una falla durante el registro.
 */
data class RegistroUIStateApi(
    val usuario: UsuarioApi = UsuarioApi("", "", "", "", false, 0),
    val repitaContrasenha: String = "",
    val isLoading: Boolean = false,
    val registrationSuccess: Boolean = false,
    val error: String? = null
)
