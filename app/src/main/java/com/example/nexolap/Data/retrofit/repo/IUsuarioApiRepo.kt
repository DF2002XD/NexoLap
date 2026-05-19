package com.example.nexolap.data.retrofit.repo

import com.example.nexolap.modelo.UsuarioDTO

/**
 * Interfaz que define el repositorio de operaciones para la gestión de usuarios mediante una API.
 */
interface IUsuarioApiRepo {
    suspend fun readAll(onSuccess: (List<UsuarioDTO>) -> Unit, onError: () -> Unit)
    suspend fun read(
        id: String,
        onSuccess: (usuarioCreado: UsuarioDTO?) -> Unit,
        onError: () -> Unit
    )

    suspend fun create(usuarioDTO: UsuarioDTO, onSuccess: () -> Unit, onError: () -> Unit)

    suspend fun update(usuarioDTO: UsuarioDTO, onSuccess: () -> Unit, onError: () -> Unit)
    suspend fun delete(id: String, onSuccess: () -> Unit, onError: () -> Unit)

    suspend fun loginUser(
        correo: String,
        contrasenha: String,
        keepLogged: Boolean,
        onSuccess: (UsuarioDTO) -> Unit,
        onError: () -> Unit
    )

    fun logoutUser(onSuccess: () -> Unit, onError: () -> Unit)

    fun getCurrentUser(): UsuarioDTO?

    suspend fun checkStoredSession(onSuccess: (UsuarioDTO) -> Unit, onError: () -> Unit)
}
