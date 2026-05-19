package com.example.nexolap.viewmodel.uistate

/**
 * Representa el estado de la interfaz de usuario para la pantalla de lista de usuarios.
 *
 * @property listaUsuarios Lista de objetos [UsuarioUIState] que se mostrarán en la interfaz.
 * @property error Mensaje de error opcional en caso de fallos en las operaciones del perfil.
 */
data class ListaUsuarioUIState(
    var listaUsuarios: List<UsuarioUIState> = ArrayList(),
    var error: String? = null
)

/**
 * Representa la información básica de un usuario para ser mostrada en la interfaz de usuario.
 *
 * @property id Identificador único del usuario.
 * @property nombre Nombre del usuario.
 * @property correo Dirección de correo electrónico.
 * @property contraseña Contraseña del usuario.
 */
data class UsuarioUIState(
    var id: String,
    var nombre: String,
    var correo: String,
    var contraseña: String,
    var foto: String? = null
)
