package com.example.nexolap.data.repository

import com.example.nexolap.modelo.UsuarioDTO

/**
 * Interfaz del repositorio que define las operaciones de acceso a datos para la entidad Usuario.
 * Proporciona métodos para la gestión CRUD y el manejo del estado de sesión del usuario.
 */
interface IUsuarioRepo {
    fun readAll(onSuccess: (List<UsuarioDTO>) -> Unit, onError: () -> Unit)
    fun read(id: String, onSuccess: (usuarioCreado: UsuarioDTO?) -> Unit, onError: () -> Unit)

    fun create(usuarioDTO: UsuarioDTO, onSuccess: () -> Unit, onError: () -> Unit)

    fun update(usuarioDTO: UsuarioDTO, onSuccess: () -> Unit, onError: () -> Unit)
    fun delete(id: String, onSuccess: () -> Unit, onError: () -> Unit)

    fun loginUser(
        email: String,
        passwd: String,
        keepLogged: Boolean,
        onSuccess: (UsuarioDTO) -> Unit,
        onError: () -> Unit
    )

    fun logoutUser(onSuccess: () -> Unit, onError: () -> Unit)

    fun getCurrentUser(): UsuarioDTO?
}

