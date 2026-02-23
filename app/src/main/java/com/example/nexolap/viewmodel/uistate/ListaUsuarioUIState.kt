package com.example.nexolap.viewmodel.uistate

/**
 * Representa el estado de la interfaz de usuario para la pantalla de lista de usuarios.
 *
 * @property listaUsuarios Lista de objetos [UsuarioUIState] que se mostrarán en la interfaz.
 */
data class ListaUsuarioUIState(var listaUsuarios: List<UsuarioUIState> = ArrayList())
data class UsuarioUIState(
    var id: String,
    var nombre: String,
    var correo: String,
    var contraseña: String
)

/**
 * Representa el estado de la interfaz de usuario para una lista de usuarios (versión 1).
 *
 * @property listaUsuarios Una lista de objetos [UsuarioUIState1] que contiene la información de los usuarios a mostrar.
 */
data class ListaUsuarioUIState1(var listaUsuarios: List<UsuarioUIState1> = ArrayList())
data class UsuarioUIState1(
    val id: Int,
    val nombre: String,
    val correo: String,
    val contraseña: String
)


